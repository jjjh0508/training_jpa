package com.jihwan.springdata.order.controller;


import com.jihwan.springdata.order.dto.MenuOrderDTO;
import com.jihwan.springdata.menu.service.MenuService;
import com.jihwan.springdata.order.dto.OrderUpdateDTO;
import com.jihwan.springdata.order.entity.Order;
import com.jihwan.springdata.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
@Api(value = "주문 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class OrderController {

    private final MenuService menuService;
    private final OrderService orderService;

    public OrderController(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }


    @GetMapping
    @ApiOperation(value = "전체 주문 검색 Api")
    public ResponseEntity<List<?>> findAllOrder() {
        List<Order> orders = orderService.findAllOrder();

        System.out.println(orders);
        return ResponseEntity.ok().body(orders);

    }


    @GetMapping("/{orderCode}")
    @ApiOperation(value = "단일 주문 검색 Api")
    public ResponseEntity<Object> findOrderByCode(@PathVariable int orderCode) {
        Order order = orderService.findOrderByCode(orderCode);
        for (int i = 0; i < order.getOrderMenuList().size(); i++) {
            System.out.println(order.getOrderMenuList().get(i).getMenu());
        }
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    @ApiOperation(value = " 주문 등록 Api")
    public ResponseEntity<?> orderRegist(@RequestBody List<MenuOrderDTO> menuOrderDTO) {


        if (Objects.isNull(menuOrderDTO)) {
            return ResponseEntity.status(404).body("잘못된 주문입니다.");
        }
        int result = orderService.regist(menuOrderDTO);

        if (result > 0) {
            return ResponseEntity.ok().body("주문되었습니다.");
        } else {
            return ResponseEntity.status(500).body("주문 실패했습니다.");
        }
    }

    @DeleteMapping("{orderCode}")
    @ApiOperation(value = "주문 삭제 Api")
    public ResponseEntity<?> orderDelete(@PathVariable int orderCode) {
        Order findOrder = orderService.findOrderByCode(orderCode);
        if (Objects.isNull(findOrder)) {
            ResponseEntity.status(404).body("주문이 없습니다.");
        }
        int result = orderService.orderDelete(findOrder);

        if (result > 0) {
            return ResponseEntity.ok().body("주문이 취소되었습니다.");
        } else {
            return ResponseEntity.status(500).body("주문을 삭제할 수 없습니다.");
        }
    }

    @PutMapping
    @ApiOperation(value = "주문 수정 Api")
    public ResponseEntity<?> orderUpdate(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        Order findOrder = orderService.findOrderByCode(orderUpdateDTO.getOrderCode());

        if (Objects.isNull(findOrder)) {
            return ResponseEntity.status(404).body("존재하지 않는 주문입니다.");
        }
        int result = orderService.orderUpdate(orderUpdateDTO);

        if (result > 0) {
            return ResponseEntity.ok().body("주문이 수정되었습니다.");
        }else {
            return ResponseEntity.status(500).body("주문을 수정할 수 없습니다.");
        }
    }

}

package com.jihwan.springdata.order.controller;


import com.jihwan.springdata.order.dto.MenuOrderDTO;
import com.jihwan.springdata.menu.service.MenuService;
import com.jihwan.springdata.order.entity.Order;
import com.jihwan.springdata.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final MenuService menuService;
    private final OrderService orderService;

    public OrderController(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<?>> findAllOrder() {
        List<Order> orders = orderService.findAllOrder();

        System.out.println(orders);
        return ResponseEntity.ok().body(orders);

    }


    @GetMapping("/{orderCode}")
    public ResponseEntity<Object> findOrderByCode(@PathVariable int orderCode) {
        System.out.println(orderCode);
        Order order = orderService.findOrderByCode(orderCode);
        for (int i = 0; i < order.getOrderMenuList().size(); i++) {
            System.out.println(order.getOrderMenuList().get(i).getMenu());
        }
        return ResponseEntity.ok().body(order);
    }
    @PostMapping("/regist")
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



}

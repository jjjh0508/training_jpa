package com.jihwan.springdata.order.controller;


import com.jihwan.springdata.order.dto.MenuOrderDTO;
import com.jihwan.springdata.menu.service.MenuService;
import com.jihwan.springdata.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

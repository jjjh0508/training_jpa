package com.jihwan.springdata.order.service;

import com.jihwan.springdata.order.dto.MenuOrderDTO;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.order.entity.Order;
import com.jihwan.springdata.order.entity.OrderMenu;
import com.jihwan.springdata.menu.entity.OrderPk;
import com.jihwan.springdata.menu.repository.MenuRepository;
import com.jihwan.springdata.order.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    public OrderService(MenuRepository menuRepository, OrderRepository orderRepository) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public int regist(List<MenuOrderDTO> menuOrderDTO) {
        Order order = new Order();
        List<OrderMenu> orderMenuList = new ArrayList<>();
        int totalSum = 0;

        for (int i = 0; i < menuOrderDTO.size(); i++) {
            Menu menu = menuRepository.findById(menuOrderDTO.get(i).getMenuCode());
            System.out.println(menu);
            orderMenuList.add(new OrderMenu(new OrderPk(order.getOrderCode(), menu.getMenuCode()), order, menu, menuOrderDTO.get(i).getAmount()));
            totalSum+=menu.getMenuPrice()*menuOrderDTO.get(i).getAmount();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        order.setOrderMenuList(orderMenuList);
        order.setTotalOrderPrice(totalSum);
        order.setOrderDate(simpleDateFormat.format(new Date()));
        order.setOrderTime(simpleDateFormatTime.format(new Date()));
        Order findOrder = orderRepository.save(order);

        if (Objects.isNull(findOrder)) {
            return 0;
        } else {
            return 1;
        }



    }

    public List<Order> findAllOrder() {
         List<Order> orderList = orderRepository.findAll();
         return orderList;
    }

    public Order findOrderByCode(int orderCode) {
        Order order = orderRepository.findById(orderCode);
        return order;
    }
}

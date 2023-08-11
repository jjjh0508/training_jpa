package com.jihwan.springdata.order.service;

import com.jihwan.springdata.order.dto.MenuOrderDTO;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.order.dto.OrderUpdateDTO;
import com.jihwan.springdata.order.entity.Order;
import com.jihwan.springdata.order.entity.OrderMenu;
import com.jihwan.springdata.order.entity.OrderPk;
import com.jihwan.springdata.menu.repository.MenuRepository;
import com.jihwan.springdata.order.repository.OrdeMenuRepository;
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

    private final OrdeMenuRepository ordeMenuRepository;

    public OrderService(MenuRepository menuRepository, OrderRepository orderRepository, OrdeMenuRepository ordeMenuRepository) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.ordeMenuRepository = ordeMenuRepository;
    }

    @Transactional
    public int regist(List<MenuOrderDTO> menuOrderDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        Order order = new Order();
        List<OrderMenu> orderMenuList = new ArrayList<>();
        int totalSum = 0;

        for (int i = 0; i < menuOrderDTO.size(); i++) {
            Menu menu = menuRepository.findById(menuOrderDTO.get(i).getMenuCode());
            System.out.println(menu);
            orderMenuList.add(new OrderMenu(new OrderPk(order.getOrderCode(), menu.getMenuCode()), order, menu, menuOrderDTO.get(i).getAmount()));
            totalSum+=menu.getMenuPrice()*menuOrderDTO.get(i).getAmount();
        }

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

    public int orderDelete(Order findOrder) {
        orderRepository.delete(findOrder);
        Order order = orderRepository.findById(findOrder.getOrderCode());
        if (Objects.isNull(order)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    public int orderUpdate(OrderUpdateDTO orderUpdateDTO) {
        Order findOrder = orderRepository.findById(orderUpdateDTO.getOrderCode());
        //새로운 주문에서 메뉴 리스트 가져온다
        List<MenuOrderDTO> menuOrderDTOS = orderUpdateDTO.getMenuOrderDTOS();
        // 원래 메뉴의 리스트를 가져온다
        List<OrderMenu> orderMenuList = findOrder.getOrderMenuList();
        // 새롭게 담을 엔티티 리스트 생성
        List<OrderMenu> newOrderMenuList =new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        int priceSum = 0;
        //추가되는 메뉴가 없으면 리턴
        if(menuOrderDTOS.size()==0){
            return 0;
        }
        //세팅
        for (int i = 0; i < menuOrderDTOS.size(); i++) {
            Menu findMenu = menuRepository.findById(menuOrderDTOS.get(i).getMenuCode());
            newOrderMenuList.add(new OrderMenu(new OrderPk(findOrder.getOrderCode(),menuOrderDTOS.get(i).getMenuCode()),findOrder,findMenu,menuOrderDTOS.get(i).getAmount()));
            priceSum+= findMenu.getMenuPrice()*menuOrderDTOS.get(i).getAmount();
        }

            ordeMenuRepository.deleteAllInBatch(findOrder.getOrderMenuList());

        findOrder.setOrderMenuList(newOrderMenuList);
        findOrder.setOrderDate(simpleDateFormat.format(new Date()));
        findOrder.setOrderTime(simpleDateFormatTime.format(new Date()));
        findOrder.setTotalOrderPrice(priceSum);



        Order newOrder = orderRepository.save(findOrder);

        if (Objects.isNull(newOrder)) {
            return 0;
        } else {
            return 1;
        }

    }
}

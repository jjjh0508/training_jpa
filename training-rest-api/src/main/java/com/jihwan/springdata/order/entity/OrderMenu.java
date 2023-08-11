package com.jihwan.springdata.order.entity;

import com.jihwan.springdata.menu.entity.Menu;

import javax.persistence.*;

@Entity(name = "orderMenu")
@Table(name = "tbl_order_menu")
public class OrderMenu {

    @EmbeddedId
    private OrderPk orderPk;

    @MapsId("orderCode")
    @ManyToOne
    @JoinColumn(name = "order_code")
    private Order order;


    @MapsId("menuCode")
    @ManyToOne //cascade 옵션을 주면 에러남 같이 지울려고 한다
    @JoinColumn(name = "menu_code")
    private Menu menu;


    @Column(name = "order_amount")
    private int orderAmount;

    public OrderMenu() {
    }

    public OrderMenu(OrderPk orderPk, Order order, Menu menu, int orderAmount) {
        this.orderPk = orderPk;
        this.order = order;
        this.menu = menu;
        this.orderAmount = orderAmount;
    }

    public OrderPk getOrderPk() {
        return orderPk;
    }

    public void setOrderPk(OrderPk orderPk) {
        this.orderPk = orderPk;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "orderPk=" + orderPk +

                ", menu=" + menu +
                ", orderAmount=" + orderAmount +
                '}';
    }
}

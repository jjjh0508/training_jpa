package com.jihwan.springdata.order.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderPk implements Serializable {
    @Column(name = "order_code")
    private int orderCode;
    @Column(name = "menu_code")
    private int menuCode;

    public OrderPk(int orderCode, int menuCode) {
        this.orderCode = orderCode;
        this.menuCode = menuCode;
    }

    public OrderPk() {
    }

    public int getOrderCode() {
        return orderCode;
    }



    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "OrderPk{" +
                "orderCode=" + orderCode +
                ", menuCode=" + menuCode +
                '}';
    }
}

package com.jihwan.springdata.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


import java.util.List;

@Entity(name = "order")
@Table(name = "tbl_order")
public class Order {


    @Id
    @Column(name = "order_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;


    @Column(name = "order_date",length = 8)
    private String orderDate;

    @Column(name = "order_time",length = 8)
    private String orderTime;


    @Column(name = "total_order_price")
    private int totalOrderPrice;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "order")
    private List<OrderMenu> orderMenuList;


    public Order() {
    }

    public Order(int orderCode, String orderDate, String orderTime, int totalOrderPrice, List<OrderMenu> orderMenuList) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalOrderPrice = totalOrderPrice;
        this.orderMenuList = orderMenuList;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public List<OrderMenu> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(List<OrderMenu> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", totalOrderPrice=" + totalOrderPrice +
                '}';
    }
}

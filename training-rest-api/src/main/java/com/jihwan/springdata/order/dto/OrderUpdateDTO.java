package com.jihwan.springdata.order.dto;

import java.util.List;

public class OrderUpdateDTO {

    private int orderCode;


    private List<MenuOrderDTO> menuOrderDTOS;

    public OrderUpdateDTO() {
    }

    public OrderUpdateDTO(int orderCode, List<MenuOrderDTO> menuOrderDTOS) {
        this.orderCode = orderCode;
        this.menuOrderDTOS = menuOrderDTOS;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public List<MenuOrderDTO> getMenuOrderDTOS() {
        return menuOrderDTOS;
    }

    public void setMenuOrderDTOS(List<MenuOrderDTO> menuOrderDTOS) {
        this.menuOrderDTOS = menuOrderDTOS;
    }


}

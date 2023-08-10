package com.jihwan.springdata.order.dto;

public class MenuOrderDTO {

    private int menuCode;

    private int amount;


    public MenuOrderDTO() {
    }


    public MenuOrderDTO(int menuCode, int amount) {
        this.menuCode = menuCode;
        this.amount = amount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MenuOrder{" +
                "menuCode=" + menuCode +
                ", amount=" + amount +
                '}';
    }
}

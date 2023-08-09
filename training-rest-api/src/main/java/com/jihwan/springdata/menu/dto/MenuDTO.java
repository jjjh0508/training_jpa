package com.jihwan.springdata.menu.dto;

import com.jihwan.springdata.menu.entity.Menu;

public class MenuDTO {

    private int menuCode;
    private String menuName;

    private int menuPrice;
    private int categoryCode;
    private String  orderableStatus;

    //엔티티를 dto에 한번에 넣기 위해 생성
    public MenuDTO(Menu menu){
        this.menuCode =menu.getMenuCode();
        this.menuName =menu.getMenuName();
        this.menuPrice =menu.getMenuPrice();
        this.categoryCode= menu.getCategoryCode();
        this.orderableStatus = menu.getOrderableStatus();
    }
    public MenuDTO(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public MenuDTO() {
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "menuDTO{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}

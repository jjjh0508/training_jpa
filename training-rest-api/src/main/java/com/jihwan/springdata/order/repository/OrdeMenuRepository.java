package com.jihwan.springdata.order.repository;

import com.jihwan.springdata.order.entity.OrderMenu;
import com.jihwan.springdata.order.entity.OrderPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdeMenuRepository extends JpaRepository<OrderMenu, OrderPk> {

}

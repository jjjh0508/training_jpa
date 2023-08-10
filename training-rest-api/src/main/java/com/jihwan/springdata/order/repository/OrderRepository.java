package com.jihwan.springdata.order.repository;

import com.jihwan.springdata.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int orderCode);
}

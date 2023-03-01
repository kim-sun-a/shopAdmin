package com.shop.shopadmin.repository;

import com.shop.shopadmin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

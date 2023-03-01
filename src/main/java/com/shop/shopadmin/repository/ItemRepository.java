package com.shop.shopadmin.repository;

import com.shop.shopadmin.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

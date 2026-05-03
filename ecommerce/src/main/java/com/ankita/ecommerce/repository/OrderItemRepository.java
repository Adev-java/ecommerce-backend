package com.ankita.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}

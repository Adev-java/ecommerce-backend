package com.ankita.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
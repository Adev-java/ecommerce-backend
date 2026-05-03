package com.ankita.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.ecommerce.entity.CartItem;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
package com.ankita.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

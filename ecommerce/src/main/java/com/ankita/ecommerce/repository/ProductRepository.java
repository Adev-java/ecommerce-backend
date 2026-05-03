package com.ankita.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
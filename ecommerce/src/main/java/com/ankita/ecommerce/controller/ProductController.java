package com.ankita.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankita.ecommerce.dto.ProductRequestDTO;
import com.ankita.ecommerce.dto.ProductResponseDTO;
import com.ankita.ecommerce.entity.Product;
import com.ankita.ecommerce.service.ProductService;
 import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody @NonNull Product product) {
        return productService.addProduct(product);
    }
    @PostMapping("/request")
public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO dto) {
    return productService.addProduct(dto);
}

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

   

@GetMapping("/products")
public Page<Product> getProducts(
        @RequestParam int page,
        @RequestParam int size) {

    return productService.getAllProducts(page, size);
}
}
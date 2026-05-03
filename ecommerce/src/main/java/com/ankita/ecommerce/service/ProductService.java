package com.ankita.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ankita.ecommerce.dto.ProductRequestDTO;
import com.ankita.ecommerce.dto.ProductResponseDTO;
import com.ankita.ecommerce.entity.Product;
import com.ankita.ecommerce.repository.ProductRepository;
 import org.springframework.data.domain.*;

@Service
public class ProductService {
   

public Page<Product> getAllProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return productRepository.findAll(pageable);
}

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(@NonNull Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

    Product product = new Product();
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());

    Product saved = productRepository.save(product);

    return new ProductResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getPrice()
    );
}
}

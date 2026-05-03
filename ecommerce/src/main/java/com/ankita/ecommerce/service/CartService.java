package com.ankita.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ankita.ecommerce.entity.Cart;
import com.ankita.ecommerce.entity.CartItem;
import com.ankita.ecommerce.entity.Product;
import com.ankita.ecommerce.repository.CartItemRepository;
import com.ankita.ecommerce.repository.CartRepository;
import com.ankita.ecommerce.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart addToCart( @NonNull Long cartId,  @NonNull Long productId, int quantity) {

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        Product product = productRepository.findById(productId).orElseThrow();
         // ✅ STOCK CHECK
    if (product.getStock() < quantity) {
        throw new RuntimeException("Not enough stock available");
    }

  // ✅ CHECK IF PRODUCT ALREADY EXISTS IN CART
    CartItem existingItem = cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .orElse(null);
              if (existingItem != null) {
        // ✅ UPDATE QUANTITY
        existingItem.setQuantity(existingItem.getQuantity() + quantity);
        cartItemRepository.save(existingItem);
    } else {
        // ✅ CREATE NEW ITEM
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);

        cartItemRepository.save(newItem);
        cart.getItems().add(newItem);
    }

 return cartRepository.save(cart);
}
       
}

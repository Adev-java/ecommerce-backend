package com.ankita.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankita.ecommerce.entity.Cart;
import com.ankita.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestParam @NonNull Long cartId,
                          @RequestParam @NonNull Long productId,
                          @RequestParam int quantity) {

        return cartService.addToCart(cartId, productId, quantity);
    }
}
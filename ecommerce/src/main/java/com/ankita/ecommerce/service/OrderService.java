package com.ankita.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ankita.ecommerce.entity.Cart;
import com.ankita.ecommerce.entity.CartItem;
import com.ankita.ecommerce.entity.Order;
import com.ankita.ecommerce.entity.OrderItem;
import com.ankita.ecommerce.entity.Product;
import com.ankita.ecommerce.repository.CartRepository;
import com.ankita.ecommerce.repository.OrderRepository;
import com.ankita.ecommerce.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(@NonNull Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();
             if (product.getStock() < cartItem.getQuantity()) {
        throw new RuntimeException("Insufficient stock for product: " + product.getName());
    }
            // reduce stock
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());

            total += product.getPrice() * cartItem.getQuantity();

            orderItems.add(orderItem);
        }
        // ✅ CLEAR CART AFTER ORDER
cart.getItems().clear();
cartRepository.save(cart);

order.setItems(orderItems);
        order.setTotalPrice(total);

        return orderRepository.save(order);
       
    }
}
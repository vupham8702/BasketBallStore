package com.project.basketballstore.controller;

import com.project.basketballstore.model.shopping_cart.Order;
import com.project.basketballstore.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @PostMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable int id){
        return orderRepository.findById(id) ;
    }

    @GetMapping
    public ResponseEntity<?> createOrder(){
        return null;
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteOrder(){
        return null;
    }
}

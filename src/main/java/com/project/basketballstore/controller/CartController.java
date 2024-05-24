package com.project.basketballstore.controller;

import com.project.basketballstore.model.DTO.CartDTO;
import com.project.basketballstore.model.shopping_cart.Cart;
import com.project.basketballstore.model.shopping_cart.Item;
import com.project.basketballstore.service.Cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addItemToCart(@PathVariable int id, Principal principal){
       try {
           cartService.addToCart(id,principal);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thất bại !!!");
       }
        return  ResponseEntity.ok("Thành công !!!");
    }

    @GetMapping
    public ResponseEntity<?> viewCart(Principal principal){
        List<Item> itemList = cartService.showCart(principal);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateItemCart(){
        cartService.updateCart();
        return ResponseEntity.ok("Thành công !!!");
    }

}

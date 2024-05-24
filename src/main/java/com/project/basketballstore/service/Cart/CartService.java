package com.project.basketballstore.service.Cart;

import com.project.basketballstore.model.DTO.CartDTO;
import com.project.basketballstore.model.shopping_cart.Cart;

import java.security.Principal;

public interface CartService {
    Cart addToCart(int id, Principal principal);
    Cart viewCart(Principal principal);
    Cart updateCart();
}

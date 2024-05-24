package com.project.basketballstore.service.Cart;


import com.project.basketballstore.model.shopping_cart.Cart;
import com.project.basketballstore.model.shopping_cart.Item;

import java.security.Principal;
import java.util.List;

public interface CartService {
    Cart addToCart(int id, Principal principal);
    List<Item> showCart(Principal principal);
    Cart updateCart();
}

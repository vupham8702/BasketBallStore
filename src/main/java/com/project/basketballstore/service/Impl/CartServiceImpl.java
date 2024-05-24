package com.project.basketballstore.service.Impl;

import com.project.basketballstore.model.DTO.CartDTO;
import com.project.basketballstore.model.product.Product;
import com.project.basketballstore.model.shopping_cart.Cart;
import com.project.basketballstore.model.shopping_cart.Item;
import com.project.basketballstore.model.user.User;
import com.project.basketballstore.repository.ItemRepository;
import com.project.basketballstore.repository.CartRepository;
import com.project.basketballstore.repository.ProductRepository;
import com.project.basketballstore.service.Cart.CartService;
import com.project.basketballstore.service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, ItemRepository itemRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Cart addToCart(int id, Principal principal) {
        Cart cart = checkCart(principal);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        };
        Product product = optionalProduct.get();
        Set<Item> items = cart.getItem();
        Item item = new Item();
        item.setQuantity(1);
        item.setProductId(product.getId());
        item = itemRepository.save(item);
        items.add(item);
        cart.setItem(items);
        return cartRepository.save(cart);
    }

    @Override
    public Cart viewCart(Principal principal) {
        Cart cart = checkCart(principal);
        return cart;
    }

    @Override
    public Cart updateCart() {

        return null;
    }

    public Cart checkCart(Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Optional<Cart> cart = cartRepository.findById(user.getId());
        if (cart.isEmpty()) {
            Cart cartUser = new Cart();
            cartUser.setId(user.getId());
            cartRepository.save(cartUser);
            return cartUser;
        }
        return cart.get();

    }
}

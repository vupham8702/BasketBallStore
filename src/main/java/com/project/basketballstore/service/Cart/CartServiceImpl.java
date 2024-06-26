package com.project.basketballstore.service.Cart;

import com.project.basketballstore.model.DTO.ItemDTO;
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
import java.util.*;


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
        item.setProduct(product);

        item = itemRepository.save(item);
        items.add(item);
        cart.setItem(items);
        return cartRepository.save(cart);
    }

    @Override
    public List<Item> showCart(Principal principal) {
        Cart cart = checkCart(principal);
        Set<Item> items = cart.getItem();

        return new ArrayList<>(items);
    }

    @Override
    public Item updateCart(int id, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isEmpty()){
            throw new RuntimeException("sản phẩm không khả dụng !!!");
        }
        Item item = itemOptional.get();
        item.setQuantity(itemDTO.getQuantity());
        return  itemRepository.save(item);
    }

    @Override
    @Transactional
    public void removeItem(int id,Principal principal) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isEmpty()){
            throw new RuntimeException("sản phẩm không khả dụng !!!");
        }
        Item item = itemOptional.get();
        Cart cart = checkCart(principal);
        Set<Item> items = cart.getItem();
        items.remove(item);
        cartRepository.save(cart);
        itemRepository.deleteById(id);
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

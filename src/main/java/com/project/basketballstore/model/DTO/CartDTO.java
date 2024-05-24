package com.project.basketballstore.model.DTO;


import com.project.basketballstore.model.shopping_cart.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private int id;
    private Set<Item> item ;
}

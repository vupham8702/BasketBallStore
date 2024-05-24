package com.project.basketballstore.model.shopping_cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart")
public class Cart {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    @JoinTable(name = "cart_item", joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> item ;


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", item=" + item +'\'' +
                '}';
    }
}

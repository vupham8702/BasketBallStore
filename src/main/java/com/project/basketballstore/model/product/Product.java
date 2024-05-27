package com.project.basketballstore.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.basketballstore.model.image.ImageModel;
import com.project.basketballstore.model.shopping_cart.Item;
import com.project.basketballstore.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "name")
    private String name;

    @Column(name="price")
    private Double price;

    @Column(name = "inventory")
    private int inventory;

    @Column(name = "size")
    private Double size;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    @JoinTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageModel> productImage  ;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", size=" + size +
                ", productImage=" + productImage +
                ", item=" + item +
                ", user=" + user +
                '}';
    }
}

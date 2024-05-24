package com.project.basketballstore.model.shopping_cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "shopping_firm")
    private String shoppingFirm;

    @Column(name = "payment_methods")
    private String paymentMethods;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", recipient='" + recipient + '\'' +
                ", shoppingFirm='" + shoppingFirm + '\'' +
                ", paymentMethods='" + paymentMethods + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}

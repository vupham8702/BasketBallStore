package com.project.basketballstore.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String phoneNumber;
    private String address;
    private String recipient;
    private String shoppingFirm;
    private Double totalPrice;
    private String paymentMethods;
    private LocalDateTime createAt;
}

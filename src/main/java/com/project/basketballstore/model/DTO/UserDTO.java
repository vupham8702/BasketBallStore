package com.project.basketballstore.model.DTO;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private int id;

    private String email;

    private String name;

    private String password;

    private boolean status;
}

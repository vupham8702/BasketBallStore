package com.project.basketballstore.model.DTO;

import com.project.basketballstore.model.image.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String productId;
    private String name;
    private Double price;
    private int inventory;
    private Double size;
    private Set<MultipartFile> file;
    private int userId;
}

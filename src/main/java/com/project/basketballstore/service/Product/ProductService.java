package com.project.basketballstore.service.Product;

import com.project.basketballstore.model.DTO.ProductDTO;
import com.project.basketballstore.model.product.Product;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

public interface ProductService {
    Product addNewProduct(ProductDTO productDTO, Principal principal) throws IOException;
    Product deleteProductDetail(int id);

    Optional<Product> getProductDetail(int id);
}

package com.project.basketballstore.service.Impl;

import com.project.basketballstore.model.DTO.ProductDTO;
import com.project.basketballstore.model.image.ImageModel;
import com.project.basketballstore.model.product.Product;
import com.project.basketballstore.model.user.User;
import com.project.basketballstore.repository.ImageRepository;
import com.project.basketballstore.repository.ProductRepository;
import com.project.basketballstore.service.Product.ProductService;
import com.project.basketballstore.service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ImageRepository imageRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Product addNewProduct(ProductDTO productDTO, Principal principal) throws IOException {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Set<ImageModel> images = uploadImage(productDTO.getFile());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setInventory(productDTO.getInventory());
        product.setSize(productDTO.getSize());
        product.setProductId(productDTO.getProductId());
        product.setUser(user);
        product.setProductImage(images);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProductDetail(int id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductDetail(int id) {
        return productRepository.findById(id);
    }


    public Set<ImageModel> uploadImage(Set<MultipartFile> multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageRepository.save(imageModel);
            imageModels.add(imageModel);

        }
        return imageModels;
    }
}

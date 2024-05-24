package com.project.basketballstore.controller;

import com.project.basketballstore.model.DTO.ProductDTO;
import com.project.basketballstore.model.product.Product;
import com.project.basketballstore.repository.ProductRepository;
import com.project.basketballstore.service.Product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @GetMapping
    public ResponseEntity<?> listProduct() {
        List<Product> reportList = productRepository.findAll();
        return ResponseEntity.ok(reportList);

    }

    @GetMapping("/{id}")
    public Optional<Product> getProductDetail(@PathVariable int id){
        return productService.getProductDetail(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@ModelAttribute ProductDTO productDTO, Principal principal) {
        try {
            productService.addNewProduct(productDTO, principal);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tạo mới thất bại !!!");
        }
        return ResponseEntity.ok("Tạo mới thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
       productService.deleteProductDetail(id);
       return ResponseEntity.ok("Xóa thành công sản phẩm !!!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable int id){
        return null;
    }
}

package com.project.basketballstore.repository;

import com.project.basketballstore.model.product.Product;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product deleteById(int id);
    Product getById(int id);
}

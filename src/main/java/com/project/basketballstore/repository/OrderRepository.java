package com.project.basketballstore.repository;

import com.project.basketballstore.model.shopping_cart.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}

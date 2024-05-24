package com.project.basketballstore.repository;

import com.project.basketballstore.model.shopping_cart.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

}

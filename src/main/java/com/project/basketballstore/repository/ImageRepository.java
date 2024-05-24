package com.project.basketballstore.repository;

import com.project.basketballstore.model.image.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel,Integer> {
}

package com.project.basketballstore.repository;

import com.project.basketballstore.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
    List<User> searchUser(String keywordU);

    @Query("select u from User u WHERE u.name=?1")
    User findByName(String name);
}

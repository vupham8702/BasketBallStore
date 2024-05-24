package com.project.basketballstore.repository;

import com.project.basketballstore.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Set<Role> findByCode(String user );
}

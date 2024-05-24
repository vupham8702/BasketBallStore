package com.project.basketballstore.service.User;

import com.project.basketballstore.model.DTO.UserDTO;
import com.project.basketballstore.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User findByEmail(String email);

    User createUser(User user);
    Optional<User> getUserDetail(int id);

    User updateUser(int id, UserDTO userDTO);

    User disableUser(int id);
}

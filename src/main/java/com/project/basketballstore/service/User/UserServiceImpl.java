package com.project.basketballstore.service.User;

import com.project.basketballstore.model.user.User;
import com.project.basketballstore.repository.RoleRepository;
import com.project.basketballstore.repository.UserRepository;
import com.project.basketballstore.model.DTO.UserDTO;
import com.project.basketballstore.service.User.UserService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User createUser(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus(true);
        user.setRoles(new HashSet<>(roleRepository.findByCode("USER")));

        return userRepository.save(user);


    }

    @Override
    public Optional<User> getUserDetail(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(int id, UserDTO userDTO) {
        Optional<User> users = userRepository.findById(id);
        if (users.isEmpty()){
             throw new RuntimeException("Người dùng không tồn tại");
        }

        User user = users.get();
        user.setName(userDTO.getName());
        user.setStatus(true);
        return userRepository.save(user);
    }

    @Override
    public User disableUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw  new RuntimeException("Người dùng không tồn tại");
        }

        User user = optionalUser.get();
        user.setStatus(false);
       return userRepository.save(user);
    }

}

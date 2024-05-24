package com.project.basketballstore.controller;


import com.project.basketballstore.model.DTO.UserDTO;
import com.project.basketballstore.model.user.User;
import com.project.basketballstore.service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@ModelAttribute User user) {
        try {
            userService.createUser(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tạo mới thất bại !!!");
        }
        return ResponseEntity.ok("Tạo mới thành công");
    }

    @GetMapping("/{id}")
    public Optional<User> userDetail(@PathVariable int id) {
        return userService.getUserDetail(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @ModelAttribute UserDTO userDTO){
        try {
            userService.updateUser(id,userDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sửa thông tin thất bại !!!");
        }

        return ResponseEntity.ok("Sửa thông tin thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> disableUser(@PathVariable int id){
        try {
            userService.disableUser(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Vô hiệu hóa người dùng thất bại !!!");
        }

        return ResponseEntity.ok("Vô hiệu hóa người dùng thành công");
    }

}

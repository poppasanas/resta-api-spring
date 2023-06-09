package com.ss.restaapispring.controller;

import com.ss.restaapispring.entity.UserEntity;
import com.ss.restaapispring.service.UserService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

   public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> findAllUsers(){
       return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findUserById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

   @PostMapping
   public UserEntity saveUser(@RequestBody UserEntity userEntity){
       return userService.saveUser(userEntity);
   }

   @PutMapping
   public UserEntity updateUser(@RequestBody UserEntity userEntity){
       return userService.updateUser(userEntity);
   }

   @DeleteMapping("/{id}")
   public void deleteUser(@PathVariable("id") Long id){
       userService.deleteEmployee(id);
    }
}

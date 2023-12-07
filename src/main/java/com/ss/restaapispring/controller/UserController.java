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

   @PostMapping
   public UserEntity saveUser(@RequestBody UserEntity userEntity){
       return userService.saveUser(userEntity);
   }

}

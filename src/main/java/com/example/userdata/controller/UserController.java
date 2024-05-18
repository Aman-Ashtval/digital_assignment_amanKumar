package com.example.userdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userdata.service.UserJpaService;
import com.example.userdata.model.User;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    private UserJpaService userJpaService;

    @GetMapping("/get-users")
    public ArrayList<User> getAllUsers(){
        return userJpaService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userJpaService.getUserById(userId);
    }

    @PostMapping("/create-user")
    public User addUser(@RequestBody User user){
        return userJpaService.addUser(user);
    }

}

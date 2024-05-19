package com.example.userdata.controller;

import com.example.userdata.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userdata.service.UserJpaService;
import com.example.userdata.model.User;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    private UserJpaService userJpaService;

    @PostMapping("/create-user")
    public User addUser(@RequestBody User user){
        return userJpaService.addUser(user);
    }

    @PostMapping("/get-users")
    public ArrayList<User> getAllUsers(@RequestBody User user){
        Integer userId = user.getUserId();
        String userMobileNumber = user.getMobileNumber();
        Manager manager = user.getManager();

        if(userId != null){
            return userJpaService.getUserById(userId);
        }else if(userMobileNumber != null){
            return userJpaService.getUserBymobileNumber(userMobileNumber);
        }else if(manager != null){
            return userJpaService.getUserByManager(manager);
        }
        return userJpaService.getAllUsers();
    }

    @PostMapping("/delete-user")
    public void deleteUser(@RequestBody User user){
        Integer  userId = user.getUserId();
        String userMobileNumber = user.getMobileNumber();

        if(userId != null){
            userJpaService.deleteUserByUserId(userId);
        }
        if(userMobileNumber != null){
            userJpaService.deleteUserByMObileNumber(userMobileNumber);
        }
    }

}

package com.example.userdata.repository;

import com.example.userdata.model.Manager;
import com.example.userdata.model.User;

import java.util.ArrayList;

public interface UserRepository {

    User addUser(User user);

    ArrayList<User> getAllUsers();

    ArrayList<User> getUserById(int userId);

    ArrayList<User> getUserBymobileNumber(String mobileNumber);

    ArrayList<User> getUserByManager(Manager manager);

    void deleteUserByUserId(int userId);

    void deleteUserByMObileNumber(String mobileNumber);


}

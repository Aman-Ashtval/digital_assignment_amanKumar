package com.example.userdata.repository;

import com.example.userdata.model.User;

import java.util.ArrayList;

public interface UserRepository {

    ArrayList<User> getAllUsers();

    User getUserById(int userId);

    User addUser(User user);
}

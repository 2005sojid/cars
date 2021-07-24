package com.example.cars.service;


import com.example.cars.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    List<User> findAll();
    User saveUser(User user);
    void deleteUserById(Long id);
}

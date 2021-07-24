package com.example.cars.service.impl;

import com.example.cars.model.User;
import com.example.cars.repo.UserRepo;
import com.example.cars.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null){
            log.warn("IN findUserById not found user by id : {}", id);
        }
        else{
            log.info("IN findUserById found user by id : {}", id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepo.findAll();
        log.info("IN findAll found : {} users", users.size());
        return users;
    }

    @Override
    public User saveUser(User user) {
        if (user == null){
            throw new RuntimeException("User is null");
        }
        return userRepo.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN deleteUserById user with id: {} is null", id);
            throw new RuntimeException("User is null");
        }
        userRepo.deleteById(id);
    }



}

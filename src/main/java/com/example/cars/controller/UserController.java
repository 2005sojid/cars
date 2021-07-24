package com.example.cars.controller;

import com.example.cars.dto.UserDto;
import com.example.cars.model.User;
import com.example.cars.repo.CarRepo;
import com.example.cars.repo.UserRepo;
import com.example.cars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public static UserRepo userRepo;
    public static CarRepo carRepo;

    @Autowired
    public UserController(UserService userService, UserRepo userRepo, CarRepo carRepo) {
        this.userService = userService;
        UserController.userRepo = userRepo;
        UserController.carRepo = carRepo;
    }

    @PostMapping("/register")
    public String createUser(UserDto user) {
        User user1 = UserDto.toUser(user);
        userService.saveUser(user1);
        return "redirect:/login";
    }



}

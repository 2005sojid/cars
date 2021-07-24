package com.example.cars.rest;

import com.example.cars.dto.UserDto;
import com.example.cars.model.User;
import com.example.cars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user:
             users) {
            userDtoList.add(UserDto.fromUser(user));
        }
        return userDtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        User user =  userService.findUserById(id);
        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }
}

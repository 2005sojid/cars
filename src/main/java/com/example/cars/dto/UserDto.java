package com.example.cars.dto;

import com.example.cars.config.SecurityConfig;
import com.example.cars.controller.UserController;
import com.example.cars.model.Car;
import com.example.cars.model.User;
import com.example.cars.repo.CarRepo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserDto {

    private static CarRepo carRepo = UserController.carRepo;

    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String dob; // Date of birth
    private String email;
    private List<Map<String, String>> cars;

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.name = user.getName();
        userDto.dob = String.valueOf(user.getDob());
        userDto.email = user.getEmail();
        userDto.cars = cars(user.getCars());
        return userDto;
    }

    public static User toUser(UserDto userDto){
        User user = new User();
        if (userDto.getId() != null){
            user.setId(userDto.id);
        }
        user.setUsername(userDto.username);
        user.setPassword(SecurityConfig.passwordEncoder().encode(userDto.password));
        user.setEmail(userDto.getEmail());
        user.setDob(LocalDate.parse(userDto.getDob()));
        user.setName(userDto.getName());
        if (userDto.cars == null){
            user.setCars(null);
        }
        else {
            user.setCars(toCars(userDto.getCars()));
        }

        return user;
    }


    public static List<Map<String, String>> cars(List<Car> cars){
        List<Map<String, String>> userCars = new ArrayList<>();
        for (Car car:
             cars) {
            Map<String, String> strings = new HashMap<>();
            strings.put("id", String.valueOf(car.getId()));
            strings.put("number", car.getNumber());
            userCars.add(strings);
        }
        return userCars;
    }

    public static List<Car> toCars(List<Map<String, String>> cars){
        List<Car> carList = new ArrayList<>();
        for (Map<String, String> stringMap : cars){
            carList.add(carRepo.findById(Long.valueOf(stringMap.get("id"))).orElse(null));
        }
        return carList;
    }
}

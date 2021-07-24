package com.example.cars.dto;

import com.example.cars.controller.UserController;
import com.example.cars.model.Car;
import com.example.cars.model.User;
import com.example.cars.repo.UserRepo;
import lombok.Data;


@Data
public class CarDto {
    private static UserRepo userRepo = UserController.userRepo;

    private Long id;
    private String number;
    private String model;
    private String producer;
    private String color;
    private Long userId;

    public static CarDto fromCar(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setNumber(car.getNumber());
        carDto.setModel(car.getModel());
        carDto.setProducer(car.getProducer());
        carDto.setColor(car.getColor());
        carDto.setUserId(car.getUser().getId());
        return carDto;
    }

    public static Car toCar(CarDto carDto, User user){
        Car car = new Car();
        if (carDto.id != null){
            car.setId(carDto.getId());
        }

        car.setNumber(carDto.getNumber());
        car.setModel(carDto.getModel());
        car.setProducer(carDto.getProducer());
        car.setColor(carDto.getColor());
        car.setUser(user);
        return car;
    }

}

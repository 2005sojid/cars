package com.example.cars.service;

import com.example.cars.model.Car;

import java.util.List;

public interface CarService{
    Car findCarById(Long id);
    List<Car> findAll();
    Car saveCar(Car car);
    void deleteCarById(Long id);
    List<Car> findAllByUserId(Long id);
}

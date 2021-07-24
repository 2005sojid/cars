package com.example.cars.service.impl;

import com.example.cars.model.Car;
import com.example.cars.repo.CarRepo;
import com.example.cars.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public Car findCarById(Long id) {
        Car car = carRepo.findById(id).orElse(null);
        if (car == null){
            log.warn("IN findCarById car with id : {} not found", id);
            throw new RuntimeException("Car not found");
        }
        log.info("IN findCarById found car by id: {} ", id);
        return car;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepo.findAll();
        log.info("IN findAll found : {} cars", cars.size());
        return cars;
    }

    @Override
    public Car saveCar(Car car) {
        if (car == null){
            throw new RuntimeException("Car is null");
        }
        return carRepo.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        Car car = carRepo.findById(id).orElse(null);
        if (car == null){
            log.warn("IN deleteCarById car with id : {} is null", id);
            throw new RuntimeException("Car is null");
        }
        log.info("IN deleteCarById found car with id: {}", id);
        carRepo.deleteById(id);
    }

    @Override
    public List<Car> findAllByUserId(Long id) {
        List<Car> cars = carRepo.findCarsByUserId(id).orElse(null);
        if (cars == null){
            log.warn("IN findAllByUserId cars with user_id : {} is null", id);
            throw new RuntimeException("Cars are null");
        }
        log.info("IN findAllByUserId found cars with user_id: {}", id);
        return cars;
    }
}

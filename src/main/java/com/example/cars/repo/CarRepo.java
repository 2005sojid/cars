package com.example.cars.repo;

import com.example.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Optional<Car> findCarByNumber(String number);
    Optional<List<Car>> findCarsByUserId(Long id);

}

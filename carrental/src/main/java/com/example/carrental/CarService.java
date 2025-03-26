package com.example.carrental;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final List<Car> cars;

    public CarService() {
        // Initialisation avec les mêmes données que CarController
        this.cars = List.of(
                new Car("11AA22", "Ferrari", 100),
                new Car("22BB33", "Toyota", 50)
        );
    }

    // Nouvelle méthode pour ton service
    public List<Car> getAvailableCars() {
        return cars.stream()
                .filter(car -> !car.isRented())
                .toList();
    }
}
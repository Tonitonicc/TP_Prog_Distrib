package com.example.carrental;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
        initializeDatabase();
    }

    private void initializeDatabase() {
        if (carRepository.count() == 0) {
            carRepository.save(new Car("11AA22", "Ferrari", 100));
            carRepository.save(new Car("22BB33", "Toyota", 50));
            logger.info("Database initialized with sample cars");
        }
    }

    // Health Check pour Kubernetes
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/cars/newcarservice")
    public String newCarService() {
        return "New car service is active!";
    }

    @GetMapping("/cars")
    public List<Car> listOfCars() {
        return carRepository.findByRentedFalse();
    }

    @GetMapping("/cars/{plateNumber}")
    public Car aCar(@PathVariable String plateNumber) {
        return carRepository.findById(plateNumber).orElse(null);
    }

    @PutMapping("/cars/{plateNumber}")
    public Car rentOrGetBack(@PathVariable String plateNumber,
                             @RequestParam boolean rent) {
        return carRepository.findById(plateNumber)
                .map(car -> {
                    car.setRented(rent);
                    return carRepository.save(car);
                })
                .orElse(null);
    }

    public static class RentalDates {
        private String begin;
        private String end;

        public String getBegin() {
            return begin;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
}
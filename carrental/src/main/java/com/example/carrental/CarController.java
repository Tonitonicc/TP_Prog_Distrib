package com.example.carrental;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private List<Car> cars = new ArrayList<>();

    public CarController() {
        // Exemple de voitures initiales
        cars.add(new Car("11AA22", "Ferrari", 100));
        cars.add(new Car("22BB33", "Toyota", 50));
    }

    // 1. Nouveau endpoint pour le service "newcarservice"
    @GetMapping("/newcarservice")
    public String newCarService() {
        return "New car service is active!"; // Message de test
    }

    // 2. Lister les voitures non louées (existant)
    @GetMapping
    public List<Car> listOfCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isRented()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    // 3. Obtenir une voiture par sa plaque (existant)
    @GetMapping("/{plateNumber}")
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return car;
            }
        }
        return null;
    }

    // 4. Louer/rendre une voiture (existant)
    @PutMapping("/{plateNumber}")
    public Car rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent") boolean rent) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                car.setRented(rent);
                return car;
            }
        }
        return null;
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

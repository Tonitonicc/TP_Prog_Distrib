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

    // Lister les voitures non louées
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

    // Obtenir une voiture spécifique par son numéro de plaque
    @GetMapping("/{plateNumber}")
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return car;
            }
        }
        return null; // Voiture non trouvée
    }

    // Louer ou rendre une voiture
    @PutMapping("/{plateNumber}")
    public Car rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent") boolean rent) {

        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                if (rent) {
                    // Louer la voiture
                    car.setRented(true);
                } else {
                    // Rendre la voiture
                    car.setRented(false);
                }
                return car; // Retourne la voiture mise à jour
            }
        }
        return null; // Si la voiture n'est pas trouvée
    }

    // Classe interne pour les dates de location (si nécessaire pour un futur ajout)
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

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CarMaintenance;
import com.example.demo.repository.CarMaintenanceRepository;

@Controller
@RestController
@RequestMapping("/car-maintenance")
public class CarMaintenanceController {

    @Autowired
    private CarMaintenanceRepository carMaintenanceRepository;

    // Get all car maintenance records
    @GetMapping("/all")
    public List<CarMaintenance> getAllCarMaintenances() {
        return carMaintenanceRepository.findAll();
    }

    // Get car maintenance by ID
    @GetMapping("/{id}")
    public ResponseEntity<CarMaintenance> getCarMaintenanceById(@PathVariable int id) {
        Optional<CarMaintenance> carMaintenance = carMaintenanceRepository.findById(id);
        if (carMaintenance.isPresent()) {
            return ResponseEntity.ok(carMaintenance.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create new car maintenance record
    @PostMapping("/create")
    public ResponseEntity<CarMaintenance> createCarMaintenance(@RequestBody CarMaintenance carMaintenance) {
        try {
            CarMaintenance savedCarMaintenance = carMaintenanceRepository.save(carMaintenance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCarMaintenance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update car maintenance record
    @PutMapping("/{id}")
    public ResponseEntity<CarMaintenance> updateCarMaintenance(@PathVariable int id, @RequestBody CarMaintenance carMaintenanceDetails) {
        Optional<CarMaintenance> carMaintenanceOptional = carMaintenanceRepository.findById(id);

        if (carMaintenanceOptional.isPresent()) {
            CarMaintenance carMaintenance = carMaintenanceOptional.get();
            carMaintenance.setMaintenance_date(carMaintenanceDetails.getMaintenance_date());
            carMaintenance.setStatus(carMaintenanceDetails.getStatus());
            carMaintenance.setDescription(carMaintenanceDetails.getDescription());
            carMaintenance.setCost(carMaintenanceDetails.getCost());
            carMaintenance.setCar(carMaintenanceDetails.getCar());
            carMaintenanceRepository.save(carMaintenance);
            return ResponseEntity.ok(carMaintenance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete car maintenance record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarMaintenance(@PathVariable int id) {
        Optional<CarMaintenance> carMaintenanceOptional = carMaintenanceRepository.findById(id);

        if (carMaintenanceOptional.isPresent()) {
            carMaintenanceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

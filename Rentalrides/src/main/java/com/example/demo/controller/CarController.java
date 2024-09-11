package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarRepository carRepository;

	// Get all cars
	@GetMapping("/all")
	public ResponseEntity<List<Car>> getAllCars() {
		List<Car> cars = carRepository.findAll();
		return ResponseEntity.ok(cars);
	}

	// Get car by ID
	@GetMapping("/{car_number}")
	public ResponseEntity<Car> getCarById(@PathVariable String car_number) {
		Optional<Car> car = carRepository.findById(car_number);
		if (car.isPresent()) {
			return ResponseEntity.ok(car.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Create new car
	@PostMapping("/createCar")
	public ResponseEntity<Car> createCar(@RequestBody Car car) {
		try {
			Car savedCar = carRepository.save(car);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Create multiple cars
	@PostMapping("/list")
	public ResponseEntity<List<Car>> createCars(@RequestBody List<Car> cars) {
		try {
			List<Car> savedCars = carRepository.saveAll(cars);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCars);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Update car
	@PutMapping("/update/{car_number}")
	public ResponseEntity<Car> updateCar(@PathVariable String car_number, @RequestBody Car carDetails) {
		Optional<Car> carOptional = carRepository.findById(car_number);

		if (carOptional.isPresent()) {
			Car car = carOptional.get();
			car.setModel(carDetails.getModel());
			car.setManufacturing_year(carDetails.getManufacturing_year());
			car.setMileage(carDetails.getMileage());
			car.setLocation_status(carDetails.getLocation_status());
			car.setBookings(carDetails.getBookings());
			car.setInsurances(carDetails.getInsurances());
			car.setCarMaintenances(carDetails.getCarMaintenances());
			carRepository.save(car);
			return ResponseEntity.ok(car);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Delete car by ID
	@DeleteMapping("/delete/{car_number}")
	public ResponseEntity<Void> deleteCar(@PathVariable String car_number) {
		Optional<Car> carOptional = carRepository.findById(car_number);

		if (carOptional.isPresent()) {
			carRepository.deleteById(car_number);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}

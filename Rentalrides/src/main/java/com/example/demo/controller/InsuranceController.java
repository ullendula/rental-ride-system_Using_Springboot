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

import com.example.demo.model.Insurance;
import com.example.demo.repository.InsuranceRepository;

@Controller
@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    // Get all insurances
    @GetMapping("/all")
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    // Get insurance by ID
    @GetMapping("/id")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int id) {
        Optional<Insurance> insurance = insuranceRepository.findById(id);
        if (insurance.isPresent()) {
            return ResponseEntity.ok(insurance.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create new insurance
    @PostMapping("/create")
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
        try {
            Insurance savedInsurance = insuranceRepository.save(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update insurance
    @PutMapping("/update")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable int id, @RequestBody Insurance insuranceDetails) {
        Optional<Insurance> insuranceOptional = insuranceRepository.findById(id);

        if (insuranceOptional.isPresent()) {
            Insurance insurance = insuranceOptional.get();
            insurance.setPolicy_number(insuranceDetails.getPolicy_number());
            insurance.setCoverage_amount(insuranceDetails.getCoverage_amount());
            insurance.setStart_date(insuranceDetails.getStart_date());
            insurance.setEnd_date(insuranceDetails.getEnd_date());
            insurance.setCar(insuranceDetails.getCar());
            insuranceRepository.save(insurance);
            return ResponseEntity.ok(insurance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete insurance by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable int id) {
        Optional<Insurance> insuranceOptional = insuranceRepository.findById(id);

        if (insuranceOptional.isPresent()) {
            insuranceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

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

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;

@Controller
@RestController
@RequestMapping("/admins")
public class AdminController {
	
	 @Autowired
	    private AdminRepository adminRepository;

	    // Get all admins
	    @GetMapping("/all")
	    public List<Admin> getAllAdmins() {
	        return adminRepository.findAll();
	    }

	    // Get admin by ID
	    @GetMapping("/admin_id")
	    public ResponseEntity<Admin> getAdminById(@PathVariable Integer admin_id) {
	        Optional<Admin> admin = adminRepository.findById(admin_id);
	        if (admin.isPresent()) {
	            return ResponseEntity.ok(admin.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    // Create new admin
	    @PostMapping("/create")
	    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
	        try {
	            Admin savedAdmin = adminRepository.save(admin);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }

	    // Update admin
	    @PutMapping("/update/{admin_id}")
	    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer admin_id, @RequestBody Admin adminDetails) {
	        Optional<Admin> adminOptional = adminRepository.findById(admin_id);

	        if (adminOptional.isPresent()) {
	            Admin admin = adminOptional.get();
	            admin.setName(adminDetails.getName());
	            admin.setEmail(adminDetails.getEmail());
	            admin.setPassword(adminDetails.getPassword());
	            admin.setUsers(adminDetails.getUsers());
	            adminRepository.save(admin);
	            return ResponseEntity.ok(admin);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    // Delete admin by ID
	    @DeleteMapping("/delete/{admin_id}")
	    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer admin_id) {
	        Optional<Admin> adminOptional = adminRepository.findById(admin_id);

	        if (adminOptional.isPresent()) {
	            adminRepository.deleteById(admin_id);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

}

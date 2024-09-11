package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    // Handle user login
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody Login loginDetails) {
        Optional<Login> user = Optional.ofNullable(loginRepository.findByEmail(loginDetails.getEmail()));

        if (user.isPresent() && user.get().getPassword().equals(loginDetails.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Login> registerUser(@RequestBody Login login) {
        try {
            // Ensure that the email is not already registered
            if (loginRepository.findByEmail(login.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            Login savedLogin = loginRepository.save(login);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLogin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update user credentials
    @PutMapping("/update")
    public ResponseEntity<Login> updateUser(@PathVariable Long id, @RequestBody Login loginDetails) {
        Optional<Login> loginOptional = loginRepository.findById(id);

        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            login.setEmail(loginDetails.getEmail());
            login.setPassword(loginDetails.getPassword());
            loginRepository.save(login);
            return ResponseEntity.ok(login);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a user by ID
    @DeleteMapping("/delete	")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Login> loginOptional = loginRepository.findById(id);

        if (loginOptional.isPresent()) {
            loginRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

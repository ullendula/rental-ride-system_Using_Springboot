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

import com.example.demo.model.Register;
import com.example.demo.repository.RegisterRepository;

@Controller
@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    // Get all registers
    @GetMapping("/All")
    public List<Register> getAllRegisters() {
        return registerRepository.findAll();
    }

    // Get a specific register by ID
    @GetMapping("/id")
    public ResponseEntity<Register> getRegisterById(@PathVariable Integer id) {
        Optional<Register> register = registerRepository.findById(id);
        return register.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new register
    @PostMapping("/create")
    public ResponseEntity<Register> createRegister(@RequestBody Register register) {
        try {
            Register savedRegister = registerRepository.save(register);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRegister);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update an existing register
    @PutMapping("/update/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable Integer id, @RequestBody Register registerDetails) {
        Optional<Register> registerOptional = registerRepository.findById(id);

        if (registerOptional.isPresent()) {
            Register register = registerOptional.get();
            register.setUsername(registerDetails.getUsername());
            register.setEmail(registerDetails.getEmail());
            register.setPassword(registerDetails.getPassword());
            registerRepository.save(register);
            return ResponseEntity.ok(register);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a register
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegister(@PathVariable Integer id) {
        Optional<Register> registerOptional = registerRepository.findById(id);

        if (registerOptional.isPresent()) {
            registerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

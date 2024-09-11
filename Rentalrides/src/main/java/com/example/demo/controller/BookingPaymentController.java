package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.BookingPayment;
import com.example.demo.repository.BookingPaymentRepository;

@RestController
@RequestMapping("/bookingPayments")
public class BookingPaymentController {

    @Autowired
    private BookingPaymentRepository bookingPaymentRepository;

    // Get all BookingPayments
    @GetMapping("/All")
    public List<BookingPayment> getAllBookingPayments() {
        return bookingPaymentRepository.findAll();
    }

    // Get BookingPayment by ID
    @GetMapping("/bp/{id}")
    public ResponseEntity<BookingPayment> getBookingPaymentById(@PathVariable Long id) {
        Optional<BookingPayment> bookingPayment = bookingPaymentRepository.findById(id);
        if (bookingPayment.isPresent()) {
            return ResponseEntity.ok().body(bookingPayment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new BookingPayment
    @PostMapping
    public BookingPayment createBookingPayment(@RequestBody BookingPayment bookingPayment) {
        return bookingPaymentRepository.save(bookingPayment);
    }

    // Update BookingPayment
    @PutMapping("/update/{id}")
    public ResponseEntity<BookingPayment> updateBookingPayment(@PathVariable Long id, @RequestBody BookingPayment bookingPaymentDetails) {
        Optional<BookingPayment> optionalBookingPayment = bookingPaymentRepository.findById(id);
        if (optionalBookingPayment.isPresent()) {
            BookingPayment bookingPayment = optionalBookingPayment.get();
            bookingPayment.setStartDate(bookingPaymentDetails.getStartDate());
            bookingPayment.setEndDate(bookingPaymentDetails.getEndDate());
            bookingPayment.setTotalCost(bookingPaymentDetails.getTotalCost());
            bookingPayment.setCurrentBookingStatus(bookingPaymentDetails.getCurrentBookingStatus());
            bookingPayment.setAmount(bookingPaymentDetails.getAmount());
            bookingPayment.setPaymentDate(bookingPaymentDetails.getPaymentDate());
            bookingPayment.setPaymentMethod(bookingPaymentDetails.getPaymentMethod());
            bookingPayment.setPaymentStatus(bookingPaymentDetails.getPaymentStatus());
            bookingPayment.setUser(bookingPaymentDetails.getUser());
            bookingPayment.setCar(bookingPaymentDetails.getCar());

            final BookingPayment updatedBookingPayment = bookingPaymentRepository.save(bookingPayment);
            return ResponseEntity.ok(updatedBookingPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete BookingPayment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookingPayment(@PathVariable Long id) {
        Optional<BookingPayment> optionalBookingPayment = bookingPaymentRepository.findById(id);
        if (optionalBookingPayment.isPresent()) {
            bookingPaymentRepository.delete(optionalBookingPayment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

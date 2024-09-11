package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BookingPayment;


public interface BookingPaymentRepository extends JpaRepository<BookingPayment, Long> {

	List<BookingPayment> findAll();

	Optional<BookingPayment> findById(Long id);

	BookingPayment save(BookingPayment bookingPayment);

	void delete(BookingPayment bookingPayment);

}

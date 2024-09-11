package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CarMaintenance;

public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Integer>{

	List<CarMaintenance> findAll();

	

	
	
}

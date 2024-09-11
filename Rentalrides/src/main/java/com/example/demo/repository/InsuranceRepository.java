package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer>{

}

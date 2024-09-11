package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CarMaintenance")
public class CarMaintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maintenance_id;
	@Column(name="maintenance_date")
	private Date maintenance_date;
	@Column(name="Status")
	private String status;
	@Column(name="description")
	private String description;
	@Column(name="Cost")
	private double cost;

	@ManyToOne
	@JoinColumn(name = "car_number")
	private Car car;

	public CarMaintenance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarMaintenance(int maintenance_id, Date maintenance_date, String status, String description, double cost,
			Car car) {
		super();
		this.maintenance_id = maintenance_id;
		this.maintenance_date = maintenance_date;
		this.status = status;
		this.description = description;
		this.cost = cost;
		this.car = car;
	}

	@Override
	public String toString() {
		return "CarMaintenance [maintenance_id=" + maintenance_id + ", maintenance_date=" + maintenance_date
				+ ", status=" + status + ", description=" + description + ", cost=" + cost + ", car=" + car + "]";
	}

	public int getMaintenance_id() {
		return maintenance_id;
	}

	public void setMaintenance_id(int maintenance_id) {
		this.maintenance_id = maintenance_id;
	}

	public Date getMaintenance_date() {
		return maintenance_date;
	}

	public void setMaintenance_date(Date maintenance_date) {
		this.maintenance_date = maintenance_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	
	

}

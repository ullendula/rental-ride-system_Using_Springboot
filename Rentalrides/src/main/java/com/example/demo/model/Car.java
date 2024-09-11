package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Car")
public class Car {
	@Column(name="Car_Number")
	@Id
	private String car_number;
	@Column(name="Model")
	private String model;
	@Column(name="Manufacturing_year")
	private int manufacturing_year;
	@Column(name="mileage")
	private int mileage;
	@Column(name="location_status")
	private String location_status;

	@OneToMany(mappedBy = "car")
	private List<BookingPayment> bookings;

	@OneToMany(mappedBy = "car")
	private List<Insurance> insurances;

	@OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(String car_number, String model, int manufacturing_year, int mileage, String location_status,
			List<BookingPayment> bookings, List<Insurance> insurances, List<CarMaintenance> carMaintenances) {
		super();
		this.car_number = car_number;
		this.model = model;
		this.manufacturing_year = manufacturing_year;
		this.mileage = mileage;
		this.location_status = location_status;
		this.bookings = bookings;
		this.insurances = insurances;
		this.carMaintenances = carMaintenances;
	}

	@Override
	public String toString() {
		return "Car [car_number=" + car_number + ", model=" + model + ", manufacturing_year=" + manufacturing_year
				+ ", mileage=" + mileage + ", location_status=" + location_status + ", bookings=" + bookings
				+ ", insurances=" + insurances + ", carMaintenances=" + carMaintenances + "]";
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getManufacturing_year() {
		return manufacturing_year;
	}

	public void setManufacturing_year(int manufacturing_year) {
		this.manufacturing_year = manufacturing_year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getLocation_status() {
		return location_status;
	}

	public void setLocation_status(String location_status) {
		this.location_status = location_status;
	}

	public List<BookingPayment> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingPayment> bookings) {
		this.bookings = bookings;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public List<CarMaintenance> getCarMaintenances() {
		return carMaintenances;
	}

	public void setCarMaintenances(List<CarMaintenance> carMaintenances) {
		this.carMaintenances = carMaintenances;
	}
	
	

	

}

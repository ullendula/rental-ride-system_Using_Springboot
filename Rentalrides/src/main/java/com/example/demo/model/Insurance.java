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
@Table(name = "Insurance")
public class Insurance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insurance_id;
	@Column(name="Policy_Number")
	private String policy_number;
	@Column(name="Coverage_Amount")
	private double coverage_amount;
	@Column(name="Start_Date")
	private Date start_date;
	@Column(name="End_date")
	private Date end_date;

	@ManyToOne
	@JoinColumn(name = "car_number")
	private Car car;

	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Insurance(int insurance_id, String policy_number, double coverage_amount, Date start_date, Date end_date,
			Car car) {
		super();
		this.insurance_id = insurance_id;
		this.policy_number = policy_number;
		this.coverage_amount = coverage_amount;
		this.start_date = start_date;
		this.end_date = end_date;
		this.car = car;
	}

	@Override
	public String toString() {
		return "Insurance [insurance_id=" + insurance_id + ", policy_number=" + policy_number + ", coverage_amount="
				+ coverage_amount + ", start_date=" + start_date + ", end_date=" + end_date + ", car=" + car + "]";
	}

	public int getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}

	public String getPolicy_number() {
		return policy_number;
	}

	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}

	public double getCoverage_amount() {
		return coverage_amount;
	}

	public void setCoverage_amount(double coverage_amount) {
		this.coverage_amount = coverage_amount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
	

}

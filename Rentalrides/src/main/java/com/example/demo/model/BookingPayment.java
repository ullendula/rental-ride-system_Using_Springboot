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
@Table(name = "BookingPayment")
public class BookingPayment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    
    @Column(name="StartDate")
    private Date startDate;
    
    @Column(name="EndDate")
    private Date endDate;
    
    @Column(name="TotalCost")
    private double totalCost;
    
    @Column(name="CurrentBookingStatus")
    private String currentBookingStatus;

    @Column(name = "Amount")
    private double amount;
    
    @Column(name = "PaymentDate")
    private Date paymentDate;
    
    @Column(name = "PaymentMethod")
    private String paymentMethod;
    
    @Column(name = "PaymentStatus")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "carNumber")
    private Car car;
    
    public BookingPayment() {
        super();
    }

    public BookingPayment(Long bookingId, Date startDate, Date endDate, double totalCost, String currentBookingStatus,
                          double amount, Date paymentDate, String paymentMethod, String paymentStatus,
                          User user, Car car) {
        super();
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.currentBookingStatus = currentBookingStatus;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.user = user;
        this.car = car;
    }

    @Override
    public String toString() {
        return "BookingPayment [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate 
            + ", totalCost=" + totalCost + ", currentBookingStatus=" + currentBookingStatus 
            + ", amount=" + amount + ", paymentDate=" + paymentDate + ", paymentMethod=" + paymentMethod 
            + ", paymentStatus=" + paymentStatus + ", user=" + user + ", car=" + car + "]";
    }

    // Getters and Setters

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrentBookingStatus() {
        return currentBookingStatus;
    }

    public void setCurrentBookingStatus(String currentBookingStatus) {
        this.currentBookingStatus = currentBookingStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}


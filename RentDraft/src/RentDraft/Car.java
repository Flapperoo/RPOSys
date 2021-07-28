/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentDraft;

import java.util.Date;

/**
 *
 * @author paolo
 */
public class Car {
    private String model;
    private String brand;
    private String description;
    private String licensePlate;
    private double price;
    private Date dateRented;
    private Date rentUntil;
    
    Car (String model, String brand, String description, String licensePlate, double price, Date dateRented, Date rentUntil){
    
        this.model = model;
        this.brand = brand;
        this.description = description;
        this.licensePlate = licensePlate;
        this.price = price;
        this.dateRented = dateRented;
        this.rentUntil = rentUntil;
        
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getRentUntil() {
        return rentUntil;
    }

    public void setRentUntil(Date rentUntil) {
        this.rentUntil = rentUntil;
    }
    
    public boolean availabilityCheck(Date dateRented, Date rentUntil){
        
        if (dateRented.compareTo(rentUntil) >= 0) {
            return true;
        }
        else
            return false;
        
        /* if number u comparing to is greater, you get negative integer, 
        if your object is larger it will be positive, and if equal it will be zero.*/
    }
    
    public void rentCar(Date dateRented, Date rentUntil){
        setDateRented(dateRented);
        setRentUntil(rentUntil);
        
    }
    
    /* or we can remove the set shiz, i only added setter cuz what if someone wanna do it individually and not obth
    
    public void rentCar(Date dateRented, Date rentUntil){
        this.dateRented = dateRented;
        this.rentUntil = rentUntil;
        
    }
    
    */
    
}

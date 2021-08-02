/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentFinal;

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
    private String dateRented;
    private String rentUntil;
    
    Car (String licensePlate, String brand, String model, double price, String description,  String dateRented, String rentUntil){
    
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.description = description;
        this.dateRented = dateRented;
        this.rentUntil = rentUntil;  
    }

    Car(String licensePlate, String brand, String model, double price, String descrip) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.description = descrip;
        this.price = price;
        this.model = model;
    }

    public String getModel() {
        return model;
    }


    public String getBrand() {
        return brand;
    }


    public String getDescription() {
        return description;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public double getPrice() {
        return price;
    }


    public String getDateRented() {
        return dateRented;
    }


    public String getRentUntil() {
        return rentUntil;
    }

}

package com.nicolasperussi.burger.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "couriers")
public class Courier extends User {
    private String licensePlate;
    private Boolean isAvailable;

    public Courier() {
    }

    public Courier(String name, String email, String password, String licensePlate, Boolean isAvailable) {
        super(name, email, password);
        this.licensePlate = licensePlate;
        this.isAvailable = isAvailable;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}

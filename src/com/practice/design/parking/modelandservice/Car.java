package com.practice.design.parking.modelandservice;

public class Car implements Vehicle {

    private String registrationNumber;
    private String color;

    public Car(final String registrationNumber, final String color) {
        this.color = color;
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}

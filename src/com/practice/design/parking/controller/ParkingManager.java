package com.practice.design.parking.controller;

import java.util.List;

import com.practice.design.parking.modelandservice.ParkingSystem;

/*
 * This class acts as a controller, it takes the instructions from user interface and forwards it to the
 * Swiggy Parking Facade, and gives the result back to the View.
 */
public class ParkingManager {

    private ParkingSystem swiggyParking;

    public ParkingManager(final ParkingSystem swiggyParking) {
        this.swiggyParking = swiggyParking;
    }

    public boolean createSwiggyParking(final int slots) {
        swiggyParking.setParkingSlots(slots);
        return true;
    }

    public int parkVehicle(final String registrationNumber, final String color) {
        return swiggyParking.allocate(registrationNumber, color);
    }

    public int unparkVehicle(final int slotNumber) {
        return swiggyParking.unallocate(slotNumber);
    }

    public String getParkingSlotsInfo() {
        return swiggyParking.getParkingSlotDetails();
    }

    public List<Integer> getAllSlotsWithVehicleColor(final String color) {
        return swiggyParking.getSlotsWithVehicleColor(color);
    }

    public Integer getSlotWithVehicleRegistration(final String registrationNumber) {
        return swiggyParking.getSlotWithVehicleRegistrationNumber(registrationNumber);
    }

    public List<String> getVehicleRegistrationsWithColor (final String color) {
        return swiggyParking.getVehicleRegistrationNumbersWithColor(color);
    }

}

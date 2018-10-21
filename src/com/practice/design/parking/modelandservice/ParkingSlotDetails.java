package com.practice.design.parking.modelandservice;

import java.util.Objects;

public class ParkingSlotDetails {

    private int slotNumber;
    private Vehicle vehicle;

    public ParkingSlotDetails(final int slotNumber, final Vehicle vehicle) {
        this.slotNumber = slotNumber;
        this.vehicle = vehicle;
    }

    public int getSlotNumber() {
        return this.slotNumber;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ParkingSlotDetails that = (ParkingSlotDetails)o;
        return slotNumber == that.slotNumber &&
                Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, vehicle);
    }
}

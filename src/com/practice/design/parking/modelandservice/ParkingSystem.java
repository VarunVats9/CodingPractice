package com.practice.design.parking.modelandservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
 * This class acts as a facade over all the models, it takes the input form controller,
 * gives the output back to the controller.
 */
public class ParkingSystem {

    private int totalParkingSlots;
    private Queue<Integer> slotTokens;

    // color ---> List<slotDetails> MAP.
    private Map<String, List<ParkingSlotDetails>> colorToParkingSlot;

    // vehicleNumber ---> slot MAP.
    private Map<String, Integer> vehicleNumberToSlot;

    // slot ---> slotDetails MAP.
    private Map<Integer, ParkingSlotDetails> slotToParkingSlot;

    public ParkingSystem() {
        slotTokens = new PriorityQueue<>();
        colorToParkingSlot = new HashMap<>();
        vehicleNumberToSlot = new HashMap<>();
        slotToParkingSlot = new HashMap<>();
    }

    public void setParkingSlots(final int totalParkingSlots) {
        this.totalParkingSlots = totalParkingSlots;
        for (int i = 1; i <= totalParkingSlots; i++) {
            slotTokens.add(i);
        }
    }

    public int getTotalParkingSlots() {
        return this.totalParkingSlots;
    }

    public int allocate(final String registrationNumber, final String color) {

        // No tokens left, parking is full.
        if (slotTokens.isEmpty()) {
            return -1;
        }

        // Vehicle already been parked.
        if (vehicleNumberToSlot.containsKey(registrationNumber)) {
            return -2;
        }

        // Find the nearest slot from the entry.
        final int nearestSlot = slotTokens.poll();
        final Vehicle vehicle = new Car(registrationNumber, color);
        ParkingSlotDetails parkingSlot = new ParkingSlotDetails(nearestSlot, vehicle);

        // Populate vehicleNumber ---> slot MAP.
        vehicleNumberToSlot.put(vehicle.getRegistrationNumber(), nearestSlot);

        // Populate slot ---> slotDetails MAP.
        slotToParkingSlot.put(nearestSlot, new ParkingSlotDetails(nearestSlot, vehicle));

        // Populate color ---> List<slotDetails> MAP.
        if (!colorToParkingSlot.containsKey(color)) {
            colorToParkingSlot.put(color, new ArrayList<>());
        }
        colorToParkingSlot.get(color).add(parkingSlot);

        return nearestSlot;
    }

    public int unallocate(final int slotNumber) {

        // Trying to unallocate empty slot.
        final boolean alreadyThere = slotTokens.remove(slotNumber);

        // Slot is empty.
        if (alreadyThere) {
            return -1;
        }

        // This slot doesn't exist.
        if (slotNumber > totalParkingSlots || slotNumber < 0) {
            return -2;
        }

        // Update slotTokens
        slotTokens.add(slotNumber);

        final ParkingSlotDetails parkingSlot = slotToParkingSlot.get(slotNumber);

        final String color = parkingSlot.getVehicle().getColor();
        final String vehicleNumber = parkingSlot.getVehicle().getRegistrationNumber();

        // Update color ---> List<slotDetails> MAP.
        colorToParkingSlot.get(color).remove(parkingSlot);

        // Update vehicleNumber ---> slot MAP.
        vehicleNumberToSlot.remove(vehicleNumber);

        // Update vehicleNumber ---> slot MAP.
        slotToParkingSlot.remove(slotNumber);

        return 1;
    }

    public String getParkingSlotDetails() {

        final String format = "%1$-10s|%2$-20s|%3$-15s|%4$-10s|\n";
        final String header = String.format(format, "S.no.", "Registration Number", "Slot Number", "Color");
        final StringBuilder stringBuilder = new StringBuilder(header);

        final AtomicInteger counter = new AtomicInteger(0);

        colorToParkingSlot.forEach((k, v) -> {
            v.forEach(vehicle -> {
                final String line = String.format(format, counter.incrementAndGet(), vehicle.getVehicle().getRegistrationNumber(),
                        vehicle.getSlotNumber(), vehicle.getVehicle().getColor());
                stringBuilder.append(line);
            });

        });

        return stringBuilder.toString();
    }

    public List<Integer> getSlotsWithVehicleColor(final String color) {
        final List<ParkingSlotDetails> parkingSlots = colorToParkingSlot.get(color);
        if (Objects.isNull(parkingSlots)) {
            return Collections.EMPTY_LIST;
        }
        return parkingSlots.stream()
                .map(ParkingSlotDetails::getSlotNumber)
                .collect(Collectors.toList());
    }

    public Integer getSlotWithVehicleRegistrationNumber(final String registrationNumber) {
        return vehicleNumberToSlot.get(registrationNumber);
    }

    public List<String> getVehicleRegistrationNumbersWithColor(final String color) {
        return colorToParkingSlot.get(color).stream()
                .map(p -> p.getVehicle().getRegistrationNumber())
                .collect(Collectors.toList());

    }

}

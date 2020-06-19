package com.practice.design.parking.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.practice.design.parking.controller.ParkingManager;
import com.practice.design.parking.modelandservice.ParkingSystem;

/*
 * This class acts as a View and com.practice.cses.introductoryproblems.Main class, it takes the input from user, gives it to the controller.
 * And when it gets the result from controller, it shows it to the user.
 */
public class UserInterface {

    private static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR = "registration_numbers_for_cars_with_color";
    private static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";
    private static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOR = "slot_numbers_for_cars_with_color";
    private static final String STOP = "stop";
    private static final String PARK = "park";
    private static final String LEAVE = "leave";
    private static final String STATUS = "status";
    private static final String WELCOME_MESSAGE = "Hi, This is Parking Space!!! \nPlease enter the number of slots you need in the parking.\n";

    public static void main(String[] args) throws FileNotFoundException {

        ParkingSystem swiggyParking = new ParkingSystem();
        ParkingManager parkingManager = new ParkingManager(swiggyParking);

        File file = new File("/Users/vvats/gitRepo/SwiggyParkingSystem/src/com/swiggy/parking/test");
        Scanner in = new Scanner(file);

        System.out.println(WELCOME_MESSAGE);

        Integer slots = in.nextInt();
        parkingManager.createSwiggyParking(slots);

        System.out.println("Created parking slots with " + slots + " slots.\n");
        String nextLine1 = in.nextLine();

        while (true) {
            String nextLine = in.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(nextLine, " ");
            String command = stringTokenizer.nextToken();

            if (STOP.equals(command)) {
                break;
            }

            switch (command) {
                case PARK:
                    final int nearestSlot = parkingManager.parkVehicle(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                    if (nearestSlot == -1) {
                        System.out.println("Sorry, parking lot is full.");
                        break;
                    } else if (nearestSlot == -2) {
                        System.out.println("This car has already been parked.");
                        break;
                    }
                    System.out.println("Allocated slot number : [" + nearestSlot + "]");
                    break;
                case LEAVE:
                    final int unAllocateSlotNumber = Integer.valueOf(stringTokenizer.nextToken());
                    final int unparked = parkingManager.unparkVehicle(unAllocateSlotNumber);
                    if (unparked == -1) {
                        System.out.println("Sorry, cannot unpark as the slot is empty.");
                        break;
                    } else if (unparked == -2) {
                        System.out.println("Sorry, this slot number is wrong.");
                        break;
                    }
                    System.out.println("Slot number [" + unAllocateSlotNumber + "] is free.");
                    break;
                case STATUS:
                    System.out.println(parkingManager.getParkingSlotsInfo());
                    break;
                case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR:
                    final String vehicleColor = stringTokenizer.nextToken();
                    System.out.println("Vehicles with color " + vehicleColor + " : " + parkingManager.getVehicleRegistrationsWithColor(vehicleColor));
                    break;
                case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                    System.out.println("Vehicle is parked at slot number : [" + parkingManager.getSlotWithVehicleRegistration(stringTokenizer.nextToken()) + "]");
                    break;
                case SLOT_NUMBERS_FOR_CARS_WITH_COLOR:
                    final String color = stringTokenizer.nextToken();
                    System.out.println("Vehicles with color " + color + " : " + parkingManager.getAllSlotsWithVehicleColor(color));
                    break;
                default:
                    break;
            }
        }
    }
}

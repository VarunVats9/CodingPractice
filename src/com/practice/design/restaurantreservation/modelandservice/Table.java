package com.practice.design.restaurantreservation.modelandservice;

public class Table {

    private int tableId;
    private int numOfSeats;

    public Table(final int tableId, final int numOfSeats) {
        this.tableId = tableId;
        this.numOfSeats = numOfSeats;
    }
}

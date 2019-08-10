package com.practice.design.restaurantreservation;


import java.util.HashMap;
import java.util.Map;

// Took inspiration from this code.
// https://stackoverflow.com/questions/13466954/how-to-store-a-java-object-with-variable-number-of-attributes
public class Table {

    private int tableId;
    private int numOfSeats;

    // This way all the dynamic attributes goes in one JSON column, but according to below answer
    // This doesn't scale up, if you are required to make query on specific attributes.
    // https://stackoverflow.com/questions/1444462/how-to-store-data-with-dynamic-number-of-attributes-in-a-database

    // In our case, I think we won't be doing that.
    private Map<Attributes, Object> tableAttributes;

    public Map<Attributes, Object> getTableAttributes() {
        return this.tableAttributes;
    }

    public void setTableAttributes(final Map<Attributes, Object> tableAttributes) {
        this.tableAttributes = tableAttributes;
    }

    public Table(final int tableId, final int numOfSeats) {
        this.tableId = tableId;
        this.numOfSeats = numOfSeats;
        tableAttributes = new HashMap<>();
    }
}

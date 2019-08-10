package com.practice.design.restaurantreservation;

import java.util.List;

public class Restaurant {

    private int restaurantId;
    private String name;
    private List<Table> tables;
    private Address address;
    private RestaurantDateCalendar restaurantDateCalendar;


    public Restaurant(final int restaurantId, final String name, final List<Table> tables, final Address address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.tables = tables;
        this.address = address;
    }

    public void setNewCalendarForTheRestaurant(final DayCalendar.CalendarType type) {
        this.restaurantDateCalendar = new RestaurantDateCalendar(this, type);
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public List<Table> search(String query) {
        return null;
    }

}

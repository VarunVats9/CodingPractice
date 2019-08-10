package com.practice.design.restaurantreservation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantDateCalendar {

    private Restaurant restaurant;
    private Map<LocalDate, RestaurantDayCalendar> restaurantSchedule;
    private DayCalendar.CalendarType type;

    public RestaurantDateCalendar(final Restaurant restaurant, final DayCalendar.CalendarType type) {
        this.restaurant = restaurant;
        this.restaurantSchedule = new HashMap<>();
        this.type = type;
        initializeSchedule(type);
    }

    private void initializeSchedule(final DayCalendar.CalendarType type) {
        final List<Table> tableList = this.restaurant.getTables();
        switch (type) {
            case WEEKDAY:
                for (int weekDays = 0; weekDays < 5; weekDays++) {
                    final LocalDate date = LocalDate.now().plusDays(weekDays);
                    for (int table = 0; table < this.restaurant.getTables().size(); table++) {
                        this.restaurantSchedule.put(date, new RestaurantDayCalendar(tableList));
                    }
                }
                break;
            case WEEKEND:
                for (int weekEnds = 0; weekEnds < 2; weekEnds++) {
                    final LocalDate date = LocalDate.now().plusDays(weekEnds);
                    for (int table = 0; table < this.restaurant.getTables().size(); table++) {
                        this.restaurantSchedule.put(date, new RestaurantDayCalendar(tableList));
                    }
                }
                break;
            default:
                break;
        }
    }


    private class RestaurantDayCalendar {

        private Map<Table, DayCalendar> tableDayCalendar;
        private List<Table> tableList;

        public RestaurantDayCalendar(final List<Table> tables) {
            this.tableList = tables;
            this.tableDayCalendar = new HashMap<>();
            for (int i = 0; i < tables.size(); i++) {
                tableDayCalendar.put(tableList.get(i), new DayCalendar());
            }
        }

    }
}

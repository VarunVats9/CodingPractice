package com.practice.design.restaurantreservation;

public class DayCalendar {

    // Midnight to 1 AM is considered as 0th place.
    private boolean[] hourlyReservationSchedule;

    public DayCalendar() {
        this.hourlyReservationSchedule = new boolean[24];
    }

    public void reserve(final int start, final int end) {
        for (int i = start; i < end; i++) {
            hourlyReservationSchedule[i] = true;
        }
    }

    public boolean[] getHourlyReservationSchedule() {
        return this.hourlyReservationSchedule;
    }

    public void cancelReservation(final Reservation reservation) {
        for (int i = reservation.getStartTime(); i < reservation.getEndTime(); i++) {
            hourlyReservationSchedule[i] = false;
        }
    }

    public enum CalendarType {
        WEEKDAY,
        WEEKEND;
    }
}

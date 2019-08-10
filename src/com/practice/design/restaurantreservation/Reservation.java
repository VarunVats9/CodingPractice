package com.practice.design.restaurantreservation;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private String reservedFor;
    private LocalDate localDate;
    private int startTime;
    private int endTime;

    public Reservation(final int reservationId, final String reservedFor, final LocalDate localDate,
        final int startTime, final int endTime) {
        this.reservationId = reservationId;
        this.reservedFor = reservedFor;
        this.localDate = localDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }
}

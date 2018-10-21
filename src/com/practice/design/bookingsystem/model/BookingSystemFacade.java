package com.practice.design.bookingsystem.model;


public class BookingSystemFacade {

    private static final int MOVIE = 1;
    private static final int THEATRE = 2;

    public BookingSystemFacade() {

    }

    public String showMoviesOrTheatresBasedOnOption(final Integer option) {
        switch (option) {
            case MOVIE: break;
            case THEATRE: break;
            default: break;
        }
        return null;
    }

}

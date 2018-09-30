package com.practice.design.bookingsystem.controller;

import java.util.Scanner;

import com.practice.design.bookingsystem.model.BookingSystemFacade;
import com.practice.design.bookingsystem.view.BookingSystemOutput;
import com.practice.design.bookingsystem.view.UserInput;

/**
 * Created by vvats on 30/09/18.
 */
public class BookingSystemController {

    public static void main(String[] args) {

        final UserInput userInput = new UserInput();
        final BookingSystemFacade bookingSystemFacade = new BookingSystemFacade();
        final BookingSystemOutput bookingSystemOutput = new BookingSystemOutput();
        final Scanner readUserInput = new Scanner(System.in);


        userInput.chooseEitherTheatreOrMovie();
        final Integer optionChoosedByUser = readUserInput.nextInt();


    }

}

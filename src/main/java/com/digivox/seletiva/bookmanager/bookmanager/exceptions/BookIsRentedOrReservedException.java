package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ResourceBundle;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BookIsRentedOrReservedException extends Exception {

    public BookIsRentedOrReservedException() {
        super(ResourceBundle.getBundle("messages").getString("com.digivox.book.rented.reserved"));
    }
}

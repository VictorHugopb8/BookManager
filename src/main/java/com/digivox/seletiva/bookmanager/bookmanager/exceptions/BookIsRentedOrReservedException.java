package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BookIsRentedOrReservedException extends Exception {

    public BookIsRentedOrReservedException() {
        super("com.digivox.book.rented.reserved");
    }
}

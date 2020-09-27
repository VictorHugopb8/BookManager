package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyRegisteredBookException extends Exception {
    public AlreadyRegisteredBookException() {
        super("com.digivox.already.registered.book");
    }
}

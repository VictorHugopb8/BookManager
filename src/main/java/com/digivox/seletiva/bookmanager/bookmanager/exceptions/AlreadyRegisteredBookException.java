package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ResourceBundle;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyRegisteredBookException extends Exception {
    public AlreadyRegisteredBookException() {
        super(ResourceBundle.getBundle("messages").getString("com.digivox.already.registered.book"));
    }
}

package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import java.util.ResourceBundle;

public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException() {
        super(ResourceBundle.getBundle("messages").getString("com.digivox.customer.already.exists"));
    }
}

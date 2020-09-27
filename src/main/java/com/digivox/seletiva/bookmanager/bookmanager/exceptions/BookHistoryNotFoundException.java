package com.digivox.seletiva.bookmanager.bookmanager.exceptions;

import java.util.ResourceBundle;

public class BookHistoryNotFoundException extends Exception {
    public BookHistoryNotFoundException() {
        super(ResourceBundle.getBundle("messages").getString("com.digivox.bookhistory.notfound"));
    }
}

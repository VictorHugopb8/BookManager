package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;

@FunctionalInterface
public interface CancelBookReserveService {

    void cancel(Long bookId, String nationalId) throws CustomerNotFoundException, BookHistoryNotFoundException;

}

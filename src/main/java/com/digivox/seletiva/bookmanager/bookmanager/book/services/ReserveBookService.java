package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookIsRentedOrReservedException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;

import java.time.LocalDate;

@FunctionalInterface
public interface ReserveBookService {

    void reserve(Long book, String nationalId, LocalDate scheduledReserveDate)
            throws BookNotFoundException, CustomerNotFoundException, BookHistoryNotFoundException, BookIsRentedOrReservedException;

}

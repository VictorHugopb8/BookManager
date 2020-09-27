package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookIsRentedOrReservedException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;

import java.time.LocalDate;

@FunctionalInterface
public interface RentBookService {

    void rent(Long bookId, String nationalId, LocalDate scheduledReturnDate) throws BookIsRentedOrReservedException, CustomerNotFoundException, BookNotFoundException;

}

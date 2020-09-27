package com.digivox.seletiva.bookmanager.bookmanager.book.builders;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;

import java.time.LocalDate;

public class BookHistoryBuilder {


    public static BookHistory.BookHistoryBuilder createBookHistory(Customer customer, Book book) {
        return BookHistory.builder()
                .book(book)
                .customer(customer)
                .isRented(false)
                .isReserved(false)
                .scheduledReserveDate(LocalDate.now())
                .scheduledReturnDate(null)
                .rentedQuantity(1L)
                .id(1L);
    }
}

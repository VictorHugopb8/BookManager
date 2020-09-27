package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;

@FunctionalInterface
public interface DevolutionBookService {

    String returnBook(Long bookId, String nationalId) throws BookHistoryNotFoundException;

}

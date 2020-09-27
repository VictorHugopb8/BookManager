package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.AlreadyRegisteredBookException;

@FunctionalInterface
public interface RegisterBookService {

    void save(BookDTO book) throws AlreadyRegisteredBookException;

}

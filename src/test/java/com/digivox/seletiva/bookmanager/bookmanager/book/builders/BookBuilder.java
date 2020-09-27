package com.digivox.seletiva.bookmanager.bookmanager.book.builders;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;

public class BookBuilder {
    public static Book.BookBuilder createBook() {
        return Book.builder()
                .id(1L)
                .title("Programação Orientada a Objeto - POO")
                .version("1")
                .rentPerDay(5.75);
    }

    public static BookDTO.BookDTOBuilder createBookDTO() {
        return BookDTO.builder()
                .title("Programação Orientada a Objeto - POO")
                .version("1")
                .rentPerDay(5.75);
    }
}

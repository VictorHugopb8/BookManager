package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageBookServiceImpl implements PageBookService {

    private final BookRepository bookRepository;

    public Page<BookDTO> page(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookDTO> bookDTOList = new ArrayList<>(books.getSize());
        books.forEach(book -> {
            BookDTO dto = BookDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .version(book.getVersion())
                    .rentPerDay(book.getRentPerDay())
                    .build();
            bookDTOList.add(dto);
        });
        return new PageImpl<>(bookDTOList);
    }
}

package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.AlreadyRegisteredBookException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterBookServiceImpl implements RegisterBookService {

    private final BookRepository bookRepository;

    @Override
    public void save(BookDTO entity) throws AlreadyRegisteredBookException {
        Optional<Book> registeredBookOpt = bookRepository.findByTitleAndVersion(entity.getTitle(), entity.getVersion());
        if (registeredBookOpt.isPresent()) {
            throw new AlreadyRegisteredBookException();
        }

        Book book = Book.builder()
                .title(entity.getTitle())
                .version(entity.getVersion())
                .rentPerDay(entity.getRentPerDay())
                .build();
        this.bookRepository.save(book);
    }
}

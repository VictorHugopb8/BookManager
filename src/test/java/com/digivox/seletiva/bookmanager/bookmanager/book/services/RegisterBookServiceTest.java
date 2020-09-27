package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.AlreadyRegisteredBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookBuilder.createBookDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@DisplayName("Valida serviço de cadastro de livro")
@ExtendWith(MockitoExtension.class)
public class RegisterBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private RegisterBookService registerBookService;

    @BeforeEach
    public void setUp() {
        this.registerBookService = new RegisterBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve cadastrar livro com sucesso")
    public void shouldRegisterBookSuccessfully() throws AlreadyRegisteredBookException {
        when(bookRepository.findByTitleAndVersion(anyString(), anyString())).thenReturn(Optional.empty());

        registerBookService.save(createBookDTO().build());
        verify(bookRepository).save(any(Book.class));
    }

}

package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookBuilder.createBook;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida serviço de paginação de livro alugado")
public class PageBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private PageBookService pageBookService;

    @BeforeEach
    public void setUp() {
        this.pageBookService = new PageBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve paginar, lista de livros, com sucesso")
    public void shouldPageBookSuccessfully() {
        when(bookRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(createBook().build())));

        Page<BookDTO> page = pageBookService.page(Pageable.unpaged());
        assertAll("page",
                () -> assertNotNull(page),
                () -> assertEquals(1, page.getSize()),
                () -> assertEquals(1, page.getNumberOfElements())
        );
    }
}

package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookBuilder.createBook;
import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookHistoryBuilder.createBookHistory;
import static com.digivox.seletiva.bookmanager.bookmanager.customer.builders.CustomerBuilder.createCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida serviço de devolução de livro alugado")
public class DevolutionBookServiceTest {

    @Mock
    private BookHistoryRepository bookHistoryRepository;

    private DevolutionBookService devolutionBookService;

    @BeforeEach
    public void setUp() {
        this.devolutionBookService = new DevolutionBookServiceImpl(bookHistoryRepository);
    }

    @Test
    @DisplayName("Deve confirmar devolução, de livro alugado, com sucesso")
    public void shouldReturnRentedBookSuccessfully() throws BookHistoryNotFoundException {
        when(bookHistoryRepository.findByBookIdAndCustomerNationalId(anyLong(), anyString()))
                .thenReturn(Optional.of(
                        createBookHistory(
                                createCustomer().build(),
                                createBook().build()
                        ).scheduledReturnDate(
                                LocalDate.now().plusDays(1L)
                        ).build()
                ));

        String status = devolutionBookService.returnBook(1L, "012345678910");

        assertAll("devolution",
                () -> assertNotNull(status),
                () -> assertEquals("Livro devolvido com sucesso.", status)
        );
        verify(bookHistoryRepository).save(any(BookHistory.class));
    }
}

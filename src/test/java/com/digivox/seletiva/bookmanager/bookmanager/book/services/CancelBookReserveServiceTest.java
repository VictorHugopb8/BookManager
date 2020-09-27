package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookBuilder.createBook;
import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookHistoryBuilder.createBookHistory;
import static com.digivox.seletiva.bookmanager.bookmanager.customer.builders.CustomerBuilder.createCustomer;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@DisplayName("Valida serviço de cancelamento de reserva de livro")
@ExtendWith(MockitoExtension.class)
public class CancelBookReserveServiceTest {

    @Mock
    private BookHistoryRepository bookHistoryRepository;

    @Mock
    private CustomerRepository customerRepository;

    private CancelBookReserveService cancelBookReserveService;

    @BeforeEach
    public void setUp() {
        this.cancelBookReserveService = new CancelBookReserveServiceImpl(bookHistoryRepository, customerRepository);
    }

    @Test
    @DisplayName("Deve cancelar reserva de livro com sucesso")
    public void shouldCancelBookReserveSuccessfully() throws CustomerNotFoundException, BookHistoryNotFoundException {
        Customer customer = createCustomer().build();
        Book book = createBook().build();
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(bookHistoryRepository.findByBookIdAndCustomerId(anyLong(), anyLong())).thenReturn(Optional.of(createBookHistory(customer, book).build()));

        cancelBookReserveService.cancel(1L, "07985866670");
        verify(bookHistoryRepository).save(any(BookHistory.class));
    }

}

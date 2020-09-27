package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.Book;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookIsRentedOrReservedException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@DisplayName("Valida serviço de aluguel de livro")
@ExtendWith(MockitoExtension.class)
public class RentBookServiceTest {

    @Mock
    private BookHistoryRepository bookHistoryRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CustomerRepository  customerRepository;

    private RentBookService rentBookService;

    @BeforeEach
    public void setUp() {
        this.rentBookService = new RentBookServiceImpl(bookHistoryRepository, bookRepository, customerRepository);
    }

    @Test
    @DisplayName("Deve cadastrar livro com sucesso")
    public void shouldRentBookSuccessfully() throws BookIsRentedOrReservedException, CustomerNotFoundException, BookNotFoundException {
        Book book = createBook().build();
        Customer customer = createCustomer().build();
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(bookHistoryRepository.findByBookIdAndCustomerId(anyLong(), anyLong()))
                .thenReturn(Optional.of(createBookHistory(customer, book).build()));

        rentBookService.rent(1L, "12345678901", LocalDate.now());
        verify(bookHistoryRepository).save(any(BookHistory.class));
    }

}

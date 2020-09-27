package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDashboardGeneralDataDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.DashboardDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.RentedBookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookBuilder.createBook;
import static com.digivox.seletiva.bookmanager.bookmanager.book.builders.BookHistoryBuilder.createBookHistory;
import static com.digivox.seletiva.bookmanager.bookmanager.customer.builders.CustomerBuilder.createCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida serviço de dashboard")
public class DashBoardServiceTest {

    @Mock
    private BookHistoryRepository bookHistoryRepository;

    @Mock
    private BookRepository bookRepository;

    private DashboardService dashboardService;

    @BeforeEach
    public void setUp() {
        this.dashboardService = new DashboardServiceImpl(
                bookHistoryRepository,
                bookRepository
        );
    }

    @Test
    @DisplayName("Deve retornar dados de livros disponíveis, alugados e reservados, com sucesso")
    public void shouldReturnBookDashboardInfoSuccessfully() {
        when(bookHistoryRepository.countRentedBooks()).thenReturn(1);
        when(bookHistoryRepository.countReservedBooks()).thenReturn(2);
        when(bookRepository.count()).thenReturn(5L);

        List<DashboardDTO> dashboardInfo = dashboardService.getDashboardInfo("pie");

        assertAll("dashboardInfo",
                () -> assertNotNull(dashboardInfo),
                () -> assertEquals(2, ((BookDashboardGeneralDataDTO) dashboardInfo.get(0)).getAvailableBooks()),
                () -> assertEquals(2, ((BookDashboardGeneralDataDTO) dashboardInfo.get(0)).getReservedBooks()),
                () -> assertEquals(1, ((BookDashboardGeneralDataDTO) dashboardInfo.get(0)).getRentedBooks())
        );
    }

    @Test
    @DisplayName("Deve retornar dados dos livros mais alugados, com sucesso")
    public void shouldBooksMostRentedSuccessfully() {
        when(bookHistoryRepository.findMostRentedBooks())
                .thenReturn(
                        Collections.singletonList(
                                createBookHistory(
                                        createCustomer().build(),
                                        createBook().build()).build()
                        )
                );

        List<DashboardDTO> dashboardInfo = dashboardService.getDashboardInfo("bar");

        assertAll("dashboardInfo",
                () -> assertNotNull(dashboardInfo),
                () -> assertEquals("Programação Orientada a Objeto - POO", ((RentedBookDTO) dashboardInfo.get(0)).getTitle()),
                () -> assertEquals(1L, ((RentedBookDTO) dashboardInfo.get(0)).getRentedQuantity())
        );
    }

}

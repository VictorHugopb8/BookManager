package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookRepository;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDashboardGeneralDataDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.DashboardDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.RentedBookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BookHistoryRepository bookHistoryRepository;
    private final BookRepository bookRepository;

    @Override
    public List<DashboardDTO> getDashboardInfo(String type) {
        switch (type) {
            case "pie":
                return getPieData();
            case "bar":
                return get3MostRentedInBarData();
            default:
                return null;
        }
    }

    private List<DashboardDTO> get3MostRentedInBarData() {
        List<BookHistory> allRented = bookHistoryRepository.findMostRentedBooks();
        List<DashboardDTO> dtos = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            if (i < allRented.size()) {
                dtos.add(
                    RentedBookDTO.builder()
                        .title(allRented.get(i).getBook().getTitle())
                        .rentedQuantity(allRented.get(i).getRentedQuantity())
                        .build()
                );
            }
        }
        return dtos;
    }

    private List<DashboardDTO> getPieData() {
        Integer rentedBooksQtd = bookHistoryRepository.countRentedBooks();
        Integer reservedBooksQtd = bookHistoryRepository.countReservedBooks();
        Integer availableBooksQtd = (int) bookRepository.count() - rentedBooksQtd - reservedBooksQtd;

        return Collections.singletonList(BookDashboardGeneralDataDTO.builder()
                .availableBooks(availableBooksQtd)
                .rentedBooks(rentedBooksQtd)
                .reservedBooks(reservedBooksQtd)
                .build());
    }
}

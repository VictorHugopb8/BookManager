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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReserveBookServiceImpl implements ReserveBookService {

    private final BookHistoryRepository bookHistoryRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void reserve(Long bookId, String nationalId, LocalDate scheduledReserveDate)
            throws BookNotFoundException, CustomerNotFoundException, BookIsRentedOrReservedException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Customer customer = customerRepository.findByNationalId(nationalId).orElseThrow(CustomerNotFoundException::new);
        Optional<BookHistory> bookHistoryOpt = bookHistoryRepository.findByBookIdAndCustomerId(
                book.getId(),
                customer.getId()
        );

        if (bookHistoryOpt.isEmpty()) {
            if (bookHistoryRepository.countByBookIdAndCustomerIsNotNull(bookId) > 0) {
                throw new BookIsRentedOrReservedException();
            }
            bookHistoryOpt = bookHistoryRepository.findByBookId(bookId);
            if (bookHistoryOpt.isEmpty()) {
                BookHistory bookHistory = BookHistory.builder()
                        .book(book)
                        .customer(customer)
                        .isRented(false)
                        .isReserved(true)
                        .scheduledReserveDate(scheduledReserveDate)
                        .scheduledReturnDate(null)
                        .build();
                bookHistoryRepository.save(bookHistory);
            } else {
                BookHistory bookHistory = bookHistoryOpt.get();
                bookHistory.setCustomer(customer);
                bookHistory.setIsReserved(true);
                bookHistory.setScheduledReserveDate(scheduledReserveDate);
                bookHistoryRepository.save(bookHistory);
            }
        } else if (!bookHistoryOpt.get().getIsReserved() && !bookHistoryOpt.get().getIsRented()) {
            BookHistory bookHistory = bookHistoryOpt.get();
            bookHistory.setCustomer(customer);
            bookHistory.setIsReserved(true);
            bookHistory.setScheduledReserveDate(scheduledReserveDate);
            bookHistoryRepository.save(bookHistory);
        } else {
            throw new BookIsRentedOrReservedException();
    }
    }
}

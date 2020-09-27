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
public class RentBookServiceImpl implements RentBookService {

    private final BookHistoryRepository bookHistoryRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void rent(Long bookId, String nationalId, LocalDate scheduledReturnDate)
            throws BookIsRentedOrReservedException, CustomerNotFoundException, BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Customer customer = customerRepository.findByNationalId(nationalId).orElseThrow(CustomerNotFoundException::new);

        Optional<BookHistory> bookHistoryOpt = bookHistoryRepository
                .findByBookIdAndCustomerId(book.getId(), customer.getId());
        if (bookHistoryOpt.isEmpty()) {
            if (bookHistoryRepository.countByBookIdAndCustomerIsNotNull(bookId) > 0) {
                throw new BookIsRentedOrReservedException();
            } else {
                bookHistoryOpt = bookHistoryRepository.findByBookId(bookId);
            }
        }
        if (bookHistoryOpt.isPresent() && (!bookHistoryOpt.get().getIsReserved()
            || (bookHistoryOpt.get().getIsReserved()
            && nationalId.equals(bookHistoryOpt.get().getCustomer().getNationalId())))) {

            BookHistory bookHistory = bookHistoryOpt.get();
            bookHistory.setBook(book);
            bookHistory.setCustomer(customer);
            bookHistory.setIsRented(true);
            bookHistory.setIsReserved(false);
            bookHistory.setScheduledReturnDate(scheduledReturnDate);
            bookHistory.setRentedQuantity(bookHistoryOpt.get().getRentedQuantity() + 1);
            bookHistoryRepository.save(bookHistory);
        } else {
            BookHistory bookHistory = BookHistory.builder()
                    .book(book)
                    .customer(customer)
                    .isReserved(false)
                    .isRented(true)
                    .scheduledReturnDate(scheduledReturnDate)
                    .scheduledReserveDate(null)
                    .rentedQuantity(1L)
                    .build();
            bookHistoryRepository.save(bookHistory);
        }
    }
}

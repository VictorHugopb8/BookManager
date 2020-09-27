package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CancelBookReserveServiceImpl implements CancelBookReserveService {

    private final BookHistoryRepository bookHistoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void cancel(Long bookId, String nationalId) throws CustomerNotFoundException, BookHistoryNotFoundException {
        Customer customer = customerRepository.findByNationalId(nationalId).orElseThrow(CustomerNotFoundException::new);
        BookHistory bookHistory = bookHistoryRepository
                .findByBookIdAndCustomerId(
                        bookId,
                        customer.getId()
                ).orElseThrow(BookHistoryNotFoundException::new);

        bookHistory.setIsReserved(false);
        bookHistory.setScheduledReserveDate(null);
        bookHistory.setCustomer(null);
        bookHistoryRepository.save(bookHistory);
    }
}

package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistory;
import com.digivox.seletiva.bookmanager.bookmanager.book.BookHistoryRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.BookHistoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DevolutionBookServiceImpl implements DevolutionBookService {

    private final BookHistoryRepository bookHistoryRepository;

    @Override
    public String returnBook(Long bookId, String nationalId) throws BookHistoryNotFoundException {
        Optional<BookHistory> bookHistoryOpt = bookHistoryRepository.findByBookIdAndCustomerNationalId(bookId, nationalId);
        if (bookHistoryOpt.isPresent() && LocalDate.now().isAfter(bookHistoryOpt.get().getScheduledReturnDate())) {
            updateBookHistory(bookHistoryOpt.get());
            return "Livro devolvido, mas deve ser paga uma multa por atraso na entrega.";
        } else if (bookHistoryOpt.isPresent() && LocalDate.now().isBefore(bookHistoryOpt.get().getScheduledReturnDate())) {
            updateBookHistory(bookHistoryOpt.get());
            return "Livro devolvido com sucesso.";
        }
        throw new BookHistoryNotFoundException();
    }

    private void updateBookHistory(BookHistory bookHistory) {
        bookHistory.setScheduledReturnDate(null);
        bookHistory.setCustomer(null);
        bookHistory.setIsRented(false);
        bookHistoryRepository.save(bookHistory);
    }

}

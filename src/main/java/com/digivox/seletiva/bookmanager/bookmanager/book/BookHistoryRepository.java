package com.digivox.seletiva.bookmanager.bookmanager.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

    @Query(value = "SELECT bh FROM BookHistory bh WHERE bh.book.id = :bookId " +
            "AND (bh.customer.id is not null AND bh.customer.id = :customerId)")
    Optional<BookHistory> findByBookIdAndCustomerId(@Param("bookId") Long bookId, @Param("customerId") Long customerId);

    Optional<BookHistory> findByBookIdAndCustomerNationalId(Long bookId, String nationalId);

    @Query(value = "SELECT count(bh) FROM BookHistory bh WHERE bh.isRented = true")
    Integer countRentedBooks();

    @Query(value = "SELECT count(bh) FROM BookHistory bh WHERE bh.isReserved = true")
    Integer countReservedBooks();

    @Query(value = "SELECT bh FROM BookHistory bh WHERE bh.book is not null ORDER BY bh.rentedQuantity DESC")
    List<BookHistory> findMostRentedBooks();

    Long countByBookIdAndCustomerIsNotNull(Long bookId);

    Optional<BookHistory> findByBookId(Long bookId);
}

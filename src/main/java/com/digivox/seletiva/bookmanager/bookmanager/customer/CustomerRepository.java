package com.digivox.seletiva.bookmanager.bookmanager.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.nationalId LIKE :nationalId")
    Optional<Customer> findByNationalId(@Param("nationalId") String nationalId);
}

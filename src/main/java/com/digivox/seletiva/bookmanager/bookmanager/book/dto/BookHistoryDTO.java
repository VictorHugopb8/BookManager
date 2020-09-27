package com.digivox.seletiva.bookmanager.bookmanager.book.dto;

import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookHistoryDTO {

    @JsonProperty
    private BookDTO book;

    @JsonProperty
    private CustomerDTO customer;

    @JsonProperty
    private Boolean isRented;

    @JsonProperty
    private Boolean isReserved;

    @JsonProperty
    private LocalDate scheduledReturnDate;

    @JsonProperty
    private LocalDate scheduledReserveDate;

}

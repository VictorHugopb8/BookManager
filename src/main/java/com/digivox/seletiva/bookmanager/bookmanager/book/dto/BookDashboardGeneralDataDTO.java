package com.digivox.seletiva.bookmanager.bookmanager.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDashboardGeneralDataDTO extends DashboardDTO {
    private Integer availableBooks;
    private Integer rentedBooks;
    private Integer reservedBooks;
}

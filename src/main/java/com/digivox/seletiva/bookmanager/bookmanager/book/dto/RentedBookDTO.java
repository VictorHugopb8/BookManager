package com.digivox.seletiva.bookmanager.bookmanager.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentedBookDTO extends DashboardDTO {
    private String title;
    private Long rentedQuantity;
}

package com.digivox.seletiva.bookmanager.bookmanager.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    
    @NonNull
    @JsonProperty
    private String title;

    @NonNull
    @JsonProperty
    private String version;

    @NonNull
    @JsonProperty
    private Double rentPerDay;

}

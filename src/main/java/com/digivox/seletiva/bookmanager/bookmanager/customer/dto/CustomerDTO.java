package com.digivox.seletiva.bookmanager.bookmanager.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NonNull
    @JsonProperty
    private String name;

    @NonNull
    @Email
    @JsonProperty
    private String email;

    @NonNull
    @JsonProperty
    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}-?\\d{4})",message = "Telefone inválido")
    private String phone;

    @NonNull
    @CPF
    @JsonProperty
    private String nationalId;

}

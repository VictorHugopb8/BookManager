package com.digivox.seletiva.bookmanager.bookmanager.customer.services;

import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static com.digivox.seletiva.bookmanager.bookmanager.customer.builders.CustomerBuilder.createCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida serviço de paginação de clientes alugado")
public class PageCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private PageCustomerService pageCustomerService;

    @BeforeEach
    public void setUp() {
        this.pageCustomerService = new PageCustomerServiceImpl(customerRepository);
    }

    @Test
    @DisplayName("Deve paginar, lista de clientes, com sucesso")
    public void shouldPageCustomerSuccessfully() {
        when(customerRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(createCustomer().build())));

        Page<CustomerDTO> page = pageCustomerService.page(Pageable.unpaged());
        assertAll("page",
                () -> assertNotNull(page),
                () -> assertEquals(1, page.getSize()),
                () -> assertEquals(1, page.getNumberOfElements())
        );
    }
}

package com.digivox.seletiva.bookmanager.bookmanager.customer.services;

import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.digivox.seletiva.bookmanager.bookmanager.customer.builders.CustomerBuilder.createCustomerDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida serviço de cliente da loja de aluguel de livro")
public class RegisterCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private RegisterCustomerService registerCustomerService;

    @BeforeEach
    public void setUp() {
        this.registerCustomerService = new RegisterCustomerServiceImpl(customerRepository);
    }

    @Test
    @DisplayName("Deve cadastrar cliente com sucesso")
    public void shouldRegisterCustomerSuccessfully() throws CustomerAlreadyExistsException {
        when(customerRepository.findByNationalId(anyString()))
                .thenReturn(Optional.empty());

        registerCustomerService.save(createCustomerDTO().build());
        verify(customerRepository).save(any(Customer.class));
    }
}

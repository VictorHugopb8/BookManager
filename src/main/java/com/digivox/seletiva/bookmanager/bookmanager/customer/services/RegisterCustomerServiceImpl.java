package com.digivox.seletiva.bookmanager.bookmanager.customer.services;

import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterCustomerServiceImpl implements RegisterCustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void save(CustomerDTO entity) throws CustomerAlreadyExistsException {
        Optional<Customer> customerOpt = customerRepository.findByNationalId(entity.getNationalId());
        if (customerOpt.isEmpty()) {
            Customer customer = Customer.builder()
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .nationalId(entity.getNationalId())
                    .phone(entity.getPhone())
                    .build();
            customerRepository.save(customer);
            return;
        }
        throw new CustomerAlreadyExistsException();
    }

}

package com.digivox.seletiva.bookmanager.bookmanager.customer.services;

import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.CustomerRepository;
import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageCustomerServiceImpl implements PageCustomerService {

    private final CustomerRepository customerRepository;

    public Page<CustomerDTO> page(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerDTO> customerDTOList = new ArrayList<>(customers.getSize());
        customers.forEach(customer -> {
            CustomerDTO dto = CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phone(customer.getPhone())
                    .nationalId(customer.getNationalId())
                    .build();
            customerDTOList.add(dto);
        });
        return new PageImpl<>(customerDTOList);
    }
}

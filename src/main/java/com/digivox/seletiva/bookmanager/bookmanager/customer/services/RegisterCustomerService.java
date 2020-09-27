package com.digivox.seletiva.bookmanager.bookmanager.customer.services;

import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerAlreadyExistsException;

@FunctionalInterface
public interface RegisterCustomerService {

    void save(CustomerDTO customer) throws CustomerAlreadyExistsException;

}

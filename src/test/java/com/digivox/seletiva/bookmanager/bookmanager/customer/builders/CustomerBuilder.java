package com.digivox.seletiva.bookmanager.bookmanager.customer.builders;

import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;

public class CustomerBuilder {

    public static Customer.CustomerBuilder createCustomer() {
        return Customer.builder()
                .id(1L)
                .name("Customer da Silva")
                .phone("8399998888")
                .nationalId("07985866670")
                .email("abc@abc.abc");
    }

    public static CustomerDTO.CustomerDTOBuilder createCustomerDTO() {
        return CustomerDTO.builder()
                .name("Customer da Silva")
                .nationalId("07985866670")
                .phone("83999998888")
                .email("abc@abc.abc");
    }
}

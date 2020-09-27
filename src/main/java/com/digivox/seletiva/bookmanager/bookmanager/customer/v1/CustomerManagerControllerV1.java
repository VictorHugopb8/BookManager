package com.digivox.seletiva.bookmanager.bookmanager.customer.v1;

import com.digivox.seletiva.bookmanager.bookmanager.customer.dto.CustomerDTO;
import com.digivox.seletiva.bookmanager.bookmanager.customer.services.PageCustomerService;
import com.digivox.seletiva.bookmanager.bookmanager.customer.services.RegisterCustomerService;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerManagerControllerV1 {
    
    private final RegisterCustomerService registerCustomerService;
    private final PageCustomerService pageCustomerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CustomerDTO> page(Pageable pageable) {
        return this.pageCustomerService.page(pageable);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody @Validated CustomerDTO entity) throws CustomerAlreadyExistsException {
        this.registerCustomerService.save(entity);
    }

}

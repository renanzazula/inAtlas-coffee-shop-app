package com.standard.coffeeshop.controller.customer;

import com.standard.coffeeshop.controller.domain.Customer;
import com.standard.coffeeshop.utils.ConstantsApi;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Manage Customer", tags = "customer")
@RestController
@RequestMapping(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
public class CustomerController {

    public static final String CUSTOMER_CONTROLLER_BASE_URL = ConstantsApi.CUSTOMER;
    @PreAuthorize("hasAnyAuthority('CUSTOMER_SEARCH')")
    @GetMapping({""})
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(Arrays.asList(new Customer(), new Customer()).stream().collect(Collectors.toList()), HttpStatus.OK);
    }


}

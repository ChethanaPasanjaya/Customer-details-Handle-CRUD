package com.bank.bankproject.controller;

import com.bank.bankproject.dto.CustomerDto;
import com.bank.bankproject.dto.ResponseDto;
import com.bank.bankproject.dto.UpdateCustomerDto;
import com.bank.bankproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    //save method is working
    @PostMapping("/save")
    public ResponseDto saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/getall")
    public ResponseDto getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //getCustomerById is working
    @GetMapping("/getcustomer/{id}")
    public ResponseDto getCustomer(@PathVariable @NotNull(message = "customer id can't be null") Integer id) {
        return customerService.getCustomerById(id);
    }
    //deleteCustomerById is working
    @DeleteMapping("/deletecustomer/{id}")
    public ResponseDto deleteCustomer(@PathVariable @NotNull(message = "customer id can't be null") Integer id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/updateCustomer")
    public ResponseDto updateCustomer(@RequestBody @Valid UpdateCustomerDto updateCustomerDto) {
        return customerService.updateCustomer(updateCustomerDto);
    }
}

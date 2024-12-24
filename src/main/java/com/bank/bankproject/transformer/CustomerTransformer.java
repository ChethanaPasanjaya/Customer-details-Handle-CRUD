package com.bank.bankproject.transformer;

import com.bank.bankproject.dto.CustomerDto;
import com.bank.bankproject.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerTransformer {
    public CustomerDto transformerCustomer(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .age(customer.getAge())
                .nic(customer.getNic())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .cusname(customer.getCusname())
                .build();
    }

    public Customer reverseTransformerCustomer(CustomerDto customerDto) {

        return Customer.builder()
                .id(Optional.of(customerDto.getId()).orElse(null))
                .age(customerDto.getAge())
                .nic(customerDto.getNic())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .cusname(customerDto.getCusname())
                .build();

    }
}
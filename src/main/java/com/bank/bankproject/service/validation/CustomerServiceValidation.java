package com.bank.bankproject.service.validation;

import com.bank.bankproject.dto.ResponseDto;
import com.bank.bankproject.dto.UpdateCustomerDto;
import com.bank.bankproject.repository.CustomerRepository;
import com.bank.bankproject.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceValidation {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ServiceUtil serviceUtil;

    public ResponseDto updateCustomerValidation(UpdateCustomerDto updateCustomerDto) {
        ResponseDto responseDto = null;
        if (!customerRepository.existsById(updateCustomerDto.getId())) {
            responseDto = serviceUtil.getValidationErrorResponse("Customer ID doesn't exist");
        } else if (customerRepository.existsByEmailAndIdNot(updateCustomerDto.getEmail(), updateCustomerDto.getId())) {
            responseDto = serviceUtil.getValidationErrorResponse("Customer email is already taken");
        } else if (customerRepository.existsByNicAndIdNot(updateCustomerDto.getNic(), updateCustomerDto.getId())) {
            responseDto = serviceUtil.getValidationErrorResponse("Customer NIC is already taken");
        } else if (customerRepository.existsByPhoneAndIdNot(updateCustomerDto.getPhone(), updateCustomerDto.getId())) {
            responseDto = serviceUtil.getValidationErrorResponse("Customer phone number is already taken");
        }
        return responseDto;
    }

    public ResponseDto deleteCustomerValidation(Integer id) {
        ResponseDto responseDto = null;
        if (!customerRepository.existsById(id)) {
            responseDto = serviceUtil.getValidationErrorResponse("customer id isn't there");
        }
        return responseDto;
    }

    public ResponseDto getByIdCustomerValidation(Integer id) {
        ResponseDto responseDto = null;
        if (!customerRepository.existsById(id)) {
            responseDto = serviceUtil.getValidationErrorResponse("customer id isn't there");
        }
        return responseDto;
    }
}

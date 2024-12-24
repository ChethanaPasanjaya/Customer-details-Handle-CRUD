/*
package com.bank.bankproject.service;

import com.bank.bankproject.dto.ResponseDto;
import com.bank.bankproject.service.util.ServiceUtil;
import com.bank.bankproject.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class AccountTypeService {
    @Autowired
    private CustomerTransformer customerTransformer;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private ServiceUtil serviceUtil;

    public ResponseDto saveAccountType(CustomerDto customerDto) throws ConstraintViolationException {
        ResponseDto responseDto = null;
        try {
            CustomerModel customerModel1 = customerTransformer.reverseTransformerAccountType(customerDto);
            CustomerModel saveCustomerModel = accountTypeRepository.save(customerModel1);
            responseDto = serviceUtil.getServiceResponse(saveCustomerModel);
            responseDto.setHttpStatus(HttpStatus.OK.value()); // Set the appropriate HTTP status
            return responseDto;
        } catch (ConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponse(e);
            responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return responseDto;
        }
    }


    public ResponseDto getById(Long id) {
        ResponseDto responseDto = null;
        try {
            Optional<CustomerModel> accountTypeModelOptional = accountTypeRepository.findById(id);
            if (accountTypeModelOptional.isPresent()) {
                CustomerModel customerModel = accountTypeModelOptional.get();
                CustomerDto customerDto = customerTransformer.transformerAccountType(customerModel);
                responseDto = serviceUtil.getServiceResponse(customerDto);
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("err.find.customer.details.by.email");
            }

        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponse(e);
        }

        return responseDto;
    }
}*/

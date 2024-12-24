package com.bank.bankproject.service;

import com.bank.bankproject.dto.CustomerDto;
import com.bank.bankproject.dto.ResponseDto;
import com.bank.bankproject.dto.UpdateCustomerDto;
import com.bank.bankproject.model.Customer;
import com.bank.bankproject.repository.CustomerRepository;
import com.bank.bankproject.service.util.ServiceUtil;
import com.bank.bankproject.service.validation.CustomerServiceValidation;
import com.bank.bankproject.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerTransformer customerTransformer;
    @Autowired
    private ServiceUtil serviceUtil;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerServiceValidation customerServiceValidation;

    public ResponseDto saveCustomer(CustomerDto customerDto) {
        ResponseDto responseDto = null;
        try {
            Customer customer = null;
            if (customerDto != null) {
                customer = customerTransformer.reverseTransformerCustomer(customerDto);
            }
            Customer saveCustomer = null;
            if (customer != null) {
                saveCustomer = customerRepository.save(customer);
                responseDto = serviceUtil.getServiceResponse(saveCustomer);
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("ex.save.customer.details");
            }

        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponse(e);
        }
        return responseDto;
    }

    public ResponseDto getAllCustomers() {
        ResponseDto responseDto = null;
        try {
            List<CustomerDto> customerDtoList = null;
            List<Customer> customerList = customerRepository.findAll();
            if (customerList != null) {
                customerDtoList = customerList.stream()
                        .map(customer -> customerTransformer.transformerCustomer(customer))
                        .collect(Collectors.toList());
                responseDto = serviceUtil.getServiceResponse(customerDtoList);
            }

        } catch (Exception e) {
            responseDto = serviceUtil.getErrorServiceResponse("err.get.client.logo");
        }
        return responseDto;
    }



    public ResponseDto getCustomerById(int id) {
        ResponseDto responseDto = null;
        responseDto = customerServiceValidation.getByIdCustomerValidation(id);
        if (responseDto != null) {
            return responseDto;
        }
        try {
            if (customerRepository.existsById(id)) {
                Customer customer = customerRepository.findCustomerById(id);
                CustomerDto customerDto = customerTransformer.transformerCustomer(customer);
                responseDto = serviceUtil.getServiceResponse(customerDto);
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("err.get.client.byID");
            }
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponse(e);
        }
        return responseDto;
    }

    public ResponseDto deleteCustomer(Integer id) {
        ResponseDto responseDto = null;
        responseDto = customerServiceValidation.deleteCustomerValidation(id);
        if (responseDto != null) {
            // Return the validation error response if the validation failed
            return responseDto;
        }
        try {
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                responseDto = serviceUtil.getServiceResponse("user " + id + " has been deleted");
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("err.delete.customer.record");
            }
        }catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponse(e);
        }
        return responseDto;
    }

    public ResponseDto updateCustomer(UpdateCustomerDto updatedCustomerDto) {
        ResponseDto responseDto = null;
        responseDto = customerServiceValidation.updateCustomerValidation(updatedCustomerDto);
        if (responseDto != null) {
            return responseDto;
        }
        try {
            Customer existingCustomer = null;
            if (customerRepository.findById(updatedCustomerDto.getId()).isPresent()) {
                existingCustomer = customerRepository.findById(updatedCustomerDto.getId()).get();
                // Update the customer's details with the data from the CustomerDto
                existingCustomer.setCusname(updatedCustomerDto.getCusname());
                existingCustomer.setAge(updatedCustomerDto.getAge());
                existingCustomer.setNic(updatedCustomerDto.getNic());
                existingCustomer.setPhone(updatedCustomerDto.getPhone());
                existingCustomer.setEmail(updatedCustomerDto.getEmail());
                // Save the updated customer in the database
                Customer customer = customerRepository.save(existingCustomer);
                responseDto = serviceUtil.getServiceResponse(customer);
            }
        } catch (Exception e) {
            responseDto = serviceUtil.getErrorServiceResponse(e);
        }
        return responseDto;
    }
}


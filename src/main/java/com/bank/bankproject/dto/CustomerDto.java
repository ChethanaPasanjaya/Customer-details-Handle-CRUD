package com.bank.bankproject.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
@Data
@Builder
public class CustomerDto {

    private Integer id;
    @NotNull(message = "customer name should not be null")
    private String cusname;

    @Min(value = 25, message = "Age should not be less than 25")
    @Max(value = 60, message = "Age should not be greater than 60")
    private int age;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phone;

    @Pattern(regexp = "^[0-9]{9}[vVxX]$", message = "Invalid NIC format")
    private String nic;

    @Email(message = "Invalid email format")
    private String email;
}

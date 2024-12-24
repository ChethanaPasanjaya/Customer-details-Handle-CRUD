package com.bank.bankproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cusname")
//    @NotBlank(message = "customer name should not be null")
    private String cusname;

//    @Max(60)
//    @Min(25)
    @Column(name = "age")
    private int age;


    @Column(name = "phone")
//    @Pattern(regexp = "^\\d{10}$")
    private String phone;

//    @Pattern(regexp = "^[0-9]{9}[vVxX]$")
    @Column(name = "nic")
    private String nic;

//    @Email
    @Column(name = "email")
    private String email;
}

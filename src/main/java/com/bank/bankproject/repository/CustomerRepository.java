package com.bank.bankproject.repository;

import com.bank.bankproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerById(Integer id);

    // Custom query to check if a customer with the given email exists while excluding the current customer's ID
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email AND c.id <> :customerId")
    boolean existsByEmailAndIdNot(String email, Integer customerId);

    // Custom query to check if a customer with the given NIC exists while excluding the current customer's ID
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.nic = :nic AND c.id <> :customerId")
    boolean existsByNicAndIdNot(String nic, Integer customerId);

    // Custom query to check if a customer with the given phone number exists while excluding the current customer's ID
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.phone = :phone AND c.id <> :customerId")
    boolean existsByPhoneAndIdNot(String phone, Integer customerId);

}

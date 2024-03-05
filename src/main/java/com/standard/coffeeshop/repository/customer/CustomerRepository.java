package com.standard.coffeeshop.repository.customer;

import com.standard.coffeeshop.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}

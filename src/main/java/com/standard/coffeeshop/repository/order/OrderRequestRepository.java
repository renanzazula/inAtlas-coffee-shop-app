package com.standard.coffeeshop.repository.order;

import com.standard.coffeeshop.repository.entity.CustomerEntity;
import com.standard.coffeeshop.repository.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequestEntity, String> {

    List<OrderRequestEntity> findAllByCustomer(CustomerEntity customerEntity);
}

package com.standard.coffeeshop.repository.order;

import com.standard.coffeeshop.repository.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequestEntity, Long> {
}

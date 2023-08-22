package com.inAtlas.coffeeShop.repository.order;

import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequestEntity, Long> {
}

package com.standard.coffeeShop.repository.order;

import com.standard.coffeeShop.repository.entity.OrderRequestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRequestItemsProductRepository extends JpaRepository<OrderRequestItemEntity, Long> {


}

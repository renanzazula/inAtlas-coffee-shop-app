package com.inAtlas.coffeeShop.repository.order;

import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRequestItemsProductRepository extends JpaRepository<OrderRequestItemsEntity, Long> {


}

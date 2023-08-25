package com.inAtlas.coffeeShop.repository.order;

import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRequestItemsProductRepository extends JpaRepository<OrderRequestItemEntity, Long> {


}

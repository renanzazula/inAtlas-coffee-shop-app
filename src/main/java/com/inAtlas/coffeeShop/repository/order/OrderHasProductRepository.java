package com.inAtlas.coffeeShop.repository.order;

import com.inAtlas.coffeeShop.repository.entity.OrderHasProductEntity;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHasProductRepository extends JpaRepository<OrderHasProductEntity, Long> {


}

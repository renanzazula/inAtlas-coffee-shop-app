package com.inAtlas.coffeeShop.repository.discount;

import com.inAtlas.coffeeShop.repository.entity.DiscountEntity;
import com.inAtlas.coffeeShop.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {


    List<DiscountEntity> findAllByToDate(Date toDate);
}

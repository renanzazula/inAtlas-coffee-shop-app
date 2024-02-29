package com.standard.coffeeshop.repository.discount;

import com.standard.coffeeshop.repository.entity.DiscountEntity;
import com.standard.coffeeshop.repository.entity.DiscountTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

    List<DiscountEntity> findAllByToDateAndDiscountType(Date toDate, DiscountTypeEnum discountType);

}

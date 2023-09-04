package com.standard.coffeeShop.service.discount;

import com.standard.coffeeShop.service.dto.DiscountDto;

import java.util.List;


public interface DiscountService {

    DiscountDto add(DiscountDto obj);
    DiscountDto update(Long id, DiscountDto obj);
    void delete(Long id);
    List<DiscountDto> getAll();
    DiscountDto getById(Long id);


}

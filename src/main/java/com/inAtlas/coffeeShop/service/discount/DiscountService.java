package com.inAtlas.coffeeShop.service.discount;

import com.inAtlas.coffeeShop.service.dto.DiscountDto;

import java.util.List;


public interface DiscountService {

    DiscountDto add(DiscountDto obj);
    DiscountDto update(Long id, DiscountDto obj);
    void delete(Long id);
    List<DiscountDto> get();
    DiscountDto getById(Long id);


}

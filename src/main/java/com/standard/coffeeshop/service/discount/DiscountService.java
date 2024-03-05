package com.standard.coffeeshop.service.discount;

import com.standard.coffeeshop.service.dto.DiscountDto;

import java.util.List;


public interface DiscountService {

    DiscountDto add(DiscountDto obj);
    DiscountDto update(String id, DiscountDto obj);
    void delete(String id);
    List<DiscountDto> getAll();
    DiscountDto getById(String id);


}

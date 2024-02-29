package com.standard.coffeeshop.service.discount;

import com.standard.coffeeshop.service.dto.DiscountDto;

import java.util.List;


public interface DiscountService {

    DiscountDto add(DiscountDto obj);
    DiscountDto update(Long id, DiscountDto obj);
    void delete(Long id);
    List<DiscountDto> getAll();
    DiscountDto getById(Long id);


}

package com.standard.coffeeshop.service.product;


import com.standard.coffeeshop.service.dto.ProductDto;

import java.util.List;

public interface ProductService  {

    ProductDto add(ProductDto obj);
    ProductDto update(String id, ProductDto obj);
    void delete(String id);
    List<ProductDto> getAll();
    ProductDto getById(String id);

}

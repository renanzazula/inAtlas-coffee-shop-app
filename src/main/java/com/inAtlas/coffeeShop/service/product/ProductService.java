package com.inAtlas.coffeeShop.service.product;


import com.inAtlas.coffeeShop.service.dto.ProductDto;

import java.util.List;

public interface ProductService  {

    ProductDto add(ProductDto obj);
    ProductDto update(Long id, ProductDto obj);
    void delete(Long id);
    List<ProductDto> get();
    ProductDto getById(Long id);

}

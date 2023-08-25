package com.inAtlas.coffeeShop.service.product;


import com.inAtlas.coffeeShop.service.dto.ProductDto;

import java.util.List;

public interface ProductService  {

    ProductDto add(ProductDto obj);
    ProductDto update(long id, ProductDto obj);
    void delete(long id);
    List<ProductDto> getAll();
    ProductDto getById(long id);

}

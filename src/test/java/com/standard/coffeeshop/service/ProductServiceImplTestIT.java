package com.standard.coffeeshop.service;


import com.standard.coffeeshop.repository.product.ProductRepository;
import com.standard.coffeeshop.service.dto.ProductDto;
import com.standard.coffeeshop.service.product.ProductService;
import com.standard.coffeeshop.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class ProductServiceImplTestIT extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductService productService;

    ProductDto obj = null;

    @BeforeEach
     void setUp() {
        obj = setUpProduct();
        productService = new ProductServiceImpl(productRepository);
        productService.add(obj);
    }

    @Test
     void add() {
        ProductDto saved = productService.add(obj);
        assertNotNull(saved);
        ProductDto found = productService.getById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getStatus(), saved.getStatus());
        assertEquals(found.getQuantity(), saved.getQuantity());
        assertEquals(found.getPriceUnit(), saved.getPriceUnit());
    }

    @Test
     void getById() {
        obj = productService.add(obj);
        ProductDto found = productService.getById(obj.getId());
        assertNotNull(found);
        assertEquals(found.getId(), obj.getId());
    }

    @Test
     void update() {
        obj = productService.add(obj);
        ProductDto found = productService.getById(obj.getId());
        found.setName(PRODUCT_LATTE_TEST +  " update");
        found.setDescription(PRODUCT_LATTE_TEST +  " update");
        found.setQuantity(150l);
        found.setPriceUnit(20.0);

        ProductDto updated =  productService.update(obj.getId(), found);
        assertEquals(found.getId(), updated.getId());
        assertEquals(found.getName(), updated.getName());
        assertEquals(found.getDescription(), updated.getDescription());
        assertEquals(found.getStatus(), updated.getStatus());
        assertEquals(found.getQuantity(), updated.getQuantity());
        assertEquals(found.getPriceUnit(), updated.getPriceUnit());
    }


    @Test
     void delete() {
        obj = productService.add(obj);
        ProductDto found = productService.getById(obj.getId());
        assertNotNull(found);
        productService.delete(found.getId());
    }

    @Test
     void getAll() {
        obj = productService.add(obj);
        List<ProductDto> products = productService.getAll();
        assertNotNull(products);
    }

}
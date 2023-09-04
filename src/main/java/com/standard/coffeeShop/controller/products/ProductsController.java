package com.standard.coffeeShop.controller.products;


import com.standard.coffeeShop.controller.domain.PrintMenu;
import com.standard.coffeeShop.controller.domain.Product;
import com.standard.coffeeShop.service.product.ProductService;
import com.standard.coffeeShop.utils.ConstantsApi;
import com.standard.coffeeShop.utils.functions.DomainToDtoAdapter;
import com.standard.coffeeShop.utils.functions.DtoToDomainAdapter;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Manage Products", tags = "products")
@RestController
@RequestMapping(ConstantsApi.PRODUCT)
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({""})
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll()
                .stream()
                .map(DtoToDomainAdapter.productDtoToProductDomainAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping({"/print/menu"})
    public ResponseEntity<List<PrintMenu>> getPrintMenu() {
        return new ResponseEntity<>(productService.getAll()
                .stream()
                .map(DtoToDomainAdapter.productDtoToPrintMenuAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Product> getById(@PathVariable long id) {
        return new ResponseEntity<>(DtoToDomainAdapter.productDtoToProductDomainAdapter
                .apply(productService.getById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product obj) {
        return new ResponseEntity<>(DtoToDomainAdapter.productDtoToProductDomainAdapter
                .apply(productService.add(DomainToDtoAdapter.productToProductDtoAdapter.apply(obj))), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Product> update(@PathVariable long id, @RequestBody Product obj) {
        return new ResponseEntity<>(DtoToDomainAdapter.productDtoToProductDomainAdapter
                .apply(productService.update(id, DomainToDtoAdapter.productToProductDtoAdapter.apply(obj))), HttpStatus.OK);
    }


}

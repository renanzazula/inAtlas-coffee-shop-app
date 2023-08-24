package com.inAtlas.coffeeShop.controller.discounts;

import com.inAtlas.coffeeShop.controller.domain.Discount;
import com.inAtlas.coffeeShop.service.discount.DiscountService;
import com.inAtlas.coffeeShop.utils.ConstantsApi;
import com.inAtlas.coffeeShop.utils.functions.DomainToDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.DtoToDomainAdapter;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Manage discounts", tags = "discount")
@RestController
@RequestMapping(ConstantsApi.DISCOUNT)
public class DiscountsController {

    private final DiscountService discountService;

    public DiscountsController(DiscountService discountService) {
        this.discountService = discountService;
    }


    @GetMapping({""})
    public ResponseEntity<List<Discount>> getAll() {
        return new ResponseEntity<>(discountService.getAll()
                .stream()
                .map(DtoToDomainAdapter.discountDtoToDiscountAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Discount> getById(@PathVariable Long id) {
        return new ResponseEntity<>(DtoToDomainAdapter.discountDtoToDiscountAdapter
                .apply(discountService.getById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Discount> add(@RequestBody Discount obj) {
        return new ResponseEntity<>(DtoToDomainAdapter.discountDtoToDiscountAdapter
                .apply(discountService.add(DomainToDtoAdapter
                        .discountToDiscountDtoAdapter
                        .apply(obj))), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        discountService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Discount> update(@PathVariable Long id, @RequestBody Discount obj) {
        return new ResponseEntity<>(DtoToDomainAdapter
                .discountDtoToDiscountAdapter
                .apply(discountService.update(id,
                        DomainToDtoAdapter.discountToDiscountDtoAdapter
                                .apply(obj))), HttpStatus.OK);
    }

}

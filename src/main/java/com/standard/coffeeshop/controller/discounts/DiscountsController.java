package com.standard.coffeeshop.controller.discounts;

import com.standard.coffeeshop.controller.domain.Discount;
import com.standard.coffeeshop.service.discount.DiscountService;
import com.standard.coffeeshop.utils.ConstantsApi;
import com.standard.coffeeshop.utils.functions.DomainToDtoAdapter;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Manage discounts", tags = "discount")
@RestController
@AllArgsConstructor
@RequestMapping(ConstantsApi.DISCOUNT)
public class DiscountsController {

    private final DiscountService discountService;

    @GetMapping({""})
    public ResponseEntity<List<Discount>> getAll() {
        return new ResponseEntity<>(discountService.getAll()
                .stream()
                .map(DtoToDomainAdapter.discountDtoToDiscountAdapter)
                .toList(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Discount> getById(@PathVariable String id) {
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
    public void delete(@PathVariable String id) {
        discountService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Discount> update(@PathVariable String id, @RequestBody Discount obj) {
        return new ResponseEntity<>(DtoToDomainAdapter
                .discountDtoToDiscountAdapter
                .apply(discountService.update(id,
                        DomainToDtoAdapter.discountToDiscountDtoAdapter
                                .apply(obj))), HttpStatus.OK);
    }

}

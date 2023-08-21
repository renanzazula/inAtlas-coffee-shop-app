package com.inAtlas.coffeeShop.controller.orders;

import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.service.order.OrderRequestService;
import com.inAtlas.coffeeShop.utils.ConstantsApi;
import com.inAtlas.coffeeShop.utils.functions.DomainToDtoAdapter;
import com.inAtlas.coffeeShop.utils.functions.DtoToDomainAdapter;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Manage Orders", tags = "order")
@RestController
@RequestMapping(ConstantsApi.ORDER)
public class OrdersController {

    private final OrderRequestService orderRequestService;

    public OrdersController(OrderRequestService orderRequestService) {
        this.orderRequestService = orderRequestService;
    }

    @GetMapping({""})
    public ResponseEntity<List<OrderRequest>> get() {
        return new ResponseEntity<>(orderRequestService.get()
                .stream()
                .map(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<OrderRequest> getById(@PathVariable Long id) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderRequest> add(@RequestBody OrderRequest obj) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.add(DomainToDtoAdapter.orderRequestToOrderRequestDtoAdapter.apply(obj))), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderRequestService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<OrderRequest> update(@PathVariable Long id, @RequestBody OrderRequest obj) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.update(id, DomainToDtoAdapter.orderRequestToOrderRequestDtoAdapter.apply(obj))), HttpStatus.OK);
    }




}

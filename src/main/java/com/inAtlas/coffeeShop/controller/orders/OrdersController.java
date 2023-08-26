package com.inAtlas.coffeeShop.controller.orders;

import com.inAtlas.coffeeShop.controller.domain.OrderRequest;
import com.inAtlas.coffeeShop.service.order.OrderRequestService;
import com.inAtlas.coffeeShop.utils.ConstantsApi;
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
    public ResponseEntity<List<OrderRequest>> getAllOrders() {
        return new ResponseEntity<>(orderRequestService.getAll()
                .stream()
                .map(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping({"/{orderId}"})
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(orderId)), HttpStatus.OK);
    }

    @PostMapping("/open")
    public ResponseEntity<OrderRequest> openOrder() {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.openOrder()), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{orderId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderRequest> delete(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.delete(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/close"})
    public ResponseEntity<OrderRequest> closeOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.closeOrder(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/reOpen"})
    public ResponseEntity<OrderRequest> openOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.reopenOrder(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/receipt"})
    public ResponseEntity<OrderRequest> printOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/add/{productId}"})
    public ResponseEntity<OrderRequest> addProduct(@PathVariable long orderId, @PathVariable long productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.addProduct(orderId, productId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/remove/{productId}"})
    public ResponseEntity<OrderRequest> removeProduct(@PathVariable long orderId, @PathVariable long productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.removeProduct(orderId, productId)), HttpStatus.OK);
    }

}

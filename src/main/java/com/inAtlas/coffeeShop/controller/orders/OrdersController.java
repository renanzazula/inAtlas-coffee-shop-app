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
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable Long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(orderId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderRequest> createNewOrder() {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.createNewOrder()), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{orderId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderRequest> delete(@PathVariable Long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.delete(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/close"})
    public ResponseEntity<OrderRequest> closeOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.closeOrder(orderId)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/add/{productId}/{quantity}"})
    public ResponseEntity<OrderRequest> addNewProduct(@PathVariable Long orderId, @PathVariable Long productId, Long quantity) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.addNewProduct(orderId, productId, quantity)), HttpStatus.OK);
    }

    @PutMapping({"/{orderId}/remove/{productId}/{quantity}"})
    public ResponseEntity<OrderRequest> addRemoveProduct(@PathVariable Long orderId, @PathVariable Long productId, Long quantity) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.removeProduct(orderId, productId, quantity)), HttpStatus.OK);
    }

}

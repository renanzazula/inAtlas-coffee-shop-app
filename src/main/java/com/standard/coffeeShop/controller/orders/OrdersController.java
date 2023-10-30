package com.standard.coffeeShop.controller.orders;

import com.standard.coffeeShop.controller.domain.OrderRequest;
import com.standard.coffeeShop.controller.domain.PrintReceipt;
import com.standard.coffeeShop.service.order.OrderRequestService;
import com.standard.coffeeShop.utils.ConstantsApi;
import com.standard.coffeeShop.utils.functions.DtoToDomainAdapter;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Manage Orders", tags = "order")
@RestController
@AllArgsConstructor
@RequestMapping(OrdersController.ORDER_CONTROLLER_BASE_URL)
public class OrdersController {
    public static final String ORDER_CONTROLLER_BASE_URL = ConstantsApi.ORDER;

    private final OrderRequestService orderRequestService;

    @PreAuthorize("hasAnyAuthority('ORDER_SEARCH')")
    @GetMapping({""})
    public ResponseEntity<List<OrderRequest>> getAllOrders() {
        return new ResponseEntity<>(orderRequestService.getAll()
                .stream()
                .map(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_SEARCH')")
    @GetMapping({"/{orderId}"})
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_OPEN')")
    @PostMapping("/open")
    public ResponseEntity<OrderRequest> openOrder() {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.openOrder()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_DELETE')")
    @PutMapping({"/{orderId}/delete"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderRequest> delete(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.delete(orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_CLOSE')")
    @PutMapping({"/{orderId}/close"})
    public ResponseEntity<OrderRequest> closeOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.closeOrder(orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_REOPEN')")
    @PutMapping({"/{orderId}/reopen"})
    public ResponseEntity<OrderRequest> reopenOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.reopenOrder(orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_PRINT')")
    @GetMapping({"/{orderId}/receipt"})
    public ResponseEntity<PrintReceipt> printOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.printReceiptDtoToPrintReceiptAdapter
                .apply(orderRequestService.printOrder(orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_ADD')")
    @PutMapping({"/{orderId}/add/{productId}"})
    public ResponseEntity<OrderRequest> addProduct(@PathVariable long orderId, @PathVariable long productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.addProduct(orderId, productId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_REMOVE')")
    @PutMapping({"/{orderId}/remove/{productId}"})
    public ResponseEntity<OrderRequest> removeProduct(@PathVariable long orderId, @PathVariable long productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.removeProduct(orderId, productId)), HttpStatus.OK);
    }

}

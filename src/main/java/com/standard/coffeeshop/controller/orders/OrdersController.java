package com.standard.coffeeshop.controller.orders;

import com.standard.coffeeshop.controller.domain.OrderRequest;
import com.standard.coffeeshop.controller.domain.PrintReceipt;
import com.standard.coffeeshop.service.order.OrderRequestService;
import com.standard.coffeeshop.utils.ConstantsApi;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
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
    @GetMapping({"/"})
    public ResponseEntity<List<OrderRequest>> getAllOrders(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(orderRequestService.getAll(customerId)
                .stream()
                .map(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_SEARCH')")
    @GetMapping({"/{orderId}"})
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable("customerId") String customerId, @PathVariable String orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.getById(customerId,orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_OPEN')")
    @PostMapping("/open")
    public ResponseEntity<OrderRequest> openOrder(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.openOrder(customerId)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_DELETE')")
    @PutMapping({"/{orderId}/delete"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderRequest> delete(@PathVariable("customerId") String customerId, @PathVariable String orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.delete(customerId,orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_CLOSE')")
    @PutMapping({"/{orderId}/close"})
    public ResponseEntity<OrderRequest> closeOrder(@PathVariable("customerId") String customerId, @PathVariable String orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.closeOrder(customerId,orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_REOPEN')")
    @PutMapping({"/{orderId}/reopen"})
    public ResponseEntity<OrderRequest> reopenOrder(@PathVariable("customerId") String customerId, @PathVariable String orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.reopenOrder(customerId,orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_PRINT')")
    @GetMapping({"/{orderId}/receipt"})
    public ResponseEntity<PrintReceipt> printOrder(@PathVariable("customerId") String customerId, @PathVariable String orderId) {
        return new ResponseEntity<>(DtoToDomainAdapter.printReceiptDtoToPrintReceiptAdapter
                .apply(orderRequestService.printOrder(customerId,orderId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_ADD')")
    @PutMapping({"/{orderId}/add/{productId}"})
    public ResponseEntity<OrderRequest> addProduct(@PathVariable("customerId") String customerId, @PathVariable String orderId, @PathVariable String productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.addProduct(customerId,orderId, productId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ORDER_REMOVE')")
    @PutMapping({"/{orderId}/remove/{productId}"})
    public ResponseEntity<OrderRequest> removeProduct(@PathVariable("customerId") String customerId, @PathVariable String orderId, @PathVariable String productId) {
        return new ResponseEntity<>(DtoToDomainAdapter.orderRequestDtoToOrderRequestAdapter
                .apply(orderRequestService.removeProduct(customerId,orderId, productId)), HttpStatus.OK);
    }

}

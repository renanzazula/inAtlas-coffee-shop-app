package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestItemsEntity;
import com.inAtlas.coffeeShop.repository.entity.StatusOrderEnum;
import com.inAtlas.coffeeShop.repository.order.OrderRequestItemsProductRepository;
import com.inAtlas.coffeeShop.repository.order.OrderRequestRepository;
import com.inAtlas.coffeeShop.repository.product.ProductRepository;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.utils.Constants;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;
    private final OrderRequestItemsProductRepository orderRequestItemsProductRepository;


    public OrderRequestServiceImpl(OrderRequestRepository orderRequestRepository, ProductRepository productRepository, OrderRequestItemsProductRepository orderRequestItemsProductRepository) {
        this.orderRequestRepository = orderRequestRepository;
        this.productRepository = productRepository;
        this.orderRequestItemsProductRepository = orderRequestItemsProductRepository;
    }

    @Override
    @Transactional
    public OrderRequestDto createNewOrder() {
        OrderRequestEntity entity = new OrderRequestEntity();
        entity.setTotalAmount(0D);
        entity.setTotalQuantity(0L);
        entity.setTotalDiscount(0D);
        entity.setStatus(StatusOrderEnum.OPEN);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.save(entity));
    }

    @Override
    @Transactional
    public OrderRequestDto addProduct(long orderId, long productId) {
        OrderRequestEntity orderRequest = orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND));

        OrderRequestItemsEntity orderRequestItem = new OrderRequestItemsEntity();
        orderRequestItem.setProduct(productRepository.getById(productId));
        orderRequestItem.setPriceUnit(productRepository.getById(productId).getPriceUnit());
        orderRequest.addOrderItem(orderRequestItem);
        orderRequestRepository.saveAndFlush(orderRequest);

        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.getById(orderId);
        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
        orderRequestEntityTotals.setTotalAmount(calculateSumAmount(orderRequestEntityTotals.getOrderItems()));

        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(orderRequestEntityTotals));
    }


    @Override
    @Transactional
    public OrderRequestDto removeProduct(long orderId, long productId) {
        OrderRequestEntity orderRequestToUpdate = orderRequestRepository.getById(orderId);

        Optional<OrderRequestItemsEntity> itemProductToRemove =
                orderRequestToUpdate
                        .getOrderItems()
                        .stream()
                        .findFirst()
                        .filter(orderHasProduct -> orderHasProduct.getProduct().getId() == (productId));

        orderRequestToUpdate.removeOrderHasProduct(itemProductToRemove.get());
        OrderRequestEntity orderRequestToUpdated = orderRequestRepository.saveAndFlush(orderRequestToUpdate);

        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.getById(orderId);
        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
        orderRequestEntityTotals.setTotalAmount(calculateMinusAmount(orderRequestEntityTotals.getOrderItems()));
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(orderRequestToUpdated);
    }

    @Override
    @Transactional
    public OrderRequestDto closeOrder(long orderId) {
        orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);

        orderRequestEntity.setTotalQuantity(calculateTotalQuantity(orderRequestEntity.getOrderItems()));
        orderRequestEntity.setTotalAmount(calculateSumAmount(orderRequestEntity.getOrderItems()));

        orderRequestEntity.setStatus(StatusOrderEnum.CLOSE);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(orderRequestEntity));
    }

    @Override
    @Transactional
    public OrderRequestDto delete(long orderId) {
        orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        OrderRequestEntity entity = orderRequestRepository.getById(orderId);
        entity.setStatus(StatusOrderEnum.DELETE);
        orderRequestRepository.save(entity);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(entity));

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRequestDto> getAll() {
        return orderRequestRepository.findAll()
                .stream()
                .map(EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRequestDto getById(long id) {
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(Constants.ORDER_NOT_FOUND)));
    }

    private long calculateTotalQuantity(List<OrderRequestItemsEntity> orderHasProducts) {
        return orderHasProducts.size();
    }

    private Double calculateSumAmount(List<OrderRequestItemsEntity> orderHasProducts) {
        return orderHasProducts.stream().mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit()).sum();
    }

    private Double calculateMinusAmount(List<OrderRequestItemsEntity> orderHasProducts) {
        return orderHasProducts
                .stream()
                .mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit())
                .min().orElse(0);
    }

    private Double calculateTotalDiscount(double originalPrice, double discountPercentage) {
        double discountAmount = (discountPercentage / 100) * originalPrice;
        return 10D;
    }

    private Double calculatePromotionDiscount(OrderRequestEntity orderRequest) {
        return 10D;
    }

}

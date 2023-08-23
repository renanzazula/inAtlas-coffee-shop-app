package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.repository.entity.OrderHasProductEntity;
import com.inAtlas.coffeeShop.repository.entity.StatusOrderEnum;
import com.inAtlas.coffeeShop.repository.order.OrderHasProductRepository;
import com.inAtlas.coffeeShop.repository.product.ProductRepository;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.repository.order.OrderRequestRepository;
import com.inAtlas.coffeeShop.utils.Constants;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;
    private final OrderHasProductRepository orderHasProductRepository;


    public OrderRequestServiceImpl(OrderRequestRepository orderRequestRepository, ProductRepository productRepository, OrderHasProductRepository orderHasProductRepository) {
        this.orderRequestRepository = orderRequestRepository;
        this.productRepository = productRepository;
        this.orderHasProductRepository = orderHasProductRepository;
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
        orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND));

        OrderHasProductEntity orderHasProductEntity = new OrderHasProductEntity();
        orderHasProductEntity.setProduct(productRepository.getById(productId));
        orderHasProductEntity.setPriceUnit(productRepository.getById(productId).getPriceUnit());
        orderHasProductEntity.setOrderRequest(orderRequestRepository.getById(orderId));

        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);
        orderRequestEntity.getOrderHasProduct().add(orderHasProductEntity);
        orderRequestRepository.saveAndFlush(orderRequestEntity);

        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.getById(orderId);
        // orderRequestEntityTotals.setPromotionDiscount(0);
        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderHasProduct()));
        orderRequestEntityTotals.setTotalAmount(calculateSumAmount(orderRequestEntityTotals.getOrderHasProduct()));
        orderRequestEntityTotals.setTotalDiscount(calculateTotalDiscount(orderRequestEntityTotals.getTotalDiscount(), orderRequestEntityTotals.getOrderHasProduct()));

        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(orderRequestEntityTotals));
    }


    @Override
    @Transactional
    public OrderRequestDto removeProduct(long orderId, long productId) {
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);

        Optional<OrderHasProductEntity> orderHasProductToRemove =
                orderRequestEntity
                        .getOrderHasProduct()
                        .stream()
                        .findFirst()
                        .filter(orderHasProduct -> orderHasProduct.getProduct().getId()==(productId));

        Set<OrderHasProductEntity> orderHasProductToUpdateEntity = new HashSet<>();
        orderRequestEntity.getOrderHasProduct().forEach(orderHasProductEntity -> {
            if(orderHasProductEntity.getOrderRequestHasProductId() != orderHasProductToRemove.get().getOrderRequestHasProductId()){
                orderHasProductToUpdateEntity.add(orderHasProductEntity);
            }
        });
        orderRequestEntity.setOrderHasProduct(orderHasProductToUpdateEntity);
        orderRequestRepository.save(orderRequestEntity);

        OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.getById(orderId);
        orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderHasProduct()));
        orderRequestEntityTotals.setTotalAmount(calculateMinusAmount(orderRequestEntityTotals.getOrderHasProduct()));
        orderRequestEntityTotals.setTotalDiscount(calculateTotalDiscount(orderRequestEntityTotals.getTotalDiscount(), orderRequestEntityTotals.getOrderHasProduct()));

        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(orderRequestRepository.save(orderRequestEntityTotals));
    }

    @Override
    @Transactional
    public OrderRequestDto closeOrder(long orderId) {
        orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);

        orderRequestEntity.setTotalQuantity(calculateTotalQuantity(orderRequestEntity.getOrderHasProduct()));
        orderRequestEntity.setTotalAmount(calculateSumAmount(orderRequestEntity.getOrderHasProduct()));
        orderRequestEntity.setTotalDiscount(calculateTotalDiscount(orderRequestEntity.getTotalDiscount(), orderRequestEntity.getOrderHasProduct()));

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

    private long calculateTotalQuantity(Set<OrderHasProductEntity> orderHasProducts) {
        return orderHasProducts.size();
    }

    private Double calculateSumAmount(Set<OrderHasProductEntity> orderHasProducts) {
        return orderHasProducts.stream().mapToDouble(orderHasProductEntity -> orderHasProductEntity.getProduct().getPriceUnit()).sum();
    }

    private Double calculateMinusAmount(Set<OrderHasProductEntity> orderHasProducts) {
        return orderHasProducts
                .stream()
                .mapToDouble(orderHasProductEntity -> orderHasProductEntity.getProduct().getPriceUnit())
                .min().orElse(0);
    }

    private Double calculateTotalDiscount(double actualAmount, Set<OrderHasProductEntity> orderHasProducts) {
        return 10D;
    }

    private Double calculatePromotionDiscount(OrderRequestEntity orderRequest){
      return 10D;
    }

}

package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.repository.entity.OrderHasProductEntity;
import com.inAtlas.coffeeShop.repository.entity.ProductEntity;
import com.inAtlas.coffeeShop.repository.entity.StatusOrderEnum;
import com.inAtlas.coffeeShop.repository.order.OrderHasProductRepository;
import com.inAtlas.coffeeShop.repository.product.ProductRepository;
import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.repository.order.OrderRequestRepository;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    public static final String ORDER_NOT_FOUND = "Order not Found!";

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
    public OrderRequestDto addNewProduct(Long orderId, Long productId, Long quantity) {

        OrderHasProductEntity orderHasProductEntity = new OrderHasProductEntity();
        orderHasProductEntity.setProduct(productRepository.getById(productId));
        orderHasProductEntity.setPriceUnit(productRepository.getById(productId).getPriceUnit());
        orderHasProductEntity.setOrderRequest(orderRequestRepository.getById(orderId));
        orderHasProductRepository.save(orderHasProductEntity);

        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);
        // orderRequestEntity.setPromotionDiscount(0);

        orderRequestEntity.setTotalQuantity(0L);
        orderRequestEntity.setTotalAmount(0D);
        orderRequestEntity.setTotalDiscount(0D);
        orderRequestRepository.save(orderRequestEntity);

        OrderRequestEntity x = orderRequestRepository.getById(orderId);

        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(x);
    }


    @Override
    @Transactional
    public OrderRequestDto removeProduct(Long orderId, Long productId, Long quantity) {
        OrderRequestEntity entity = orderRequestRepository.getById(orderId);
        entity.getOrderHasProduct().removeIf(orderHasProductItem -> orderHasProductItem.getId() == productId);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(entity));
    }

    @Override
    @Transactional
    public OrderRequestDto closeOrder(Long orderId) {
        OrderRequestEntity entity = orderRequestRepository.getById(orderId);
        entity.setStatus(StatusOrderEnum.CLOSE);
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.saveAndFlush(entity));
    }

    @Override
    @Transactional
    public OrderRequestDto delete(Long id) {
        OrderRequestEntity entity = orderRequestRepository.getById(id);
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
    public OrderRequestDto getById(Long id) {
        return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                .apply(orderRequestRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(ORDER_NOT_FOUND)));
    }

}

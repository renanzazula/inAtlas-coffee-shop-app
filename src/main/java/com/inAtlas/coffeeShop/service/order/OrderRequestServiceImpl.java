package com.inAtlas.coffeeShop.service.order;


import com.inAtlas.coffeeShop.service.dto.OrderRequestDto;
import com.inAtlas.coffeeShop.repository.entity.OrderRequestEntity;
import com.inAtlas.coffeeShop.repository.order.OrderRequestRepository;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;

    public OrderRequestServiceImpl(OrderRequestRepository orderRequestRepository) {
        this.orderRequestRepository = orderRequestRepository;
    }

    @Override
    @Transactional
    public OrderRequestDto add(OrderRequestDto obj) {
        return null;
    }

    @Override
    @Transactional
    public OrderRequestDto update(Long id, OrderRequestDto obj) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(id);
        orderRequestEntity.setStatus("INATIVO");
        orderRequestRepository.save(orderRequestEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRequestDto> get() {
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
                        new EntityNotFoundException("Order not Found!")));
    }

}

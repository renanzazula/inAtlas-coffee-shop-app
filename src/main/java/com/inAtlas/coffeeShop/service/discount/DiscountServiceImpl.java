package com.inAtlas.coffeeShop.service.discount;

import com.inAtlas.coffeeShop.service.dto.DiscountDto;
import com.inAtlas.coffeeShop.repository.discount.DiscountRepository;
import com.inAtlas.coffeeShop.repository.entity.DiscountEntity;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    
    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    @Transactional
    public DiscountDto add(DiscountDto obj) {
        return null;
    }

    @Override
    @Transactional
    public DiscountDto update(Long id, DiscountDto obj) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DiscountEntity DiscountEntity = discountRepository.getById(id);
        DiscountEntity.setStatus("INATIVO");
        discountRepository.save(DiscountEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiscountDto> get() {
        return discountRepository.findAll()
                .stream()
                .map(EntityToDtoAdapter.discountEntityToDiscountDtoAdapter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DiscountDto getById(Long id) {
        return EntityToDtoAdapter.discountEntityToDiscountDtoAdapter
                .apply(discountRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Discount not Found!")));
    }



}

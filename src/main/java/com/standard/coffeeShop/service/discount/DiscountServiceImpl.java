package com.standard.coffeeShop.service.discount;

import com.standard.coffeeShop.repository.discount.DiscountRepository;
import com.standard.coffeeShop.repository.entity.DiscountEntity;
import com.standard.coffeeShop.repository.entity.ComboItemEntity;
import com.standard.coffeeShop.repository.entity.DiscountTypeEnum;
import com.standard.coffeeShop.repository.product.ProductRepository;
import com.standard.coffeeShop.service.dto.DiscountDto;
import com.standard.coffeeShop.utils.Constants;
import com.standard.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public DiscountDto add(DiscountDto obj) {
        DiscountEntity discountEntity = new DiscountEntity();
        switch (obj.getDiscountType()){
            case "DISCOUNT_ORDER":
                configureDiscountOrder(obj);
                return getDiscountDto(obj, discountEntity);
            case "DISCOUNT_PRODUCT":
                configureDiscountProduct(obj);
                return getDiscountDto(obj, discountEntity);
            case "DISCOUNT_PRODUCT_COMBO":
                configureDiscountProductCombo(obj);
                return getDiscountDto(obj, discountEntity);
            default:
                throw new IllegalArgumentException("Type not found");
        }
    }

    @Override
    @Transactional
    public DiscountDto update(Long id, DiscountDto discountDto) {
        DiscountEntity discountEntity = discountRepository.getById(id);
        return getDiscountDto(discountDto, discountEntity);
    }

    private DiscountDto getDiscountDto(DiscountDto obj, DiscountEntity discountEntity) {
        discountEntity.setStatus(obj.getStatus());
        discountEntity.setFromDate(obj.getFromDate());
        discountEntity.setToDate(obj.getToDate());
        discountEntity.setDiscount(obj.getDiscount());
        discountEntity.setAmountFrom(obj.getAmountFrom());
        discountEntity.setAmountTo(obj.getAmountTo());
        for (int i = 0; i < obj.getDiscountItems().size(); i++) {
            ComboItemEntity discountItem = new ComboItemEntity();
            discountItem.setProduct(productRepository.getById(obj.getDiscountItems().get(i).getProduct().getId()));
            discountEntity.addDiscountItem(discountItem);
        }
        return EntityToDtoAdapter.discountEntityToDiscountDtoAdapter.apply(discountRepository.saveAndFlush(discountEntity));
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
    public List<DiscountDto> getAll() {
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
                        new EntityNotFoundException(Constants.DISCOUNT_NOT_FOUND)));
    }

    private DiscountDto configureDiscountOrder(DiscountDto obj){
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setTitle(obj.getTitle());
        discountEntity.setStatus(obj.getStatus());
        discountEntity.setFromDate(obj.getFromDate());
        discountEntity.setToDate(obj.getToDate());
        discountEntity.setAmountFrom(obj.getAmountFrom());
        discountEntity.setAmountTo(obj.getAmountTo());
        discountEntity.setDiscount(obj.getDiscount());
        discountEntity.setDiscountType(DiscountTypeEnum.valueOf(obj.getDiscountType()));
        return EntityToDtoAdapter.discountEntityToDiscountDtoAdapter.apply(discountRepository.saveAndFlush(discountEntity));
    }

    private DiscountDto configureDiscountProduct(DiscountDto obj){
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setTitle(obj.getTitle());
        discountEntity.setStatus(obj.getStatus());
        discountEntity.setFromDate(obj.getFromDate());
        discountEntity.setToDate(obj.getToDate());
        discountEntity.setAmountFrom(obj.getAmountFrom());
        discountEntity.setAmountTo(obj.getAmountTo());
        discountEntity.setDiscount(obj.getDiscount());
        discountEntity.setDiscountType(DiscountTypeEnum.valueOf(obj.getDiscountType()));
        for (int i = 0; i < obj.getDiscountItems().size(); i++) {
            ComboItemEntity discountItem = new ComboItemEntity();
            discountItem.setProduct(productRepository.getById(obj.getDiscountItems().get(i).getProduct().getId()));
            discountEntity.addDiscountItem(discountItem);
        }
        return EntityToDtoAdapter.discountEntityToDiscountDtoAdapter.apply(discountRepository.saveAndFlush(discountEntity));
    }

    private DiscountDto configureDiscountProductCombo(DiscountDto obj){
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setTitle(obj.getTitle());
        discountEntity.setStatus(obj.getStatus());
        discountEntity.setFromDate(obj.getFromDate());
        discountEntity.setToDate(obj.getToDate());
        discountEntity.setAmountFrom(obj.getAmountFrom());
        discountEntity.setAmountTo(obj.getAmountTo());
        discountEntity.setDiscount(obj.getDiscount());
        discountEntity.setDiscountType(DiscountTypeEnum.valueOf(obj.getDiscountType()));
        for (int i = 0; i < obj.getDiscountItems().size(); i++) {
            ComboItemEntity discountItem = new ComboItemEntity();
            discountItem.setProduct(productRepository.getById(obj.getDiscountItems().get(i).getProduct().getId()));
            discountEntity.addDiscountItem(discountItem);
        }
        return EntityToDtoAdapter.discountEntityToDiscountDtoAdapter.apply(discountRepository.saveAndFlush(discountEntity));
    }

}

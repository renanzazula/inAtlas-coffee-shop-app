package com.standard.coffeeshop.service.product;

import com.standard.coffeeshop.repository.entity.ProductEntity;
import com.standard.coffeeshop.repository.product.ProductRepository;
import com.standard.coffeeshop.service.dto.ProductDto;
import com.standard.coffeeshop.utils.Constants;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductDto add(ProductDto obj) {
        // add validation if product exist with this name
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(obj.getName());
        productEntity.setDescription(obj.getDescription());
        productEntity.setStatus(obj.getStatus());
        productEntity.setQuantity(obj.getQuantity());
        productEntity.setPriceUnit(obj.getPriceUnit());
        productEntity.setWhoCreate(0l);
        productEntity.setWhoUpdate(0l);
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public ProductDto update(String id, ProductDto obj) {
        ProductEntity productEntity = productRepository.getById(id);
        productEntity.setName(obj.getName());
        productEntity.setDescription(obj.getDescription());
        productEntity.setStatus(obj.getStatus());
        productEntity.setQuantity(obj.getQuantity());
        productEntity.setPriceUnit(obj.getPriceUnit());
        productEntity.setWhoUpdate(0l);
        productRepository.saveAndFlush(productEntity);
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.getById(id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND));
        ProductEntity productEntity = productRepository.getById(id);
        productEntity.setStatus("INATIVO");
        productRepository.save(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(EntityToDtoAdapter.productEntityToProductDtoAdapter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(String id) {
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND)));
    }

}

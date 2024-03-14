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
import java.util.Optional;

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
        productEntity.setWhoCreate(0L);
        productEntity.setWhoUpdate(0L);
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
        productEntity.setWhoUpdate(0L);
        productRepository.saveAndFlush(productEntity);
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.getById(id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            ProductEntity productEntity = productRepository.getById(id);
            productEntity.setStatus("INATIVO");
            productRepository.save(productEntity);
        } else {
            throw new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(EntityToDtoAdapter.productEntityToProductDtoAdapter).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(String id) {
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND)));
    }

}

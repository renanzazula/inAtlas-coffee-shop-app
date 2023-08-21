package com.inAtlas.coffeeShop.service.product;

import com.inAtlas.coffeeShop.repository.entity.ProductEntity;
import com.inAtlas.coffeeShop.repository.product.ProductRepository;
import com.inAtlas.coffeeShop.service.dto.ProductDto;
import com.inAtlas.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    public static final String PRODUCT_NOT_FOUND = "Product not Found!";

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductDto add(ProductDto obj) {
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
    public ProductDto update(Long id, ProductDto obj) {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
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
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.getById(id);
        productEntity.setStatus("INATIVO");
        productRepository.save(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> get() {
        return productRepository.findAll().stream().map(EntityToDtoAdapter.productEntityToProductDtoAdapter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Long id) {
        return EntityToDtoAdapter.productEntityToProductDtoAdapter.apply(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND)));
    }

}

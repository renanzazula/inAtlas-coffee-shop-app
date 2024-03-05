package com.standard.coffeeshop.service.order;


import com.standard.coffeeshop.repository.customer.CustomerRepository;
import com.standard.coffeeshop.repository.discount.DiscountRepository;
import com.standard.coffeeshop.repository.entity.*;
import com.standard.coffeeshop.repository.order.OrderRequestRepository;
import com.standard.coffeeshop.repository.product.ProductRepository;
import com.standard.coffeeshop.service.dto.DiscountDto;
import com.standard.coffeeshop.service.dto.OrderRequestDto;
import com.standard.coffeeshop.service.dto.PrintReceiptDto;
import com.standard.coffeeshop.service.dto.PrintReceiptItemDto;
import com.standard.coffeeshop.utils.Constants;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    private final CustomerRepository customerRepository;

    public OrderRequestServiceImpl(OrderRequestRepository orderRequestRepository, ProductRepository productRepository, DiscountRepository discountRepository, CustomerRepository customerRepository) {
        this.orderRequestRepository = orderRequestRepository;
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public OrderRequestDto openOrder(String customerId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            OrderRequestEntity entity = new OrderRequestEntity();
            entity.setTotalAmount(0D);
            entity.setTotalQuantity(0L);
            entity.setTotalDiscount(0D);
            entity.setStatus(StatusOrderEnum.OPEN);
            entity.setCustomer(customerOptional.get());
            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(orderRequestRepository.save(entity));
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    @Transactional
    public OrderRequestDto addProduct(String customerId, String orderId, String productId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {

            OrderRequestEntity orderRequest = orderRequestRepository.findById(orderId)
                    .orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException(Constants.PRODUCT_NOT_FOUND + productId));
            OrderRequestItemEntity orderRequestItem = new OrderRequestItemEntity();
            orderRequestItem.setProduct(product);
            orderRequestItem.setPriceUnit(product.getPriceUnit());
            orderRequest.addOrderItem(orderRequestItem);
            OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.saveAndFlush(orderRequest);

            orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
            orderRequestEntityTotals.setTotalAmount(calculateSumAmount(orderRequestEntityTotals.getOrderItems()));

            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                    .apply(orderRequestRepository.saveAndFlush(orderRequestEntityTotals));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }


    @Override
    @Transactional
    public OrderRequestDto removeProduct(String customerId, String orderId, String productId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            OrderRequestEntity orderRequestToUpdate = orderRequestRepository
                    .findById(orderId).orElseThrow(() ->
                            new EntityNotFoundException(Constants.ORDER_NOT_FOUND));

            Optional<OrderRequestItemEntity> itemProductToRemove =
                    Optional.ofNullable(orderRequestToUpdate
                            .getOrderItems()
                            .stream()
                            .findFirst()
                            .filter(orderHasProduct -> orderHasProduct.getProduct().getId() == (productId))
                            .orElseThrow(() -> new IllegalArgumentException(Constants.PRODUCT_NOT_FOUND + productId)));

            orderRequestToUpdate.removeOrderItem(itemProductToRemove.get());

            OrderRequestEntity orderRequestEntityTotals = orderRequestRepository.saveAndFlush(orderRequestToUpdate);
            orderRequestEntityTotals.setTotalQuantity(calculateTotalQuantity(orderRequestEntityTotals.getOrderItems()));
            orderRequestEntityTotals.setTotalAmount(calculateMinusAmount(orderRequestEntityTotals.getOrderItems(), orderRequestEntityTotals.getTotalAmount()));
            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter.apply(orderRequestRepository.saveAndFlush(orderRequestEntityTotals));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    @Override
    @Transactional
    public OrderRequestDto closeOrder(String customerId, String orderId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {

            OrderRequestEntity orderRequestEntity = orderRequestRepository
                    .findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));

            orderRequestEntity.setTotalQuantity(calculateTotalQuantity(orderRequestEntity.getOrderItems()));
            orderRequestEntity.setTotalAmount(calculateSumAmount(orderRequestEntity.getOrderItems()));

            DiscountDto discountDto = calculateTotalDiscount(orderId);
            orderRequestEntity.setTotalDiscount(discountDto != null ? discountDto.getDiscount() : 0D);
            orderRequestEntity.setTotalAmount(orderRequestEntity.getTotalAmount() - orderRequestEntity.getTotalDiscount());
            orderRequestEntity.setStatus(StatusOrderEnum.CLOSE);
            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                    .apply(orderRequestRepository.saveAndFlush(orderRequestEntity));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    @Override
    @Transactional
    public OrderRequestDto reopenOrder(String customerId, String orderId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            OrderRequestEntity orderRequestEntity = orderRequestRepository
                    .findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND));
            orderRequestEntity.setStatus(StatusOrderEnum.OPEN);
            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                    .apply(orderRequestRepository.saveAndFlush(orderRequestEntity));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    @Override
    @Transactional
    public OrderRequestDto delete(String customerId, String orderId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            OrderRequestEntity entity = orderRequestRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(Constants.ORDER_NOT_FOUND + orderId));
            entity.setStatus(StatusOrderEnum.DELETE);

            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                    .apply(orderRequestRepository.save(entity));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRequestDto> getAll(String customerId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            return orderRequestRepository.findAllByCustomer(customerOptional.get())
                    .stream()
                    .map(EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRequestDto getById(String customerId, String orderId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            return EntityToDtoAdapter.orderRequestEntityToOrderRequestDtoAdapter
                    .apply(orderRequestRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(Constants.ORDER_NOT_FOUND + orderId)));
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }

    private long calculateTotalQuantity(List<OrderRequestItemEntity> orderHasProducts) {
        return orderHasProducts.size();
    }

    private Double calculateSumAmount(List<OrderRequestItemEntity> orderHasProducts) {
        return orderHasProducts.stream().mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit()).sum();
    }

    private Double calculateMinusAmount(List<OrderRequestItemEntity> orderHasProducts, double currentAmount) {
        return currentAmount - orderHasProducts
                .stream()
                .mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit())
                .min().orElse(0);
    }


    private DiscountDto calculateTotalDiscount(String orderId) {
        OrderRequestEntity orderRequestEntity = orderRequestRepository.getById(orderId);
        List<DiscountDto> listOfOrderDiscountsToApply = calculateTotalDiscountOrder(orderRequestEntity);
        return listOfOrderDiscountsToApply.stream().max(Comparator.comparingDouble(DiscountDto::getDiscount)).orElse(null);
    }

    private List<DiscountDto> calculateTotalDiscountOrder(OrderRequestEntity orderRequestEntity) {
        List<DiscountEntity> discountListFromDb = discountRepository.findAllByToDateAndDiscountType(new Date(), DiscountTypeEnum.DISCOUNT_ORDER);
        List<DiscountDto> listOfDiscountsToApply = new ArrayList<>();
        for (int i = 0; i < discountListFromDb.size(); i++) {
            DiscountEntity discount = discountListFromDb.get(i);
            if(orderRequestEntity.getOrderItems().size() >= discount.getQuantityItems()){
                DiscountDto discountsToApply = new DiscountDto();
                discountsToApply.setDiscount((discount.getDiscount() / 100) * orderRequestEntity.getTotalAmount());
                listOfDiscountsToApply.add(discountsToApply);
            }
        }
        return listOfDiscountsToApply;
    }

    private  List<DiscountDto> calculateTotalDiscountPromotion(OrderRequestEntity orderRequestEntity) {
        List<DiscountEntity> discountListFromDb = discountRepository.findAllByToDateAndDiscountType(new Date(), DiscountTypeEnum.DISCOUNT_PRODUCT_COMBO);
        List<DiscountDto> listOfDiscountsToApply = new ArrayList<>();

        for (int i = 0; i < discountListFromDb.size(); i++) {
            DiscountEntity discount = discountListFromDb.get(i);

            List<String> idsDiscountItemConfiguration = new ArrayList<>();
            for (int j = 0; j < discount.getDiscountItems().size(); j++) {
                idsDiscountItemConfiguration.add(discount.getDiscountItems().get(j).getProduct().getId());
            }

            List<String> idsOrderItems = new ArrayList<>();
            for (int j = 0; j < orderRequestEntity.getOrderItems().size(); j++) {
                idsOrderItems.add(orderRequestEntity.getOrderItems().get(j).getProduct().getId());
            }

            if(idsOrderItems.containsAll(idsDiscountItemConfiguration)){
                listOfDiscountsToApply.add(EntityToDtoAdapter.discountEntityToDiscountDtoAdapter.apply(discount));
            }
        }


        return listOfDiscountsToApply;
    }

    @Override
    @Transactional(readOnly = true)
    public PrintReceiptDto printOrder(String customerId, String orderId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            OrderRequestEntity orderRequestEntity = orderRequestRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(Constants.ORDER_NOT_FOUND + orderId));
            PrintReceiptDto printReceiptDto = new PrintReceiptDto();
            printReceiptDto.setTotal(orderRequestEntity.getTotalAmount());

            List<PrintReceiptItemDto> printReceiptItemDtos = new ArrayList<>();
            Map<String, List<OrderRequestItemEntity>> groupedByProductId = orderRequestEntity.getOrderItems().stream().collect(Collectors.groupingBy(orderItem -> orderItem.getProduct().getId()));
            for (Map.Entry<String, List<OrderRequestItemEntity>> entry : groupedByProductId.entrySet()) {
                List<OrderRequestItemEntity> orderItemsForProduct = entry.getValue();
                PrintReceiptItemDto printReceiptItemDto = new PrintReceiptItemDto();
                printReceiptItemDto.setAmount(orderItemsForProduct.size());
                printReceiptItemDto.setProductName(orderItemsForProduct.get(0).getProduct().getName());
                printReceiptItemDto.setUnitPrice(orderItemsForProduct.get(0).getProduct().getPriceUnit());
                printReceiptItemDto.setTotal(orderItemsForProduct.stream().mapToDouble(orderRequestItemsEntity -> orderRequestItemsEntity.getProduct().getPriceUnit()).sum());
                printReceiptItemDtos.add(printReceiptItemDto);
            }
            printReceiptDto.setReceiptItems(printReceiptItemDtos);
            return printReceiptDto;
        } else {
            throw new EntityNotFoundException("Customer Not Found");
        }
    }


    private Double calculatePromotionDiscount(OrderRequestEntity orderRequest) {
        return 10D;
    }

}

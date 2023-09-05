package com.standard.coffeeShop.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discount")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class DiscountEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 320781767776608424L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "amount_from")
    private Double amountFrom;

    @Column(name = "amount_to")
    private Double amountTo;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "quantity_items")
    private Double quantityItems;

    @Column(name = "discount_type")
    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum discountType;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscountItemEntity> discountItems = new ArrayList<>();

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscountItemEntity> comboItems = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<DiscountItemEntity> getDiscountItems() {
        return discountItems;
    }

    public void setDiscountItems(List<DiscountItemEntity> discountItems) {
        this.discountItems = discountItems;
    }

    public Double getQuantityItems() {
        return quantityItems;
    }

    public void setQuantityItems(Double quantityItems) {
        this.quantityItems = quantityItems;
    }

    public DiscountTypeEnum getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountTypeEnum discounType) {
        this.discountType = discounType;
    }

    public void addDiscountItem(DiscountItemEntity discountItem) {
        if (discountItems != null) {
            if (discountItems == null) {
                discountItems = new ArrayList<>();
            }
            discountItem.setDiscount(this);
            discountItems.add(discountItem);
        }
    }

    public void removeDiscountItem(DiscountItemEntity discountItem) {
        if (discountItems != null) {
            discountItems.remove(discountItem);
        }
    }

    public Double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }


    public List<DiscountItemEntity> getComboItems() {
        return comboItems;
    }

    public void setComboItems(List<DiscountItemEntity> comboItems) {
        this.comboItems = comboItems;
    }
}

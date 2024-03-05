package com.standard.coffeeshop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public @Data class DiscountEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 320781767776608424L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

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

}

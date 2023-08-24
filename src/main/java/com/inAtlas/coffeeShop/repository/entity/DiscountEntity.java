package com.inAtlas.coffeeShop.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "discount")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class DiscountEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 320781767776608424L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "discount")
    private Double discount;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscountItemsEntity> discountItems = new ArrayList<>();

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

    public List<DiscountItemsEntity> getDiscountItems() {
        return discountItems;
    }

    public void setDiscountItems(List<DiscountItemsEntity> discountItems) {
        this.discountItems = discountItems;
    }

    public void addDiscountItem(DiscountItemsEntity discountItem) {
        if (discountItems != null) {
            if (discountItems == null) {
                discountItems = new ArrayList<>();
            }
            discountItem.setDiscount(this);
            discountItems.add(discountItem);
        }
    }

    public void removeDiscountItem(DiscountItemsEntity discountItem) {
        if (discountItems != null) {
            discountItems.remove(discountItem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountEntity that = (DiscountEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate) && Objects.equals(discount, that.discount) && Objects.equals(discountItems, that.discountItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, status, fromDate, toDate, discount, discountItems);
    }

    @Override
    public String toString() {
        return "DiscountEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", discount=" + discount +
                ", discountItems=" + discountItems +
                '}';
    }
}

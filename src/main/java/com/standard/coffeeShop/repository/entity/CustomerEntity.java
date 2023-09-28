package com.standard.coffeeShop.repository.entity;

import com.standard.coffeeShop.repository.entity.security.UserEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity  extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(length = 36, columnDefinition = "varchar")
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<OrderRequestEntity> orderRequests;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String customerName, UUID apiKey, Set<OrderRequestEntity> orderRequests) {
        this.id = id;
        this.customerName = customerName;
        this.apiKey = apiKey;
        this.orderRequests = orderRequests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public UUID getApiKey() {
        return apiKey;
    }

    public void setApiKey(UUID apiKey) {
        this.apiKey = apiKey;
    }

    public Set<OrderRequestEntity> getOrderRequests() {
        return orderRequests;
    }

    public void setOrderRequests(Set<OrderRequestEntity> orderRequests) {
        this.orderRequests = orderRequests;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(customerName, that.customerName) && Objects.equals(apiKey, that.apiKey) && Objects.equals(orderRequests, that.orderRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, customerName, apiKey, orderRequests);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", apiKey=" + apiKey +
                ", orderRequests=" + orderRequests +
                '}';
    }
}

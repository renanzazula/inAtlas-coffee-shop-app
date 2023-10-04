package com.standard.coffeeShop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public @Data class AuditEntity implements Serializable {

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date_create", updatable = false)
    private Date dateCreate;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "time_create", updatable = false)
    private Date timeCreate;

    @CreatedBy
    @Column(name = "who_create", insertable = true, updatable = false)
    private Long whoCreate;

    @Version
    @Column(name = "version")
    private Long version;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date_update")
    private Date dateUpdate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "time_Update")
    private Date timeUpdate;

    @LastModifiedBy
    @Column(name = "who_update")
    private Long whoUpdate;

}

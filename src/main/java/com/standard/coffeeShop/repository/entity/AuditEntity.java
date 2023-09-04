package com.standard.coffeeShop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity implements Serializable {

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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Long getWhoCreate() {
        return whoCreate;
    }

    public void setWhoCreate(Long whoCreate) {
        this.whoCreate = whoCreate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Long getWhoUpdate() {
        return whoUpdate;
    }

    public void setWhoUpdate(Long whoUpdate) {
        this.whoUpdate = whoUpdate;
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "dateCreate=" + dateCreate +
                ", timeCreate=" + timeCreate +
                ", whoCreate=" + whoCreate +
                ", version=" + version +
                ", dateUpdate=" + dateUpdate +
                ", timeUpdate=" + timeUpdate +
                ", whoUpdate=" + whoUpdate +
                '}';
    }
}

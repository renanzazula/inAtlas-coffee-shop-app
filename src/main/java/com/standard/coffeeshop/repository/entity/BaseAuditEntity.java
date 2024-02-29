package com.standard.coffeeshop.repository.entity;

import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity extends AuditEntity implements Serializable {



}

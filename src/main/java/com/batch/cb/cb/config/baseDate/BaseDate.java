package com.batch.cb.cb.config.baseDate;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDate {

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createDate;

    @LastModifiedDate
    private LocalDate updateDate;

}

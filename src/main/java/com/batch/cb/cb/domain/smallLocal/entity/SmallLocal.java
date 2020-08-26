package com.batch.cb.cb.domain.smallLocal.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "small_local")
@EqualsAndHashCode(of = "smallLocal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmallLocal {

    @Id @GeneratedValue
    private Long smallLocalId;

    private String smallLocal;

    protected SmallLocal(String smallLocal) {
        this.smallLocal = smallLocal;
    }

    public static SmallLocal createSmallLocal(String smallLocal){
        return new SmallLocal(smallLocal);
    }

}

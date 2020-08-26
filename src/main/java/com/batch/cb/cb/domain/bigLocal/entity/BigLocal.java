package com.batch.cb.cb.domain.bigLocal.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "big_local")
@EqualsAndHashCode(of = "bigLocal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BigLocal {

    @Id @GeneratedValue
    private Long bigLocalId;

    private String bigLocal;

    protected BigLocal(String bigLocal){
        this.bigLocal = bigLocal;
    }

    public static BigLocal createBigLocal(String bigLocal){
        return new BigLocal(bigLocal);
    }

}

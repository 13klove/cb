package com.batch.cb.cb.domain.library.entity;

import com.batch.cb.cb.domain.bigLocal.entity.BigLocal;
import com.batch.cb.cb.domain.libraryType.entity.LibraryType;
import com.batch.cb.cb.domain.smallLocal.entity.SmallLocal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "library")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tempLibraryId;

    private String libraryNm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bigLocalId")
    private BigLocal bigLocal;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "smallLocalId")
    private SmallLocal smallLocal;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "libraryTypeId")
    private LibraryType libraryType;

    protected Library(String libraryNm, BigLocal bigLocal, SmallLocal smallLocal, LibraryType libraryType){
        this.libraryNm = libraryNm;
        this.bigLocal = bigLocal;
        this.smallLocal = smallLocal;
        this.libraryType = libraryType;
    }

    public static Library createLibrary(String libraryNm, BigLocal bigLocal, SmallLocal smallLocal, LibraryType libraryType){
        return new Library(libraryNm, bigLocal, smallLocal, libraryType);
    }

}

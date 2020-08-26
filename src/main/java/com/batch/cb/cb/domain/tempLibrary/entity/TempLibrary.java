package com.batch.cb.cb.domain.tempLibrary.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "temp_library")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempLibrary {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tempLibraryId;

    private String libraryNm;

    private String bigLocal;

    private String smallLocal;

    private String libraryType;

    protected TempLibrary (String libraryNm, String bigLocal, String smallLocal, String libraryType){
        this.libraryNm = libraryNm;
        this.bigLocal = bigLocal;
        this.smallLocal = smallLocal;
        this.libraryType = libraryType;
    }

    public static TempLibrary createTempLibrary(String libraryNm, String bigLocal, String smallLocal, String libraryType){
        return new TempLibrary(libraryNm, bigLocal, smallLocal, libraryType);
    }

}

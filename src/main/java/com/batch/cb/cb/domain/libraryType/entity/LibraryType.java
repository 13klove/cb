package com.batch.cb.cb.domain.libraryType.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "library_type")
@EqualsAndHashCode(of = "libraryType")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryType {

    @Id @GeneratedValue
    private Long libraryTypeId;

    private String libraryType;

    protected LibraryType(String libraryType) {
        this.libraryType = libraryType;
    }

    public static LibraryType createLibraryType(String libraryType){
        return new LibraryType(libraryType);
    }

}

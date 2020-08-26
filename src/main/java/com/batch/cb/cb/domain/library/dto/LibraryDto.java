package com.batch.cb.cb.domain.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDto {

    private String libraryNm;
    private String bigLocal;
    private String smallLocal;
    private String libraryType;

}

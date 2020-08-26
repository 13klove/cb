package com.batch.cb.cb.domain.tempLibrary.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TempLibraryDto {

    private String libraryNm;
    private String bigLocal;
    private String smallLocal;
    private String libraryType;

}

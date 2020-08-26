package com.batch.cb.cb.domain.tempLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempLibraryDto {

    private String libraryNm;
    private String bigLocal;
    private String smallLocal;
    private String libraryType;

}

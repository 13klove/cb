package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.reader;

import com.batch.cb.cb.domain.library.dto.LibraryDto;
import com.batch.cb.cb.domain.library.entity.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class LibraryDbReader {

    private final EntityManagerFactory entityManagerFactory;
    private static final int libraryPagSize = 100;

    public JpaPagingItemReader<LibraryDto> jpaPagingItemReader(){
//        return new JpaPagingItemReaderBuilder<Library>()
//                .queryString("select l from Library l join fetch l.bigLocal join fetch l.smallLocal join fetch l.libraryType")
//                .pageSize(libraryPagSize)
//                .entityManagerFactory(entityManagerFactory)
//                .name("libraryJpaReader")
//                .build();
        return new JpaPagingItemReaderBuilder<LibraryDto>()
                .queryString("select " +
                        "new com.batch.cb.cb.domain.library.dto.LibraryDto(" +
                        "l.libraryNm, l.bigLocal.bigLocal, l.smallLocal.smallLocal, l.libraryType.libraryType" +
                        ") " +
                        "from Library l " +
                        "join l.bigLocal " +
                        "join l.smallLocal " +
                        "join l.libraryType")
                .pageSize(libraryPagSize)
                .entityManagerFactory(entityManagerFactory)
                .name("libraryJpaReader")
                .build();

    }

}

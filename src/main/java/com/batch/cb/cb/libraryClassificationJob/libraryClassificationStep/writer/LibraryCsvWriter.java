package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer;

import com.batch.cb.cb.domain.tempLibrary.dto.TempLibraryDto;
import com.batch.cb.cb.domain.tempLibrary.entity.TempLibrary;
import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LibraryCsvWriter implements ItemWriter<TempLibraryDto> {

    private final TempLibraryJpaRepository tempLibraryJpaRepository;

    @Override
    public void write(List<? extends TempLibraryDto> list) throws Exception {
        List<TempLibrary> tempLibraries = new ArrayList<>();
        list.forEach(a->{
            tempLibraries.add(TempLibrary.createTempLibrary(a.getLibraryNm(), a.getBigLocal(), a.getSmallLocal(), a.getLibraryType()));
        });
        tempLibraryJpaRepository.saveAll(tempLibraries);
    }

}

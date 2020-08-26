package com.batch.cb.cb.libraryClassificationJob;

import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import com.batch.cb.cb.domain.tempLibrary.dto.TempLibraryDto;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.reader.LibraryCsvReader;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer.LibraryCsvWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Configuration
@RequiredArgsConstructor
//https://renuevo.github.io/spring/batch/spring-batch-chapter-2/
public class LibraryClassificationConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TempLibraryJpaRepository tempLibraryJpaRepository;
    private final int libraryCsvSaveStepChunk = 100;

    @Bean
    public Job libraryClassificationJob(){
        return jobBuilderFactory.get("librarySaveJob")
                .start(libraryCsvSaveStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step libraryCsvSaveStep(){
        return stepBuilderFactory.get("libraryCsvSaveStep")
                .<TempLibraryDto, TempLibraryDto>chunk(libraryCsvSaveStepChunk)
                .reader(new LibraryCsvReader().libraryCsvReader())
                .writer(new LibraryCsvWriter(tempLibraryJpaRepository))
                .build();
    }

}

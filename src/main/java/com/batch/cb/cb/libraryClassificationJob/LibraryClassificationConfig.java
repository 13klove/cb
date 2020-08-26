package com.batch.cb.cb.libraryClassificationJob;

import com.batch.cb.cb.domain.bigLocal.repository.BigLocalJpaRepository;
import com.batch.cb.cb.domain.library.dto.LibraryDto;
import com.batch.cb.cb.domain.library.entity.Library;
import com.batch.cb.cb.domain.library.repository.LibraryJpaRepository;
import com.batch.cb.cb.domain.libraryType.repository.LibraryTypeJpaRepository;
import com.batch.cb.cb.domain.smallLocal.repository.SmallLocalJpaRepository;
import com.batch.cb.cb.domain.tempLibrary.dto.TempLibraryDto;
import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.reader.LibraryCsvReader;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.reader.LibraryDbReader;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.task.LibraryDataProcessTask;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer.LibraryCsvWriter;
import com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer.LibraryDbWriter;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Slf4j
@Transactional
@Configuration
@RequiredArgsConstructor
//https://renuevo.github.io/spring/batch/spring-batch-chapter-2/
public class LibraryClassificationConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TempLibraryJpaRepository tempLibraryJpaRepository;
    private final LibraryJpaRepository libraryJpaRepository;
    private final BigLocalJpaRepository bigLocalJpaRepository;
    private final SmallLocalJpaRepository smallLocalJpaRepository;
    private final LibraryTypeJpaRepository libraryTypeJpaRepository;
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    private static final int libraryCsvSaveStepChunk = 100;

    @Bean
    public Job libraryClassificationJob(){
        return jobBuilderFactory.get("librarySaveJob")
                .start(libraryCsvSaveStep())
                .next(libraryDataProcessStep())
                .next(libraryDbToCsvStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step libraryCsvSaveStep(){
        return stepBuilderFactory.get("libraryCsvSaveStep")
                .<TempLibraryDto, TempLibraryDto>chunk(libraryCsvSaveStepChunk)
                .reader(new LibraryCsvReader().libraryCsvReader())
                .writer(new LibraryCsvWriter(tempLibraryJpaRepository))
                //.faultTolerant()
                //.skip(FlatFileParseException.class)
                //.skipPolicy(new AlwaysSkipItemSkipPolicy())
                .build();
    }

    @Bean
    public Step libraryDataProcessStep(){
        return stepBuilderFactory.get("libraryDataProcessStep")
                .tasklet(new LibraryDataProcessTask(tempLibraryJpaRepository, libraryJpaRepository, bigLocalJpaRepository, smallLocalJpaRepository, libraryTypeJpaRepository, entityManager))
                .build();
    }

    @Bean
    public Step libraryDbToCsvStep(){
        return stepBuilderFactory.get("libraryDbToCsvStep")
                .<LibraryDto, LibraryDto>chunk(libraryCsvSaveStepChunk)
                .reader(new LibraryDbReader(entityManagerFactory).jpaPagingItemReader())
                .writer(new LibraryDbWriter().libraryFlatFileItemWriter())
                .build();
    }

}

package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.task;

import com.batch.cb.cb.domain.bigLocal.repository.BigLocalJpaRepository;
import com.batch.cb.cb.domain.library.repository.LibraryJpaRepository;
import com.batch.cb.cb.domain.libraryType.repository.LibraryTypeJpaRepository;
import com.batch.cb.cb.domain.smallLocal.repository.SmallLocalJpaRepository;
import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@RequiredArgsConstructor
public class LibraryDataDelTask implements Tasklet {

    private final TempLibraryJpaRepository tempLibraryJpaRepository;
    private final LibraryJpaRepository libraryJpaRepository;
    private final BigLocalJpaRepository bigLocalJpaRepository;
    private final SmallLocalJpaRepository smallLocalJpaRepository;
    private final LibraryTypeJpaRepository libraryTypeJpaRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        tempLibraryJpaRepository.deleteAll();
        libraryJpaRepository.deleteAll();
        bigLocalJpaRepository.deleteAll();
        smallLocalJpaRepository.deleteAll();
        libraryTypeJpaRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }

}

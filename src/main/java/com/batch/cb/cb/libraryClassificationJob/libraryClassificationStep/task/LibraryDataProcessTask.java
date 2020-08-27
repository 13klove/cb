package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.task;

import com.batch.cb.cb.domain.bigLocal.entity.BigLocal;
import com.batch.cb.cb.domain.bigLocal.repository.BigLocalJpaRepository;
import com.batch.cb.cb.domain.library.entity.Library;
import com.batch.cb.cb.domain.library.repository.LibraryJpaRepository;
import com.batch.cb.cb.domain.libraryType.entity.LibraryType;
import com.batch.cb.cb.domain.libraryType.repository.LibraryTypeJpaRepository;
import com.batch.cb.cb.domain.smallLocal.entity.SmallLocal;
import com.batch.cb.cb.domain.smallLocal.repository.SmallLocalJpaRepository;
import com.batch.cb.cb.domain.tempLibrary.entity.TempLibrary;
import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LibraryDataProcessTask implements Tasklet {

    private final EntityManager entityManager;
    private final TempLibraryJpaRepository tempLibraryJpaRepository;
    private final LibraryJpaRepository libraryJpaRepository;
    private final BigLocalJpaRepository bigLocalJpaRepository;
    private final SmallLocalJpaRepository smallLocalJpaRepository;
    private final LibraryTypeJpaRepository libraryTypeJpaRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        List<TempLibrary> tempLibraries = tempLibraryJpaRepository.findAll();
        Set<BigLocal> bigLocals = Sets.newHashSet();
        Set<SmallLocal> smallLocals= Sets.newHashSet();
        Set<LibraryType> libraryTypes = Sets.newHashSet();

        tempLibraries.stream().forEach(a->{
            bigLocals.add(BigLocal.createBigLocal(a.getBigLocal()));
            smallLocals.add(SmallLocal.createSmallLocal(a.getSmallLocal()));
            libraryTypes.add(LibraryType.createLibraryType(a.getLibraryType()));
        });

        List<BigLocal> saveBigLocals1 = bigLocalJpaRepository.saveAll(bigLocals);
        List<SmallLocal> saveSmallLocals1 = smallLocalJpaRepository.saveAll(smallLocals);
        List<LibraryType> saveLibraryTypes1 = libraryTypeJpaRepository.saveAll(libraryTypes);
        entityManager.flush();

        System.out.println(saveBigLocals1);
        Map<String, BigLocal> bigLocalMap = saveBigLocals1.stream().collect(Collectors.toMap(a->a.getBigLocal(), Function.identity()));
        Map<String, SmallLocal> smallLocalMap = saveSmallLocals1.stream().collect(Collectors.toMap(a->a.getSmallLocal(), Function.identity()));
        Map<String, LibraryType> libraryTypeMap = saveLibraryTypes1.stream().collect(Collectors.toMap(a->a.getLibraryType(), Function.identity()));

        List<Library> libraries = tempLibraries.stream().map(a->Library.createLibrary(a.getLibraryNm(), bigLocalMap.get(a.getBigLocal()), smallLocalMap.get(a.getSmallLocal()), libraryTypeMap.get(a.getLibraryType()))).collect(Collectors.toList());

        libraryJpaRepository.saveAll(libraries);
        return RepeatStatus.FINISHED;
    }

}

package com.batch.cb.cb.domain.tempLibrary.repository;

import com.batch.cb.cb.domain.tempLibrary.entity.TempLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempLibraryJpaRepository extends JpaRepository<TempLibrary, Long> {
}

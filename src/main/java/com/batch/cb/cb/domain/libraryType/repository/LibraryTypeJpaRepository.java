package com.batch.cb.cb.domain.libraryType.repository;

import com.batch.cb.cb.domain.libraryType.entity.LibraryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryTypeJpaRepository extends JpaRepository<LibraryType, Long> {
}

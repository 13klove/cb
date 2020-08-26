package com.batch.cb.cb.domain.library.repository;

import com.batch.cb.cb.domain.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryJpaRepository extends JpaRepository<Library, Long> {
}

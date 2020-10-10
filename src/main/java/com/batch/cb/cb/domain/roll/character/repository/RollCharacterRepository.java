package com.batch.cb.cb.domain.roll.character.repository;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.queryDsl.RollCharacterDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollCharacterRepository extends JpaRepository<RollCharacter, Long>, RollCharacterDslRepository {
}

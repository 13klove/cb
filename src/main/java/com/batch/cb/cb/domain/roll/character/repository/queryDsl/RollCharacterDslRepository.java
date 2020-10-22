package com.batch.cb.cb.domain.roll.character.repository.queryDsl;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;

import java.util.List;
import java.util.Optional;

public interface RollCharacterDslRepository {

    Optional<RollCharacter> findRollCharacter(String characterName);

    List<RollCharacter> findRollCharacters();

}

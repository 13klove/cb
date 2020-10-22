package com.batch.cb.cb.domain.roll.character.repository.queryDsl;

import com.batch.cb.cb.domain.roll.character.entity.QRollCharacter;
import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.batch.cb.cb.domain.roll.character.entity.QRollCharacter.rollCharacter;
import static com.batch.cb.cb.domain.roll.skill.entity.QRollSkill.rollSkill;

public class RollCharacterDslRepositoryImpl implements RollCharacterDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public RollCharacterDslRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<RollCharacter> findRollCharacter(String characterName) {
        RollCharacter rollCharacter = jpaQueryFactory.selectFrom(QRollCharacter.rollCharacter)
                .join(QRollCharacter.rollCharacter.rollSkills, rollSkill).fetchJoin()
                .where(whereCharacterName(characterName))
                .fetchOne();

        if(rollCharacter!=null) rollCharacter.getRollPositions();

        return Optional.ofNullable(rollCharacter);
    }

    @Override
    public List<RollCharacter> findRollCharacters() {
        List<RollCharacter> fetch = jpaQueryFactory.selectFrom(rollCharacter)
                .distinct()
                .join(rollCharacter.rollSkills, rollSkill).fetchJoin()
                .fetch();

        fetch.stream().forEach(a->a.getRollPositions());
        return fetch;
    }

    private BooleanExpression whereCharacterName(String characterName) {
        return characterName==null?null:rollCharacter.characterName.eq(characterName);
    }

}

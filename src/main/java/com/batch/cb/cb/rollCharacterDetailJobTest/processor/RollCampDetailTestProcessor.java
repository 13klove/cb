package com.batch.cb.cb.rollCharacterDetailJobTest.processor;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class RollCampDetailTestProcessor implements ItemProcessor<RollCharacter, RollCharacter> {


    @Override
    public RollCharacter process(RollCharacter rollCharacter) throws Exception {
        return rollCharacter;
    }

}

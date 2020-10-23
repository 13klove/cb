package com.batch.cb.cb.rollCharacterDetailJobTest.writer;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.RollCharacterRepository;
import com.batch.cb.cb.domain.roll.position.repository.RollPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RollCampDetailTestWriter extends JpaItemWriter<RollCharacter> implements StepExecutionListener {

    private final RollCharacterRepository rollCharacterRepository;
    private final RollPositionRepository rollPositionRepository;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        super.setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void write(List<? extends RollCharacter> items) {
        List<String> characterNames = items.stream().map(a->a.getCharacterName()).collect(Collectors.toList());
        Map<String, RollCharacter> memoryRollCharacter = rollCharacterRepository.findRollCharacters(characterNames)
                .stream()
                .collect(Collectors.toMap(a->a.getCharacterName(), Function.identity()));

        List<RollCharacter> saveResult = items.stream().map(a->{
            RollCharacter rollCharacter1 = memoryRollCharacter.get(a.getCharacterName());
            if(rollCharacter1==null){
                return a;
            }else{
                rollCharacter1.updateRollCharacter(a.getCharacterName(), a.getTier());
                rollCharacter1.updateRollSkill(a.getRollSkills());
                //삭제가 안되는 이유를... 모르겠다...
                rollPositionRepository.deleteAll();
                return rollCharacter1;
            }
        }).collect(Collectors.toList());

        super.write(saveResult);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

}

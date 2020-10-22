package com.batch.cb.cb.rollCampDetailJob.processor;

import com.batch.cb.cb.crw.roll.characterDetail.output.RollCampDetailOutPut;
import com.batch.cb.cb.crw.roll.characterDetail.parser.RollCampDetailParser;
import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.RollCharacterRepository;
import com.batch.cb.cb.domain.roll.position.repository.RollPositionRepository;
import com.batch.cb.cb.util.crw.CrwVo;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RollCampDetailProcessor implements ItemProcessor<String, RollCharacter>, StepExecutionListener {

    private Map<String, RollCharacter> allRollCharacter;
    private final CloseableHttpClient httpClient;
    private final RollCharacterRepository rollCharacterRepository;
    private final RollPositionRepository rollPositionRepository;

    @Override
    public RollCharacter process(String s) throws Exception {
        CrwVo crwVo = CrwVo.builder().client(httpClient).input(s).build();

        new RollCampDetailParser().read(crwVo);
        RollCharacter crwRollCharacter = new RollCampDetailOutPut().getOutput((String) crwVo.getOutput());

        RollCharacter dbCharacter = allRollCharacter.get(crwRollCharacter.getCharacterName());
        if(dbCharacter==null){
            return crwRollCharacter;
        }else{
            dbCharacter.updateRollCharacter(crwRollCharacter.getCharacterName(), crwRollCharacter.getTier());
            dbCharacter.updateRollSkill(crwRollCharacter.getRollSkills());
            //List<RollPosition> rollPositions = dbCharacter.getRollPositions();
            //dbCharacter.changeRollPositions(crwRollCharacter.getRollPositions());
            //rollPositionRepository.deleteAll();
            return dbCharacter;
        }


//        Optional<RollCharacter> optional = rollCharacterRepository.findRollCharacter(crwRollCharacter.getCharacterName());
//
//        if(!optional.isPresent()) {
//            return crwRollCharacter;
//        } else{
//            RollCharacter dbRollCharacter = optional.get();
//            dbRollCharacter.updateRollCharacter(crwRollCharacter.getCharacterName(), crwRollCharacter.getTier());
//            dbRollCharacter.updateRollSkill(crwRollCharacter.getRollSkills());
//            List<RollPosition> rollPositions = dbRollCharacter.getRollPositions();
//            dbRollCharacter.changeRollPositions(crwRollCharacter.getRollPositions());
//            rollPositionRepository.deleteAll(rollPositions);
//            return dbRollCharacter;
//        }

    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        allRollCharacter = rollCharacterRepository.findRollCharacters().stream().collect(Collectors.toMap(a->a.getCharacterName(), Function.identity()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}

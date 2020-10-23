package com.batch.cb.cb;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.RollCharacterRepository;
import com.batch.cb.cb.domain.roll.position.PositionType;
import com.batch.cb.cb.domain.roll.position.entity.RollPosition;
import com.batch.cb.cb.domain.roll.skill.SkillType;
import com.batch.cb.cb.domain.roll.skill.entity.RollSkill;
import com.batch.cb.cb.stepFlowJob.StepFlowJobConfig;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepa;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepb;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {StepFlowJobConfig.class, StepFlowStepa.class, StepFlowStepb.class, StepFlowStepc.class, BatchTestConfig.class})
public class RollCampTempDataTest {

    @Autowired
    RollCharacterRepository rollCharacterRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    @Transactional
    public void fetchSize(){
        List<RollCharacter> rollCharacters = rollCharacterRepository.findRollCharacters();
        System.out.println(rollCharacters);
    }

    @Test
    @Commit
    @Transactional
    public void teat(){
        RollCharacter rollCharacter1 = RollCharacter.createRollCharacter("가렌", 3);
        List<RollSkill> rollSkill1 = Arrays.asList(RollSkill.createRollSkill("가렌스킬1", SkillType.PASSIVE)
                , RollSkill.createRollSkill("가렌스킬2", SkillType.SKILL)
                , RollSkill.createRollSkill("가렌스킬3", SkillType.SKILL)
                , RollSkill.createRollSkill("가렌스킬4", SkillType.SKILL)
                , RollSkill.createRollSkill("가렌스킬5", SkillType.ULTIMATE));
        List<RollPosition> rollPositions1 = Arrays.asList(RollPosition.createRollPosition(PositionType.TOP));
        rollCharacter1.addRollSkills(rollSkill1);
        rollCharacter1.addRollPositions(rollPositions1);



        RollCharacter rollCharacter2 = RollCharacter.createRollCharacter("오공", 3);
        List<RollSkill> rollSkill12 = Arrays.asList(RollSkill.createRollSkill("오공스킬1", SkillType.PASSIVE)
                , RollSkill.createRollSkill("오공스킬2", SkillType.SKILL)
                , RollSkill.createRollSkill("오공스킬3", SkillType.SKILL)
                , RollSkill.createRollSkill("오공스킬4", SkillType.SKILL)
                , RollSkill.createRollSkill("오공스킬5", SkillType.ULTIMATE));
        List<RollPosition> rollPositions2 = Arrays.asList(RollPosition.createRollPosition(PositionType.TOP), RollPosition.createRollPosition(PositionType.JUNGLE));
        rollCharacter2.addRollSkills(rollSkill12);
        rollCharacter2.addRollPositions(rollPositions2);



        RollCharacter rollCharacter3 = RollCharacter.createRollCharacter("베인", 3);
        List<RollSkill> rollSkill13 = Arrays.asList(RollSkill.createRollSkill("베인스킬1", SkillType.PASSIVE)
                , RollSkill.createRollSkill("베인스킬2", SkillType.SKILL)
                , RollSkill.createRollSkill("베인스킬3", SkillType.SKILL)
                , RollSkill.createRollSkill("베인스킬4", SkillType.SKILL)
                , RollSkill.createRollSkill("베인스킬5", SkillType.ULTIMATE));
        List<RollPosition> rollPositions3 = Arrays.asList(RollPosition.createRollPosition(PositionType.TOP), RollPosition.createRollPosition(PositionType.ADC));
        rollCharacter3.addRollSkills(rollSkill13);
        rollCharacter3.addRollPositions(rollPositions3);



        RollCharacter rollCharacter4 = RollCharacter.createRollCharacter("미미", 3);
        List<RollSkill> rollSkill14 = Arrays.asList(RollSkill.createRollSkill("미미스킬1", SkillType.PASSIVE)
                , RollSkill.createRollSkill("미미스킬2", SkillType.SKILL)
                , RollSkill.createRollSkill("미미스킬3", SkillType.SKILL)
                , RollSkill.createRollSkill("미미스킬4", SkillType.SKILL)
                , RollSkill.createRollSkill("미미스킬5", SkillType.ULTIMATE));
        List<RollPosition> rollPositions4 = Arrays.asList(RollPosition.createRollPosition(PositionType.SUPPORT));
        rollCharacter4.addRollSkills(rollSkill14);
        rollCharacter4.addRollPositions(rollPositions4);



        RollCharacter rollCharacter5 = RollCharacter.createRollCharacter("다리우스", 3);
        List<RollSkill> rollSkill15 = Arrays.asList(RollSkill.createRollSkill("다리우스스킬1", SkillType.PASSIVE)
                , RollSkill.createRollSkill("다리우스스킬2", SkillType.SKILL)
                , RollSkill.createRollSkill("다리우스스킬3", SkillType.SKILL)
                , RollSkill.createRollSkill("다리우스스킬4", SkillType.SKILL)
                , RollSkill.createRollSkill("다리우스스킬5", SkillType.ULTIMATE));
        List<RollPosition> rollPositions5 = Arrays.asList(RollPosition.createRollPosition(PositionType.SUPPORT));
        rollCharacter5.addRollSkills(rollSkill15);
        rollCharacter5.addRollPositions(rollPositions5);

        rollCharacterRepository.save(rollCharacter1);
        rollCharacterRepository.save(rollCharacter2);
        rollCharacterRepository.save(rollCharacter3);
        rollCharacterRepository.save(rollCharacter4);
        rollCharacterRepository.save(rollCharacter5);
        //??? 왜 flush하기 전에는 쿼리가 안나가지? 트렌잭션도 걸었느뎁? 흠... COMMIT어노테이션으로 롤백 안되게 방지
        //entityManager.flush();
    }

}

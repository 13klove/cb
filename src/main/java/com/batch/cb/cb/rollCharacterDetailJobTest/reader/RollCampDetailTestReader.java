package com.batch.cb.cb.rollCharacterDetailJobTest.reader;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.position.PositionType;
import com.batch.cb.cb.domain.roll.position.entity.RollPosition;
import com.batch.cb.cb.domain.roll.skill.SkillType;
import com.batch.cb.cb.domain.roll.skill.entity.RollSkill;
import com.google.common.collect.Lists;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Arrays;
import java.util.List;

public class RollCampDetailTestReader implements ItemReader<RollCharacter>, StepExecutionListener {

    private List<RollCharacter> readList;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("1번째로 1번만 실행");
        readList = getListData();
    }

    @Override
    public RollCharacter read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("2");
        return !this.readList.isEmpty()?readList.remove(0):null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    private List<RollCharacter> getListData() {
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
        List<RollCharacter> list = Lists.newArrayList();
        list.add(rollCharacter1);
        list.add(rollCharacter2);
        list.add(rollCharacter3);
        list.add(rollCharacter4);
        list.add(rollCharacter5);
        return list;
    }

}

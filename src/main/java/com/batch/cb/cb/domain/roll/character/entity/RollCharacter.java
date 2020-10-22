package com.batch.cb.cb.domain.roll.character.entity;

import com.batch.cb.cb.config.baseDate.BaseDate;
import com.batch.cb.cb.domain.roll.position.entity.RollPosition;
import com.batch.cb.cb.domain.roll.skill.entity.RollSkill;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Entity
@Table(name = "rollCharacter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"rollSkills", "rollPositions"})
public class RollCharacter extends BaseDate {

    @Id
    @GeneratedValue
    private Long rollCharacterId;

    private String characterName;

    private Integer tier;

    @OneToMany(mappedBy = "rollCharacter", cascade = CascadeType.ALL)
    private List<RollSkill> rollSkills = Lists.newArrayList();

    @OneToMany(mappedBy = "rollCharacter", cascade = CascadeType.ALL)
    private List<RollPosition> rollPositions = Lists.newArrayList();

    protected RollCharacter(String characterName, Integer tier) {
        this.characterName = characterName;
        this.tier = tier;
    }

    public static RollCharacter createRollCharacter(String characterName, Integer tier){
        return new RollCharacter(characterName, tier);
    }

    public void updateRollCharacter(String characterName, Integer tier){
        this.characterName = characterName;
        this.tier = tier;
    }

    public void addRollSkill(RollSkill rollSkill){
        rollSkills.add(rollSkill);
        rollSkill.changeRollCharacter(this);
    }

    public void addRollSkills(List<RollSkill> rollSkills){
        rollSkills.stream().forEach(a->addRollSkill(a));
    }

    public void addRollPosition(RollPosition rollPosition) {
        rollPositions.add(rollPosition);
        rollPosition.changeRollCharacter(this);
    }

    public void addRollPositions(List<RollPosition> rollPositions){
        rollPositions.stream().forEach(a->addRollPosition(a));
    }

    public void changeRollPositions(List<RollPosition> rollPositions){
        this.rollPositions = Lists.newArrayList();
        rollPositions.stream().forEach(a->addRollPosition(a));
    }

    public void updateRollSkill(List<RollSkill> rollSkills) {
        Collections.sort(this.getRollSkills(), Comparator.comparing(RollSkill::getSkillName));
        Collections.sort(rollSkills, Comparator.comparing(RollSkill::getSkillName));
        for(int i=0;i<rollSkills.size();i++){
            RollSkill crwRollSkill = rollSkills.get(i);
            this.getRollSkills().get(i).updateRollSkill(crwRollSkill.getSkillName(), crwRollSkill.getSkillType());
        }
    }
}

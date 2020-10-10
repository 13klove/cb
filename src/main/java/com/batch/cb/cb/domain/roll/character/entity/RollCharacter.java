package com.batch.cb.cb.domain.roll.character.entity;

import com.batch.cb.cb.config.baseDate.BaseDate;
import com.batch.cb.cb.domain.roll.position.entity.RollPosition;
import com.batch.cb.cb.domain.roll.skill.entity.RollSkill;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.*;
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

    private Integer tear;

    @OneToMany(mappedBy = "rollCharacter", cascade = CascadeType.ALL)
    private List<RollSkill> rollSkills = Lists.newArrayList();

    @OneToMany(mappedBy = "rollCharacter", cascade = CascadeType.ALL)
    private List<RollPosition> rollPositions = Lists.newArrayList();

    protected RollCharacter(String characterName, Integer tear) {
        this.characterName = characterName;
        this.tear = tear;
    }

    public static RollCharacter createRollCharacter(String characterName, Integer tear){
        return new RollCharacter(characterName, tear);
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
}

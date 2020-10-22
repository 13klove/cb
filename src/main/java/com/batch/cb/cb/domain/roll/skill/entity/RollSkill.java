package com.batch.cb.cb.domain.roll.skill.entity;

import com.batch.cb.cb.config.baseDate.BaseDate;
import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.skill.SkillType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "rollSkill")
@ToString(exclude = {"rollCharacter"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RollSkill extends BaseDate {

    @Id
    @GeneratedValue
    private Long rollSkillId;

    private String skillName;

    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roll_Character_id")
    private RollCharacter rollCharacter;

    protected RollSkill(String skillName, SkillType skillType) {
        this.skillName = skillName;
        this.skillType = skillType;
    }

    public static RollSkill createRollSkill(String skillName, SkillType skillType){
        return new RollSkill(skillName, skillType);
    }

    public void updateRollSkill(String skillName, SkillType skillType){
        this.skillName = skillName;
        this.skillType = skillType;
    }

    public void smRollCharacterChange(RollCharacter rollCharacter){
        rollCharacter.addRollSkill(this);
    }

    public void changeRollCharacter(RollCharacter rollCharacter) {
        this.rollCharacter = rollCharacter;
    }

}

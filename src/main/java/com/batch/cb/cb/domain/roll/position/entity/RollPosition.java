package com.batch.cb.cb.domain.roll.position.entity;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.position.PositionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "rollPosition")
@ToString(exclude = {"rollCharacter"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RollPosition {

    @Id
    @GeneratedValue
    private Long rollPositionId;

    @Enumerated(EnumType.STRING)
    private PositionType positionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roll_character_id")
    private RollCharacter rollCharacter;

    protected RollPosition(PositionType positionType) {
        this.positionType = positionType;
    }

    public static RollPosition createRollPosition(PositionType positionType){
        return new RollPosition(positionType);
    }

    public void smRollCharacterChange(RollCharacter rollCharacter){
        rollCharacter.addRollPosition(this);
    }

    public void changeRollCharacter(RollCharacter rollCharacter){
        this.rollCharacter = rollCharacter;
    }
}

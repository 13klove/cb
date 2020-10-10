package com.batch.cb.cb.domain.roll.skill;

public enum SkillType {

    PASSIVE("페시브"),
    SKILL("스킬"),
    ULTIMATE("궁극기");

    private String desc;

    SkillType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

package com.batch.cb.cb.domain.roll.skill;

import org.apache.commons.lang3.StringUtils;

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

    public static SkillType keyValudOf(String data){
        if(StringUtils.isEmpty(data))return SkillType.PASSIVE;
        else if(data.equals("R")) return SkillType.ULTIMATE;
        else return SkillType.SKILL;
    }
}

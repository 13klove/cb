package com.batch.cb.cb.domain.roll.position;

public enum PositionType {

    TOP("탑"),
    JUNGLE("정글"),
    MID("미드"),
    BOTTOM("바텀"),
    SUPPORT("서폿");

    private String desc;

    PositionType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}

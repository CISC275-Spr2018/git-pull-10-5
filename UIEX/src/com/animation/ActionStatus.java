package com.animation;

public enum ActionStatus {
    WALK("walk"),
    FIRE("fire"),
    JUMP("jump");

    private String name = null;
    private ActionStatus(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

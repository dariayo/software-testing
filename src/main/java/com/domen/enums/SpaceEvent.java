package com.domen.enums;

public enum SpaceEvent {
    ENGINE_START("Запуск двигателя"),
    AIR_RUSH("Поток воздуха"),
    CONFETTI_LIKE_EJECTION("Выброс как конфетти");

    private final String description;

    SpaceEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

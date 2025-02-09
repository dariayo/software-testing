package com.domen.enums;

public enum EngineStatus {
    OFF("Выключен"),
    ACTIVATING("Включается"),
    ON("Включен");

    private final String description;

    EngineStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

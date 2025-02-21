package com.domen.enums;

import lombok.Getter;

@Getter
public enum EngineStatus {
    OFF("Выключен"),
    ACTIVATING("Включается"),
    ON("Включен");
    final String description;
    EngineStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}

package com.domen.enums;

import lombok.Getter;

@Getter
public enum EngineSound {
    OFF("Нет звука"),
    QUIET("Зажужжал мотор"),
    MEDIUM("Тихий свист мотора"),
    LOUD("Рев мотора");
    final String description;

    EngineSound(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}

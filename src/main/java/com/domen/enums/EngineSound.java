package com.domen.enums;

import lombok.Getter;

@Getter
public enum EngineSound {
    OFF("Нет звука"),
    QUIET("Зажужжал мотор"),
    MEDIUM("Тихий свист мотора"),
    LOUD("Рев мотора");

    EngineSound(String description) {
    }
}

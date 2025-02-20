package com.domen.enums;

import lombok.Getter;

@Getter
public enum EngineStatus {
    OFF("Выключен"),
    ACTIVATING("Включается"),
    ON("Включен");

    EngineStatus(String description) {
    }
}

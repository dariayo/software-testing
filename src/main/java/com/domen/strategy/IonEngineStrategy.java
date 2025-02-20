package com.domen.strategy;

import com.domen.model.Engine;

public class IonEngineStrategy implements EngineStrategy {
    @Override
    public void increaseSpeed(Engine engine) {
        if (engine.getFuel() >= 5) {
            engine.setSpeed(engine.getSpeed() + 5);
            engine.setFuel(engine.getFuel() - 5);
            engine.getLogger().info("Ионный двигатель: скорость увеличена до " + engine.getSpeed());
        } else {
            engine.getLogger().info("Недостаточно топлива для ионного двигателя!");
        }
    }
}

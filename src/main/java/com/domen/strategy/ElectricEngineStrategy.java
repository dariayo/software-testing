package com.domen.strategy;

import com.domen.model.Engine;

public class ElectricEngineStrategy implements EngineStrategy {
    @Override
    public void increaseSpeed(Engine engine) {
        if (engine.getFuel() >= 10) {
            engine.setSpeed(engine.getSpeed() + 10);
            engine.setFuel(engine.getFuel() - 10);
            engine.getLogger().info("Электрический двигатель: скорость увеличена до " + engine.getSpeed());
        } else {
            engine.getLogger().info("Недостаточно топлива для электрического двигателя!");
        }
    }
}


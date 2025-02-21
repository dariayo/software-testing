package com.domen.model;

import com.domen.enums.EngineSound;
import com.domen.enums.EngineStatus;
import com.domen.enums.SpaceEvent;
import com.domen.mechanics.EjectionMechanism;
import com.domen.strategy.EngineStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Engine {
    private EngineSound sound;
    public EngineStatus status;
    public EjectionMechanism ejectionMechanism;
    private int fuel;
    private int speed;

    private final int maxFuel = 1000;
    private static final Logger logger = LoggerFactory.getLogger(Engine.class);
    private EngineStrategy engineStrategy;

    public Engine(EngineStrategy engineStrategy) {
        this.status = EngineStatus.OFF;
        this.ejectionMechanism = new EjectionMechanism();
        this.fuel = maxFuel;
        this.sound = EngineSound.OFF;
        this.engineStrategy = engineStrategy;
    }

    public void activate(Space space, Person... persons) {
        if (fuel <= 0) {
            logger.info("Топливо закончилось. Двигатель остановлен.");
            this.status = EngineStatus.OFF;
            return;
        }
        this.status = EngineStatus.ACTIVATING;
        triggerEvent(SpaceEvent.ENGINE_START);

        while (speed < 100 && fuel > 0) {
            engineStrategy.increaseSpeed(this);
            soundIncrease(speed);
        }

        triggerEvent(SpaceEvent.AIR_RUSH);
        this.status = EngineStatus.ON;
        triggerEvent(SpaceEvent.CONFETTI_LIKE_EJECTION);

        ejectionMechanism.ejectPersons(space, persons);
    }

    public void soundIncrease(int currentSpeed) {
        if (currentSpeed >= 0 && currentSpeed < 50) {
            this.sound = EngineSound.QUIET;
        }
        if (currentSpeed >= 50 && currentSpeed <= 200) {
            this.sound = EngineSound.MEDIUM;
        } else {
            this.sound = EngineSound.LOUD;
        }
        logger.info("Звук " + sound.getDescription());
    }

    public void refuel(int amount) {
        if (fuel + amount <= maxFuel) {
            fuel += amount;
            logger.info("Топливо пополнено. Текущий уровень: " + fuel);
        } else {
            fuel = maxFuel;
            logger.info("Топливный бак заполнен полностью.");
        }
    }

    private void triggerEvent(SpaceEvent event) {
        System.out.println(event.getDescription());
    }

    public EngineStatus getStatus() {
        return status;
    }

    public int getFuel() {
        return fuel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setEngineStrategy(EngineStrategy engineStrategy) {
        this.engineStrategy = engineStrategy;
    }

    public EngineStrategy getEngineStrategy() {
        return engineStrategy;
    }
}

package com.domen.tests;


import com.domen.enums.EngineStatus;
import com.domen.model.Engine;
import com.domen.model.Person;
import com.domen.model.Space;
import com.domen.strategy.ElectricEngineStrategy;
import com.domen.strategy.IonEngineStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineTest {
    private Engine engine;
    private Space space;
    private Person ford;
    private Person arthur;

    @BeforeEach
    void setUp() {
        engine = new Engine(new IonEngineStrategy());
        space = Space.getInstance();
        space.getFloatingPersons().clear();
        ford = new Person("Форд");
        arthur = new Person("Артур");
    }

    @Test
    void testEngineActivationAndEjection() {
        engine.activate(space, ford, arthur);

        assertEquals(EngineStatus.ON, engine.getStatus());
        assertTrue(ford.isInSpace());
        assertTrue(arthur.isInSpace());

        assertEquals(2, space.getFloatingPersons().size());
        assertTrue(space.getFloatingPersons().contains(ford));
        assertTrue(space.getFloatingPersons().contains(arthur));
    }

    @Test
    void testEjectionTrajectoryAndSpeed() {
        engine.activate(space, ford);

        assertTrue(ford.getTrajectoryAngle() >= 0 && ford.getTrajectoryAngle() <= 360);
        assertTrue(ford.getSpeed() >= 5 && ford.getSpeed() <= 20);
    }

    @Test
    void testIonEngineSpeedIncrease() {
        engine.speedIncrease();
        engine.speedIncrease();
        assertEquals(20, engine.getSpeed());
    }

    @Test
    void testElectricEngineSpeedIncrease() {
        engine.setEngineStrategy(new ElectricEngineStrategy());
        engine.speedIncrease();
        assertEquals(10, engine.getSpeed());
    }

    @Test
    void testNoFuelInEngine() {
        engine.setFuel(0);
        assertEquals(EngineStatus.OFF, engine.getStatus());
    }
}

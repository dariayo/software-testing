package com.domen.tests;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.domen.enums.EngineSound;
import com.domen.enums.EngineStatus;
import com.domen.model.Engine;
import com.domen.model.Person;
import com.domen.model.Space;
import com.domen.strategy.ElectricEngineStrategy;
import com.domen.strategy.EngineStrategy;
import com.domen.strategy.IonEngineStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.LoggerFactory;

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
        space = new Space();
        space.getFloatingPersons().clear();
        ford = new Person("Форд");
        arthur = new Person("Артур");
    }

    @Test
    void checkSequenceOfActions() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger(Engine.class).addAppender(listAppender);

        engine.refuel(10);
        engine.soundIncrease(200);

        boolean logContainsMessage = listAppender.list.stream()
                .anyMatch(event -> event.getMessage().contains("Двигатель выключен"));

        assertTrue(logContainsMessage);
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
        engine.getEngineStrategy().increaseSpeed(engine);
        engine.getEngineStrategy().increaseSpeed(engine);
        assertEquals(10, engine.getSpeed());
    }

    @Test
    void testElectricEngineSpeedIncrease() {
        engine.setEngineStrategy(new ElectricEngineStrategy());
        engine.getEngineStrategy().increaseSpeed(engine);
        assertEquals(10, engine.getSpeed());

        engine.setEngineStrategy(new IonEngineStrategy());
        engine.getEngineStrategy().increaseSpeed(engine);
        assertEquals(15, engine.getSpeed());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 'Топливо закончилось. Двигатель остановлен.'",
            "50, ''"
    })
    void testFuelLogMessages(int fuelLevel, String expectedMessage) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger(Engine.class).addAppender(listAppender);

        engine.setFuel(fuelLevel);

        engine.activate(space, ford);

        boolean logContainsMessage = listAppender.list.stream()
                .anyMatch(event -> event.getMessage().contains(expectedMessage));

        assertTrue(logContainsMessage);
    }

    @Test
    void testFuelRefuel() {
        engine.activate(space, ford);
        engine.setFuel(50);
        engine.setSpeed(50);
        engine.refuel(60);

        assertEquals(110, engine.getFuel());
        engine.refuel(1110);
        assertEquals(1000, engine.getFuel());
    }

    @ParameterizedTest
    @CsvSource({
            "IonEngineStrategy",
            "ElectricEngineStrategy"
    })
    void testFuelConsumptionAndRefuel(String engineStrategyClass) throws Exception {
        EngineStrategy strategy = (EngineStrategy) Class.forName("com.domen.strategy." + engineStrategyClass)
                .getDeclaredConstructor().newInstance();
        engine.activate(space, ford);
        engine.setEngineStrategy(strategy);
        engine.setFuel(1);
        engine.getEngineStrategy().increaseSpeed(engine);
        assertTrue(engine.getFuel() > 5);
    }

    @Test
    void testSoundIncrease() {
        engine.activate(space, ford);
        engine.soundIncrease(2000);
        assertEquals(EngineSound.LOUD.getDescription(), engine.getSound());

        engine.soundIncrease(45);
        assertEquals(EngineSound.QUIET.getDescription(), engine.getSound());

        engine.soundIncrease(200);
        assertEquals(EngineSound.MEDIUM.getDescription(), engine.getSound());

    }


}

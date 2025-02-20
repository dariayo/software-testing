package com.domen.tests;

import com.domen.model.Person;
import com.domen.model.Space;
import com.domen.mechanics.EjectionMechanism;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EjectionMechanismTest {

    private static final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    private Space space;
    private Person arthur;
    private EjectionMechanism ejectionMechanism;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        space = new Space();
        arthur = new Person("Артур");
        ejectionMechanism = new EjectionMechanism();

        listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger(EjectionMechanism.class).addAppender(listAppender);
    }

    private void clearLogs() {
        listAppender.clearAllFilters();
    }

    @Test
    void testNoPersonsToEject() {
        clearLogs();

        ejectionMechanism.ejectPersons(space);

        assertLogContains("Нет людей для выхода в космос");
    }

    @Test
    void testTooManyPeopleInSpace() {
        clearLogs();

        for (int i = 0; i < 10; i++) {
            space.addPerson(new Person("Человек " + (i + 1)));
        }

        ejectionMechanism.ejectPersons(space, arthur);

        assertLogContains("Слишком много людей за бортом. Артур не может быть выброшен");
    }

    @Test
    void testEjectPersonSuccessfully() {
        clearLogs();

        for (int i = 0; i < 5; i++) {
            space.addPerson(new Person("Человек " + (i + 1)));
        }

        ejectionMechanism.ejectPersons(space, arthur);

        assertEquals(6, space.getFloatingPersons().size());
        assertEquals("Артур", space.getFloatingPersons().get(5).getName());
    }

    private void assertLogContains(String expectedMessage) {
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.stream().anyMatch(event -> event.getMessage().contains(expectedMessage)),
                "Expected log: " + expectedMessage);
    }
}

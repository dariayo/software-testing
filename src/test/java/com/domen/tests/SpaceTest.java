package com.domen.tests;

import com.domen.model.Person;
import com.domen.model.Space;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceTest {

    private Space space;
    private Person arthur;

    @BeforeEach
    void setUp() {
        space = Space.getInstance();
        space.getFloatingPersons().clear();
        arthur = new Person("Артур");
    }

    @Test
    void testAddCharacterToSpace() {
        arthur.ejectToSpace(180, 12.5);
        space.addPerson(arthur);

        assertEquals(1, space.getFloatingPersons().size());
        assertEquals("Артур", space.getFloatingPersons().get(0).getName());
    }

    @Test
    void testAddMultipleCharactersToSpace() {
        Person arthur = new Person("Артур");
        Person ford = new Person("Форд");

        arthur.ejectToSpace(300, 20);
        ford.ejectToSpace(200, 18);

        space.addPerson(arthur);
        space.addPerson(ford);

        assertEquals(2, space.getFloatingPersons().size());
        assertEquals("Артур", space.getFloatingPersons().get(0).getName());
        assertEquals("Форд", space.getFloatingPersons().get(1).getName());
    }

    @ParameterizedTest
    @CsvSource({
            "10, true",
            "12, false"
    })
    void testAddPersonLimit(int numPeople, boolean expectedOutcome) {
        boolean result = true;

        for (int i = 0; i < numPeople; i++) {
            result = space.addPerson(new Person("Человек " + (i + 1)));
        }

        assertEquals(expectedOutcome, result);

        assertTrue(space.getFloatingPersons().size() <= 10);
    }


}

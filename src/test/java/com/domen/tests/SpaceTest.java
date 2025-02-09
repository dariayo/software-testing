package com.domen.tests;

import com.domen.model.Person;
import com.domen.model.Space;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceTest {

    @Test
    void testSpaceInitialization() {
        Space space = new Space(true, List.of("Звезда 1", "Звезда 2"));

        assertTrue(space.isVast());
        assertEquals(2, space.getBrightSpots().size());
        assertTrue(space.getBrightSpots().contains("Звезда 1"));
    }

    @Test
    void testAddCharacterToSpace() {
        Space space = new Space(true, List.of("Звезда 2"));
        Person arthur = new Person("Артур");

        arthur.ejectToSpace(180, 12.5);
        space.addPerson(arthur);

        assertEquals(1, space.getFloatingPersons().size());
        assertEquals("Артур", space.getFloatingPersons().get(0).getName());
    }
}

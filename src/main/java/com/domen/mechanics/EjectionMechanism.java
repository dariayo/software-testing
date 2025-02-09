package com.domen.mechanics;

import java.util.Random;

import com.domen.model.Person;
import com.domen.model.Space;

public class EjectionMechanism {
    private final Random random = new Random();

    public void ejectPersons(Space space, Person... persons) {
        for (Person person : persons) {
            double trajectoryAngle = random.nextDouble() * 360;
            double speed = 5 + random.nextDouble() * 15;
            person.ejectToSpace(trajectoryAngle, speed);
            space.addPerson(person);
        }
    }
}

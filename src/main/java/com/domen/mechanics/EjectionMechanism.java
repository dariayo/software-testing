package com.domen.mechanics;

import java.util.Random;


import com.domen.model.Person;
import com.domen.model.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EjectionMechanism {
    private static final Logger logger = LoggerFactory.getLogger(EjectionMechanism.class);
    private final Random random = new Random();

    public void ejectPersons(Space space, Person... persons) {
        if (persons.length == 0) {
            logger.info("Нет людей для выхода в космос");
            return;
        }
        for (Person person : persons) {
            if (space.getFloatingPersons().size() >= 10) {
                logger.info("Слишком много людей за бортом. " + person.getName() + " не может быть выброшен");
                continue;
            }
            double trajectoryAngle = random.nextDouble() * 360;
            double speed = 5 + random.nextDouble() * 15;
            person.ejectToSpace(trajectoryAngle, speed);
            space.addPerson(person);
        }
    }
}

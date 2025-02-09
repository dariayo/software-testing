package src.main.java.com.domen.mechanics;

import java.util.Random;

import src.main.java.com.domen.model.Space;
import src.main.java.com.domen.model.Person;

public class EjectionMechanism {
    private Random random = new Random();

    public void ejectPersons(Space space, Person... persons) {
        for (Person person : persons) {
            double trajectoryAngle = random.nextDouble() * 360;
            double speed = 5 + random.nextDouble() * 15;
            person.ejectToSpace(trajectoryAngle, speed);
            space.addPerson(person);
        }
    }
}

package com.domen.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class Space {
    private static final Space INSTANCE = new Space();
    private static final Logger logger = LoggerFactory.getLogger(Space.class);
    private final List<Person> floatingPersons;

    private Space() {
        List<String> brightSpots = generateStars();
        this.floatingPersons = new ArrayList<>();
    }


    public List<String> generateStars() {
        List<String> stars = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stars.add("Звезда " + (i + 1));
        }
        return stars;
    }

    public boolean addPerson(Person person) {
        if (floatingPersons.size() < 10) {
            floatingPersons.add(person);
            return true;
        } else {
            logger.info("Слишком много людей за бортом");
            return false;
        }
    }

    public List<Person> getFloatingPersons() {
        return floatingPersons;
    }

    public static Space getInstance() {
        return INSTANCE;
    }
}

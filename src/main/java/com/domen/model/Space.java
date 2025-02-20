package com.domen.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Space {
    private static final Logger logger = LoggerFactory.getLogger(Space.class);
    private final List<Person> floatingPersons;

    public Space() {
        this.floatingPersons = new ArrayList<>();
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
}

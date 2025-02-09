package com.domen.model;

import java.util.ArrayList;
import java.util.List;

public class Space {
    private final boolean isVast;
    private final List<String> brightSpots;
    private final List<Person> floatingPersons;

    public Space(boolean isVast, List<String> brightSpots) {
        this.isVast = isVast;
        this.brightSpots = brightSpots;
        this.floatingPersons = new ArrayList<>();
    }

    public boolean isVast() {
        return isVast;
    }

    public List<String> getBrightSpots() {
        return brightSpots;
    }

    public void addPerson(Person person) {
        floatingPersons.add(person);
    }

    public List<Person> getFloatingPersons() {
        return floatingPersons;
    }
}

package com.domen.model;

import java.util.ArrayList;
import java.util.List;

public class Space {
    private boolean isVast;
    private List<String> brightSpots;
    private List<Person> floatingPersons;

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

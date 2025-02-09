package com.domen.model;

public class Contributor {
    private final String name;
    private final int reputation;

    public Contributor(String name, int reputation) {
        this.name = name;
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public int getReputation() {
        return reputation;
    }
}

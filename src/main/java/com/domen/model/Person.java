package com.domen.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
    private static final Logger logger = LoggerFactory.getLogger(Person.class);
    private final String name;
    private boolean inSpace;
    private double trajectoryAngle;
    private double speed;

    public Person(String name) {
        this.name = name;
        this.inSpace = false;
    }

    public boolean isInSpace() {
        return inSpace;
    }

    public void ejectToSpace(double trajectoryAngle, double speed) {
        if (!inSpace) {
            this.inSpace = true;
            this.trajectoryAngle = trajectoryAngle;
            this.speed = speed;
        } else {
            logger.info(getName() + " уже в открытом космосе");
        }

    }

    public String getName() {
        return name;
    }

    public double getTrajectoryAngle() {
        return trajectoryAngle;
    }

    public double getSpeed() {
        return speed;
    }
}

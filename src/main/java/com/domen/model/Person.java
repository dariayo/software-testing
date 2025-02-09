package src.main.java.com.domen.model;

public class Person {
    private String name;
    private boolean inSpace;
    private double trajectoryAngle;
    private double speed;

    public Person(String name) {
        this.name = name;
        this.inSpace = false;
    }

    public String getName() {
        return name;
    }

    public boolean isInSpace() {
        return inSpace;
    }

    public void ejectToSpace(double trajectoryAngle, double speed) {
        this.inSpace = true;
        this.trajectoryAngle = trajectoryAngle;
        this.speed = speed;
    }

    public double getTrajectoryAngle() {
        return trajectoryAngle;
    }

    public double getSpeed() {
        return speed;
    }
}

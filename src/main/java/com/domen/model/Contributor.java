package src.main.java.com.domen.model;

public class Contributor {
    private String name;
    private int reputation;

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

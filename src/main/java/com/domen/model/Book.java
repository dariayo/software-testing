package src.main.java.com.domen.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String introduction;
    private List<Edition> editions;

    public Book(String title, String introduction) {
        this.title = title;
        this.introduction = introduction;
        this.editions = new ArrayList<>();
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }
}

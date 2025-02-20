package com.domen.model;


import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String title;
    private final String introduction;
    private final List<Edition> editions;

    public Book(BookBuilder bookBuilder) {
        this.title = bookBuilder.title;
        this.introduction = bookBuilder.introduction;
        this.editions = new ArrayList<>();
    }

    public void addEdition(Edition edition) {
        if (editions.stream().anyMatch(e -> e.getVersion() == edition.getVersion())) {
            throw new IllegalArgumentException("Редакция с версией " + edition.getVersion() + " уже существует");
        }
        editions.add(edition);
    }

    public static class BookBuilder {
        private String title;
        private String introduction;

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setIntroduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
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

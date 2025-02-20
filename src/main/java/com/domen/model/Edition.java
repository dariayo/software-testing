package com.domen.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Edition {
    private final int version;
    private final List<Contributor> contributors;
    private final List<Edit> editHistory;

    public Edition(int version) {
        this.version = version;
        this.contributors = new ArrayList<>();
        this.editHistory = new ArrayList<>();
    }

    public void addContributor(Contributor contributor) {
        if (!contributors.contains(contributor)) {
            contributors.add(contributor);
        } else {
            throw new IllegalStateException("Участник " + contributor.name() + " уже существует");
        }
    }

    public boolean submitEdit(Contributor contributor, String content) {
        for (Edit edit : editHistory) {
            if (edit.contributor().equals(contributor)) {
                throw new IllegalArgumentException("Конфликт: участник " + contributor.name() + " уже вносил изменения.");
            }
        }
        editHistory.add(new Edit(contributor, content, new Date()));
        return true;
    }
    public List<Contributor> getContributors() {
        return contributors;
    }

    public int getVersion(){
        return version;
    }
    public List<Edit> getEditHistory() {
        return editHistory;
    }
}

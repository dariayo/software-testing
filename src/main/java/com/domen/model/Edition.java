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
        contributors.add(contributor);
    }

    public boolean submitEdit(Contributor contributor, String content) {
        for (Edit edit : editHistory) {
            if (edit.getContributor().equals(contributor)) {
                System.out.println("Conflict detected: " + contributor.getName());
                return false;
            }
        }
        editHistory.add(new Edit(contributor, content, new Date()));
        return true;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public int getVersion() {
        return version;
    }

    public List<Edit> getEditHistory() {
        return editHistory;
    }
}

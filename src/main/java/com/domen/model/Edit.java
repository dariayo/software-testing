package com.domen.model;

import java.util.Date;

public class Edit {
    private Contributor contributor;
    private String content;
    private Date timestamp;

    public Edit(Contributor contributor, String content, Date timestamp) {
        this.contributor = contributor;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}

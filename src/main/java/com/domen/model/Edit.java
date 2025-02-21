package com.domen.model;

import java.util.Date;

public record Edit(Contributor contributor, String content, Date timestamp) {
}

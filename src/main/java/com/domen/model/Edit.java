package com.domen.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public record Edit(Contributor contributor, String content, Date timestamp) {
}

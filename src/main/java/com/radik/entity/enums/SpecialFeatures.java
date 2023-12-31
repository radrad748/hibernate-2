package com.radik.entity.enums;

public enum SpecialFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    private SpecialFeatures(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

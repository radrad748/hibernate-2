package com.radik.entity.enums;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    private Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

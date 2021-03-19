package com.example.genderguesser.models;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    INCONCLUSIVE("INCONCLUSIVE");

    private String name;

    public String getName() {
        return name;
    }

    Gender(String name) {
        this.name = name;
    }
}

package com.example.genderguesser.models;

public enum GuessVariant {

    SINGLE("SINGLE"),
    MULTIPLE("MULTIPLE");

    private String name;

    public String getName() {
        return name;
    }

    GuessVariant(String name) {
        this.name = name;
    }
}



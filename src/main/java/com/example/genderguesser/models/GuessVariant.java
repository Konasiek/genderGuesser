package com.example.genderguesser.models;

public enum GuessVariant {

    single("single"),
    multiple("multiple");

    private String name;

    public String getName() {
        return name;
    }

    GuessVariant(String name) {
        this.name = name;
    }
}



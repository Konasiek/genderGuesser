package com.example.genderguesser.models;

import org.springframework.hateoas.RepresentationModel;

public class GenderResponse extends RepresentationModel<GenderResponse> {

    private String name;
    private Enum<Gender> gender;
    private Enum<GuessVariant> guessVariant;

    public GenderResponse(String name, Enum<Gender> gender, Enum<GuessVariant> guessVariant) {
        this.name = name;
        this.gender = gender;
        this.guessVariant = guessVariant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public void setGender(Enum<Gender> gender) {
        this.gender = gender;
    }

    public Enum<GuessVariant> getGuessVariant() {
        return guessVariant;
    }

    public void setGuessVariant(Enum<GuessVariant> guessVariant) {
        this.guessVariant = guessVariant;
    }
}

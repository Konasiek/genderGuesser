package com.example.genderguesser.services;

public interface FlatFileService {

    boolean isMaleNameExist(String nameToCheck);

    boolean isFemaleNameExist(String nameToCheck);
}


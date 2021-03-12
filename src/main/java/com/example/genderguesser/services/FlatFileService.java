package com.example.genderguesser.services;

import java.util.List;

public interface FlatFileService {

    boolean isMaleNameExist(String nameToCheck);

    boolean isFemaleNameExist(String nameToCheck);

    List<String> getGenderTokens(String gender);
}


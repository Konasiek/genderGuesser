package com.example.genderguesser.services;

import java.util.List;

public interface FlatFileService {

    List<String> getGenderTokens(String gender);

    boolean isNameExist(String nameToCheck, String gender);
}


package com.example.genderguesser.services;

import java.util.List;

public interface GenderService {

    String checkSingleName(String name);

    String checkMultipleName(String name);

    List<String> getTokens(String gender);
}

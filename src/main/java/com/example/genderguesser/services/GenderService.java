package com.example.genderguesser.services;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenderService {

    String checkSingleName(String name);

    String checkMultipleName(String name);

    List<String> getTokens(String gender, Pageable paging);
}

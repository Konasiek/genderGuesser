package com.example.genderguesser.services;

import com.example.genderguesser.models.Gender;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenderService {

    Gender checkSingleName(String name);

    Gender checkMultipleName(String name);

    List<String> getTokens(Gender gender, Pageable paging);
}

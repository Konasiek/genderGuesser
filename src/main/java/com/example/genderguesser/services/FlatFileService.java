package com.example.genderguesser.services;

import com.example.genderguesser.models.Gender;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlatFileService {

    boolean isNameExist(String nameToCheck, Gender gender);

    List<String> getGenderTokens(Gender gender, Pageable paging);
}



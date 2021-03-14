package com.example.genderguesser.services;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlatFileService {

    boolean isNameExist(String nameToCheck, String gender);

    List<String> getGenderTokens(String gender, Pageable paging);
}



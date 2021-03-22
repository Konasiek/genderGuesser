package com.example.genderguesser.services;

import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlatFileService {

    boolean isNameExist(String nameToCheck, Gender gender);

    Page<Person> getGenderTokens(Gender gender, Pageable paging);
}



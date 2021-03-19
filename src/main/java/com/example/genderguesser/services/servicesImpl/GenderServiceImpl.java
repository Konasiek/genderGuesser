package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.models.Gender;
import com.example.genderguesser.services.FlatFileService;
import com.example.genderguesser.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GenderServiceImpl implements GenderService {

    private final static Logger LOGGER = Logger.getLogger(GenderServiceImpl.class.getName());

    private FlatFileService flatFileService;

    @Autowired
    public GenderServiceImpl(FlatFileService flatFileService) {
        this.flatFileService = flatFileService;
    }

    @Override
    public Gender checkSingleName(String givenString) {

        String nameToCheck = givenString.substring(0, givenString.indexOf(" "));

        if (!nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, Gender.MALE)) {
            return Gender.MALE;
        } else if (nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, Gender.FEMALE)) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    @Override
    public Gender checkMultipleName(String givenString) {

        List<String> givenNameList = new ArrayList<>(Arrays.asList(givenString.split(" ")));
        int maleCounter = 0;
        int femaleCounter = 0;

        for (String nameToCheck : givenNameList) {
            boolean existInMaleDB = false;
            boolean existInFemaleDB = false;
            if (flatFileService.isNameExist(nameToCheck, Gender.MALE)) {
                existInMaleDB = true;
            }
            if (flatFileService.isNameExist(nameToCheck, Gender.FEMALE)) {
                existInFemaleDB = true;
            }
            if (existInMaleDB && existInFemaleDB) {
                if (!nameToCheck.toUpperCase().endsWith("A")) {
                    maleCounter++;
                } else {
                    femaleCounter++;
                }
            } else if (existInMaleDB) {
                maleCounter++;
            } else if (existInFemaleDB) {
                femaleCounter++;
            }
        }

        LOGGER.info(Gender.MALE + " names occurrence: " + maleCounter);
        LOGGER.info(Gender.FEMALE + " names occurrence: " + femaleCounter);

        if (maleCounter > femaleCounter) {
            return Gender.MALE;
        } else if (femaleCounter > maleCounter) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    @Override
    public List<String> getTokens(Gender gender, Pageable paging) {
        return flatFileService.getGenderTokens(gender, paging);
    }
}

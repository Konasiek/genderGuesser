package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.services.FlatFileService;
import com.example.genderguesser.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    private FlatFileService flatFileService;

    @Autowired
    public GenderServiceImpl(FlatFileService flatFileService) {
        this.flatFileService = flatFileService;
    }

    @Override
    public String checkSingleName(String givenString) {

        String nameToCheck = givenString.substring(0, givenString.indexOf(" "));

        if (!nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, "male")) {
            return "MALE";
        } else if (nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, "female")) {
            return "FEMALE";
        } else {
            return "INCONCLUSIVE";
        }
    }

    @Override
    public String checkMultipleName(String givenString) {

        List<String> givenNameList = new ArrayList<>(Arrays.asList(givenString.split(" ")));
        int maleCounter = 0;
        int femaleCounter = 0;

        for (String nameToCheck : givenNameList) {
            if (!nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, "male")) {
                maleCounter++;
            } else if (nameToCheck.toUpperCase().endsWith("A") && flatFileService.isNameExist(nameToCheck, "female")) {
                femaleCounter++;
            }
        }
        System.out.println("maleCounter: " + maleCounter);
        System.out.println("femaleCounter: " + femaleCounter);

        if (maleCounter > femaleCounter) {
            return "MALE";
        } else if (femaleCounter > maleCounter) {
            return "FEMALE";
        } else {
            return "INCONCLUSIVE";
        }
    }

    @Override
    public List<String> getMaleTokens() {
        return flatFileService.getGenderTokens("male");
    }

    @Override
    public List<String> getFemaleTokens() {
        return flatFileService.getGenderTokens("female");
    }
}

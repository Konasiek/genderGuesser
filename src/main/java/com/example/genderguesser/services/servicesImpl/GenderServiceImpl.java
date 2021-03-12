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
    public String checkSingleName(String givenName) {

        String nameToCheck = givenName.substring(0, givenName.indexOf(" "));

        if (!nameToCheck.toUpperCase().endsWith("A") && flatFileService.isMaleNameExist(nameToCheck)) {
            return "MALE";
        } else if (flatFileService.isFemaleNameExist(nameToCheck)) {
            return "FEMALE";
        } else {
            return "INCONCLUSIVE";
        }
    }

    @Override
    public String checkMultipleName(String givenName) {

        List<String> givenNameList = new ArrayList<>(Arrays.asList(givenName.split(" ")));
        int maleCounter = 0;
        int femaleCounter = 0;

        for (String nameToCheck : givenNameList) {
            if (!nameToCheck.toUpperCase().endsWith("A") && flatFileService.isMaleNameExist(nameToCheck)) {
                maleCounter++;
            } else if (flatFileService.isFemaleNameExist(nameToCheck)) {
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
        return flatFileService.getMaleTokens();
    }

    @Override
    public List<String> getFemaleTokens() {
        return flatFileService.getFemaleTokens();
    }
}

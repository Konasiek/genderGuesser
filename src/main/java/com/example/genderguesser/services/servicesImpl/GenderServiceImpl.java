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

        //do string to check with only first word (.split)

        if (givenName.equals("Anna") || givenName.equals("Maria")) {
            return "FEMALE";
        } else if (givenName.equals("Adam") || givenName.equals("Marcin")) {
            return "MALE";
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
            if (flatFileService.isMaleNameExist(nameToCheck)) {
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
}

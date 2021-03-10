package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.services.GenderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Override
    public String checkSingleName(String name) {

        if (name.equals("Anna") || name.equals("Maria")) {
            return "FEMALE";
        } else if (name.equals("Adam") || name.equals("Marcin")) {
            return "MALE";
        } else {
            return "INCONCLUSIVE";
        }
    }

    @Override
    public String checkMultipleName(String name) {

        int maleCount = 0;
        int femaleCount = 0;

        String str = "Anna Maria Marcin Adam";
        List<String> stringList = new ArrayList<>(Arrays.asList(str.split(" ")));
        maleCount += stringList.stream().filter(s -> checkSingleName(s).equals("MALE")).count();
        femaleCount += stringList.stream().filter(s -> checkSingleName(s).equals("FEMALE")).count();

        if (maleCount > femaleCount) {
            return "MALE";
        } else if (femaleCount > maleCount) {
            return "FEMALE";
        } else {
            return "INCONCLUSIVE";
        }
    }
}

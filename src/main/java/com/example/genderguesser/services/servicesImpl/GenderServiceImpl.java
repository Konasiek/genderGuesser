package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.services.GenderService;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    @Override
    public String checkSingleName(String name) {

        if (name.equals("Anna") || name.equals("Maria")) {
            return "FEMALE";
        }
        else if (name.equals("Adam") || name.equals("Marcin")) {
            return "MALE";
        }
        else {
            return "INCONCLUSIVE";
        }
    }
}

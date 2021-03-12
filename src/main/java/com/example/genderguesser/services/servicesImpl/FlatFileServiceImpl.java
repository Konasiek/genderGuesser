package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.helpers.FlatFileConnector;
import com.example.genderguesser.models.Person;
import com.example.genderguesser.services.FlatFileService;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlatFileServiceImpl implements FlatFileService {

    private FlatFileConnector flatFileConnector;
    private static final int maleCSTLength = 23349;
    private static final int femaleCSVLength = 17381;

    @Autowired
    public FlatFileServiceImpl(FlatFileConnector flatFileConnector) {
        this.flatFileConnector = flatFileConnector;
    }

    @Override
    public boolean isMaleNameExist(String nameToCheck) {

        FlatFileItemReader<Person> maleReader = flatFileConnector.createMaleFlatFileConnection();

        maleReader.open(new ExecutionContext());

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < maleCSTLength; i++) {
            try {
                persons.add(maleReader.read());
                if (persons.get(i).getName().equals(nameToCheck.toUpperCase())) {
                    System.out.println("male db contains: " + nameToCheck);
                    maleReader.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        maleReader.close();
        return false;
    }

    @Override
    public boolean isFemaleNameExist(String nameToCheck) {

        FlatFileItemReader<Person> femaleReader = flatFileConnector.createFemaleFlatFileConnection();

        femaleReader.open(new ExecutionContext());

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < femaleCSVLength; i++) {
            try {
                persons.add(femaleReader.read());
                if (persons.get(i).getName().equals(nameToCheck.toUpperCase())) {
                    System.out.println("female db contains: " + nameToCheck);
                    femaleReader.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        femaleReader.close();

        return false;
    }

    @Override
    public List<String> getGenderTokens(String gender) {

        FlatFileItemReader<Person> fileReader;
        int fileCSVLength;
        if (gender.equals("male")) {
            fileReader = flatFileConnector.createMaleFlatFileConnection();
            fileCSVLength = maleCSTLength;
        } else if (gender.equals("female")) {
            fileReader = flatFileConnector.createFemaleFlatFileConnection();
            fileCSVLength = femaleCSVLength;
        } else {
            throw new RuntimeException("No such gender exist");
        }
        fileReader.open(new ExecutionContext());

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < fileCSVLength; i++) {
            try {
                persons.add(fileReader.read());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileReader.close();

        List<String> tokens = new ArrayList<>();
        for (Person person : persons) {
            tokens.add(person.getName());
        }
        return tokens;
    }
}

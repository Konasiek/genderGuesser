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

    @Autowired
    public FlatFileServiceImpl(FlatFileConnector flatFileConnector) {
        this.flatFileConnector = flatFileConnector;
    }

    @Override
    public boolean isNameExist(String nameToCheck, String gender) {

        FlatFileItemReader<Person> fileReader = flatFileConnector.loadFlatFile(gender);
        int fileCSVLength = flatFileConnector.loadCSVLength(gender);
        fileReader.open(new ExecutionContext());
        for (int i = 0; i < fileCSVLength; i++) {
            try {
                if (fileReader.read().getName().equals(nameToCheck.toUpperCase())) {
                    System.out.println(gender + " db contains: " + nameToCheck);
                    fileReader.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileReader.close();
        return false;
    }

    @Override
    public List<String> getGenderTokens(String gender) {

        FlatFileItemReader<Person> fileReader = flatFileConnector.loadFlatFile(gender);
        int fileCSVLength = flatFileConnector.loadCSVLength(gender);

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

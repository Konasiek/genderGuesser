package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.models.Person;
import com.example.genderguesser.services.GenderService;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

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

        try {
            FlatFileItemReader<Person> maleReader = new FlatFileItemReader<>();
            maleReader.setResource(new FileSystemResource("src/main/resources/male.csv"));
            maleReader.setLineMapper(new DefaultLineMapper<>() {
                {
                    setLineTokenizer(new DelimitedLineTokenizer() {
                        {
                            setNames("name");
                        }
                    });
                    setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                        {
                            setTargetType(Person.class);
                        }
                    });
                }
            });

            maleReader.open(new ExecutionContext());
            for (String n : givenNameList) {
                List<Person> persons = new ArrayList<>();
                for (int i = 0; i < 10743; i++) {
                    persons.add(maleReader.read());

                    if (persons.get(i).getName().equals(n.toUpperCase())) {
                        System.out.println("db contains: " + n.toUpperCase());
                        maleCounter++;
                        break;
                    }
                }
            }
            maleReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (maleCounter > femaleCounter) {
            return "MALE";
        } else if (femaleCounter > maleCounter) {
            return "FEMALE";
        } else {
            return "INCONCLUSIVE";
        }
    }
}

package com.example.genderguesser.helpers.helpersImpl;

import com.example.genderguesser.helpers.FlatFileConnector;
import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class FlatFileConnectorImpl implements FlatFileConnector {

    private FlatFileItemReader<Person> createFlatFileConnection(String gender) {

        FlatFileItemReader<Person> fileReader = new FlatFileItemReader<>();
        if (gender.equals(Gender.MALE.getName())) {
            fileReader.setResource(new FileSystemResource("src/main/resources/male.csv"));
        } else if (gender.equals(Gender.FEMALE.getName())) {
            fileReader.setResource(new FileSystemResource("src/main/resources/female.csv"));
        }
        fileReader.setLineMapper(new DefaultLineMapper<>() {
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
        return fileReader;
    }

    @Override
    public FlatFileItemReader<Person> loadFlatFile(String gender) {

        FlatFileItemReader<Person> fileReader;
        if (gender.equals(Gender.MALE.getName()) || gender.equals(Gender.FEMALE.getName())) {
            fileReader = createFlatFileConnection(gender);
        } else {
            throw new RuntimeException("No such gender exist");
        }
        return fileReader;
    }
}

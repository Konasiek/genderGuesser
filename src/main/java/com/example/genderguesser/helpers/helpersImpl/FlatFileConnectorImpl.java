package com.example.genderguesser.helpers.helpersImpl;

import com.example.genderguesser.helpers.FlatFileConnector;
import com.example.genderguesser.models.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class FlatFileConnectorImpl implements FlatFileConnector {

    private static final int maleCSTLength = 23349;
    private static final int femaleCSVLength = 17381;

    @Override
    public FlatFileItemReader<Person> createMaleFlatFileConnection() {

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
        return maleReader;
    }

    @Override
    public FlatFileItemReader<Person> createFemaleFlatFileConnection() {

        FlatFileItemReader<Person> femaleReader = new FlatFileItemReader<>();
        femaleReader.setResource(new FileSystemResource("src/main/resources/female.csv"));
        femaleReader.setLineMapper(new DefaultLineMapper<>() {
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
        return femaleReader;
    }

    @Override
    public FlatFileItemReader<Person> loadFlatFile(String gender) {

        FlatFileItemReader<Person> fileReader;
        if (gender.equals("male")) {
            fileReader = createMaleFlatFileConnection();
        } else if (gender.equals("female")) {
            fileReader = createFemaleFlatFileConnection();
        } else {
            throw new RuntimeException("No such gender exist");
        }
        return fileReader;
    }

    @Override
    public int loadCSVLength(String gender) {

        int fileCSVLength;
        if (gender.equals("male")) {
            fileCSVLength = maleCSTLength;
        } else if (gender.equals("female")) {
            fileCSVLength = femaleCSVLength;
        } else {
            throw new RuntimeException("No such gender exist");
        }
        return fileCSVLength;
    }
}

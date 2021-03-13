package com.example.genderguesser.helpers;

import com.example.genderguesser.models.Person;
import org.springframework.batch.item.file.FlatFileItemReader;

public interface FlatFileConnector {

    FlatFileItemReader<Person> loadFlatFile(String gender);

    int loadCSVLength(String gender);
}

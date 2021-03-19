package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.helpers.helpersImpl.FlatFileConnectorImpl;
import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlatFileServiceImplTest {

    @Mock
    FlatFileConnectorImpl flatFileConnectorImpl;
    
    @InjectMocks
    FlatFileServiceImpl flatFileServiceImpl = new FlatFileServiceImpl(flatFileConnectorImpl);

    @Mock
    FlatFileItemReader<Person> fileReader;

    @Test
    @Disabled
    void isNameExist() throws Exception {
        fileReader = new FlatFileItemReader<>();
        FlatFileItemReader<Person> fileReader = new FlatFileItemReader<>();
        fileReader.setResource(new FileSystemResource("src/main/resources/male.csv"));

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
        fileReader.open(new ExecutionContext());
        Person person = fileReader.read();
        fileReader.close();
        when(this.flatFileConnectorImpl.loadFlatFile(Gender.MALE)).thenReturn(fileReader);
        assertTrue(this.flatFileServiceImpl.isNameExist(person.getName(), Gender.MALE));
    }

    @Test
    @Disabled
    void getGenderTokens() {
    }
}

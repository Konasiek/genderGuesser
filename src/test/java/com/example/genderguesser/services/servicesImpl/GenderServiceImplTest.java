package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.controllers.GenderController;
import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class GenderServiceImplTest {

    @Mock
    FlatFileServiceImpl flatFileServiceImpl;

    @InjectMocks
    GenderServiceImpl genderServiceImpl = new GenderServiceImpl(flatFileServiceImpl);

    @Test
    void checkSingleName() {
        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.FEMALE)).thenReturn(false);
        assertEquals(Gender.MALE, this.genderServiceImpl.checkSingleName("Adam Niedzielski"));

        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        assertEquals(Gender.FEMALE, genderServiceImpl.checkSingleName("Ewa Zofia Maciąg"));

        when(this.flatFileServiceImpl.isNameExist("Asdfsdfsdfsdf", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Asdfsdfsdfsdf", Gender.FEMALE)).thenReturn(false);
        assertEquals(Gender.INCONCLUSIVE, genderServiceImpl.checkSingleName("Asdfsdfsdfsdf Zofia Maciąg"));

        verify(this.flatFileServiceImpl, times(6)).isNameExist(anyString(), any());
    }

    @Test
    void checkMultipleName() {
        when(this.flatFileServiceImpl.isNameExist("Maria", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Maria", Gender.FEMALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Konrad", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Konrad", Gender.FEMALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Józef", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Józef", Gender.FEMALE)).thenReturn(false);
        assertEquals(Gender.MALE, this.genderServiceImpl.checkMultipleName("Maria Konrad Józef"));

        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.FEMALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Zofia", Gender.FEMALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Zofia", Gender.MALE)).thenReturn(false);
        assertEquals(Gender.FEMALE, this.genderServiceImpl.checkMultipleName("Adam Ewa Zofia"));

        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.FEMALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        assertEquals(Gender.INCONCLUSIVE, this.genderServiceImpl.checkMultipleName("Adam Ewa"));

        when(this.flatFileServiceImpl.isNameExist("Anudfdf", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Anudfdf", Gender.FEMALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Knuisdf", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Knuisdf", Gender.FEMALE)).thenReturn(false);
        assertEquals(Gender.INCONCLUSIVE, this.genderServiceImpl.checkMultipleName("Anudfdf Knuisdf"));

        verify(this.flatFileServiceImpl, times(20)).isNameExist(anyString(), any());
    }

    Pageable paging;
    List<Person> persons;
    Page<Person> pagePerson;
    int totalElements;

    @Test
    void getTokens() {
        paging = PageRequest.of(0, 4);
        totalElements = 100;
        persons = Arrays.asList(
                new Person("Adam"), new Person("Krzysztof"), new Person("Zenon"), new Person("Marcin"));
        pagePerson = new PageImpl<>(persons, paging, totalElements);

        when(this.flatFileServiceImpl.getGenderTokens(Gender.MALE, paging)).thenReturn(pagePerson);
        assertEquals(pagePerson, this.genderServiceImpl.getTokens(Gender.MALE, paging));

        verify(this.flatFileServiceImpl, times(1)).getGenderTokens(any(), any());
    }
}

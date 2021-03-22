package com.example.genderguesser.controllers;

import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.GenderResponse;
import com.example.genderguesser.models.GuessVariant;
import com.example.genderguesser.models.Person;
import com.example.genderguesser.services.GenderService;
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
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class GenderControllerTest {

    @Mock
    GenderService genderService;

    @InjectMocks
    GenderController genderController = new GenderController(genderService);

    GenderResponse genderResponse;
    Link link;
    EntityModel<GenderResponse> genderResponseEntityModel;
    ResponseEntity<EntityModel<GenderResponse>> responseGender;

    @Test
    void guessGender() {
        genderResponse = new GenderResponse("Konrad Ewa Dąbrowski", Gender.MALE, GuessVariant.SINGLE);
        link = linkTo(methodOn(GenderController.class).guessGender("Konrad Ewa Dąbrowski", GuessVariant.SINGLE)).withSelfRel();
        genderResponseEntityModel = EntityModel.of(genderResponse, link);
        responseGender = new ResponseEntity<>(genderResponseEntityModel, HttpStatus.OK);

        when(this.genderService.checkSingleName("Konrad Ewa Dąbrowski")).thenReturn(Gender.MALE);
        assertEquals(responseGender, genderController.guessGender("Konrad Ewa Dąbrowski", GuessVariant.SINGLE));

        genderResponse = new GenderResponse("Mateusz Ewa Jan", Gender.MALE, GuessVariant.MULTIPLE);
        link = linkTo(methodOn(GenderController.class).guessGender("Mateusz Ewa Jan", GuessVariant.MULTIPLE)).withSelfRel();
        genderResponseEntityModel = EntityModel.of(genderResponse, link);
        responseGender = new ResponseEntity<>(genderResponseEntityModel, HttpStatus.OK);

        when(this.genderService.checkMultipleName("Mateusz Ewa Jan")).thenReturn(Gender.MALE);
        assertEquals(responseGender, genderController.guessGender("Mateusz Ewa Jan", GuessVariant.MULTIPLE));

        verify(this.genderService, times(1)).checkSingleName(anyString());
        verify(this.genderService, times(1)).checkMultipleName(anyString());
    }

    Pageable paging;
    List<Person> persons;
    Page<Person> pagePerson;
    EntityModel<Page<Person>> personEntityModel;
    ResponseEntity<EntityModel<Page<Person>>> response;
    int totalElements;

    @Test
    void getTokens() {
        paging = PageRequest.of(0, 4);
        totalElements = 100;
        persons = Arrays.asList(
                new Person("Adam"), new Person("Krzysztof"), new Person("Zenon"), new Person("Marcin"));
        pagePerson = new PageImpl<>(persons, paging, totalElements);
        link = linkTo(methodOn(GenderController.class)
                .getTokens(Gender.MALE, paging.getPageNumber(), paging.getPageSize())).withSelfRel();
        personEntityModel = EntityModel.of(pagePerson, link);
        response = new ResponseEntity<>(personEntityModel, HttpStatus.OK);

        when(this.genderService.getTokens(Gender.MALE, paging)).thenReturn(pagePerson);
        assertEquals(response, genderController.getTokens(Gender.MALE, 0, 4));

        assertEquals(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR),
                genderController.getTokens(Gender.MALE, -5, 4));

        verify(this.genderService, times(1)).getTokens(any(), any());
    }
}

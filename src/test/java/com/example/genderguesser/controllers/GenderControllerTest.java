package com.example.genderguesser.controllers;

import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.GuessVariant;
import com.example.genderguesser.services.GenderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenderControllerTest {

    @Mock
    GenderService genderService;

    @InjectMocks
    GenderController genderController = new GenderController(genderService);

    @Test
    void guessGender() {

        ResponseEntity<Gender> maleResponseEntity = new ResponseEntity<>(Gender.MALE, HttpStatus.OK);
        ResponseEntity<Gender> femaleResponseEntity = new ResponseEntity<>(Gender.FEMALE, HttpStatus.OK);
        ResponseEntity<Gender> inconclusiveResponseEntity = new ResponseEntity<>(Gender.INCONCLUSIVE, HttpStatus.OK);

        when(this.genderService.checkSingleName("Mateusz Ewa Dobrowolski")).thenReturn(Gender.MALE);
        assertEquals(maleResponseEntity, genderController.guessGender("Mateusz Ewa Dobrowolski", GuessVariant.SINGLE));

        when(this.genderService.checkSingleName("Paulina")).thenReturn(Gender.FEMALE);
        assertEquals(femaleResponseEntity, genderController.guessGender("Paulina", GuessVariant.SINGLE));

        when(this.genderService.checkSingleName("")).thenReturn(Gender.INCONCLUSIVE);
        assertEquals(inconclusiveResponseEntity, genderController.guessGender("", GuessVariant.SINGLE));

        when(this.genderService.checkMultipleName("Mateusz Ewa Jan")).thenReturn(Gender.MALE);
        assertEquals(maleResponseEntity, genderController.guessGender("Mateusz Ewa Jan", GuessVariant.MULTIPLE));

        when(this.genderService.checkMultipleName("Mateusz Ewa Maria")).thenReturn(Gender.FEMALE);
        assertEquals(femaleResponseEntity, genderController.guessGender("Mateusz Ewa Maria", GuessVariant.MULTIPLE));

        when(this.genderService.checkMultipleName("")).thenReturn(Gender.INCONCLUSIVE);
        assertEquals(inconclusiveResponseEntity, genderController.guessGender("", GuessVariant.MULTIPLE));

        when(this.genderService.checkMultipleName(null)).thenReturn(Gender.INCONCLUSIVE);
        assertEquals(inconclusiveResponseEntity, genderController.guessGender(null, GuessVariant.MULTIPLE));

        verify(this.genderService, times(3)).checkSingleName(anyString());
        verify(this.genderService, times(3)).checkMultipleName(anyString());
    }

    Pageable paging;
    List<String> tokens;

//    @Test
//    void getTokens() {
//        paging = PageRequest.of(0, 4);
//        tokens = Arrays.asList("Adam", "Krzysztof", "Zenon", "Marcin");
//        when(this.genderService.getTokens(Gender.MALE, paging)).thenReturn(tokens);
//        assertEquals(tokens, genderService.getTokens(Gender.MALE, paging));
//
//        paging = PageRequest.of(2, 10);
//        tokens = Arrays.asList("Maria", "Natalia", "Zofia", "Jadwiga", "Teresa", "Ludmiła", "Mariola", "Anna", "Anka", "Magda");
//        when(this.genderService.getTokens(Gender.FEMALE, paging)).thenReturn(tokens);
//        assertEquals(tokens, genderService.getTokens(Gender.FEMALE, paging));
//
//        verify(this.genderService, times(2)).getTokens(any(), any());
//    }
}

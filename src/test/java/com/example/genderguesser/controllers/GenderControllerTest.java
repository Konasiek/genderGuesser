package com.example.genderguesser.controllers;

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

//    @Disabled
//    @Test
//    void guessGender() {
//        ResponseEntity<String> maleResponseEntity = new ResponseEntity<>("MALE", HttpStatus.OK);
//        ResponseEntity<String> femaleResponseEntity = new ResponseEntity<>("FEMALE", HttpStatus.OK);
//        ResponseEntity<String> inconclusiveResponseEntity = new ResponseEntity<>("INCONCLUSIVE", HttpStatus.OK);
//        //male single
//        when(this.genderService.checkSingleName("Mateusz Ewa Dobrowolski")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Mateusz Ewa Dobrowolski", "single"));
//        when(this.genderService.checkSingleName("Konrad Maja Merkwa")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Konrad Maja Merkwa", "single"));
//        when(this.genderService.checkSingleName("Józef Piłsudzki")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Józef Piłsudzki", "single"));
//        //female single
//        when(this.genderService.checkSingleName("Paulina")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Paulina", "single"));
//        when(this.genderService.checkSingleName("Maria")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Maria", "single"));
//        when(this.genderService.checkSingleName("Józefina")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Józefina", "single"));
//        //inconclusive single
//        when(this.genderService.checkSingleName("")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("", "single"));
//        when(this.genderService.checkSingleName("Jan Maria Rokita")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("Jan Maria Rokita", "single"));
//        when(this.genderService.checkSingleName("Zofia Adam Małysz")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("Zofia Adam Małysz", "single"));
//        //male multiple
//        when(this.genderService.checkMultipleName("Mateusz Ewa Jan")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Mateusz Ewa Jan", "multiple"));
//        when(this.genderService.checkMultipleName("Maria Jan Filip")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Maria Jan Filip", "multiple"));
//        when(this.genderService.checkMultipleName("Witold Damian Zofia")).thenReturn("MALE");
//        assertEquals(maleResponseEntity, genderController.guessGender("Witold Damian Zofia", "multiple"));
//        //female multiple
//        when(this.genderService.checkMultipleName("Mateusz Ewa Maria")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Mateusz Ewa Maria", "multiple"));
//        when(this.genderService.checkMultipleName("Jan Magda Mariola")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Jan Magda Mariola", "multiple"));
//        when(this.genderService.checkMultipleName("Urszula Ewa Maciek")).thenReturn("FEMALE");
//        assertEquals(femaleResponseEntity, genderController.guessGender("Urszula Ewa Maciek", "multiple"));
//        //inconclusive multiple
//        when(this.genderService.checkMultipleName("")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("", "multiple"));
//        when(this.genderService.checkMultipleName("Jan Maria Rokita")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("Jan Maria Rokita", "multiple"));
//        when(this.genderService.checkMultipleName("Zofia Adam Małysz")).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender("Zofia Adam Małysz", "multiple"));
//        //other
//        when(this.genderService.checkMultipleName(null)).thenReturn("INCONCLUSIVE");
//        assertEquals(inconclusiveResponseEntity, genderController.guessGender(null, "multiple"));
//
//        verify(this.genderService, times(9)).checkSingleName(anyString());
//        verify(this.genderService, times(9)).checkMultipleName(anyString());
//    }
//
//    Pageable paging;
//    List<String> tokens;

//    @Test
//    void getTokens() {
//        paging = PageRequest.of(0, 4);
//        tokens = Arrays.asList("Adam", "Krzysztof", "Zenon", "Marcin");
//        when(this.genderService.getTokens("male", paging)).thenReturn(tokens);
//        assertEquals(tokens, genderService.getTokens("male", paging));
//
//        paging = PageRequest.of(2, 10);
//        tokens = Arrays.asList("Maria", "Natalia", "Zofia", "Jadwiga", "Teresa", "Ludmiła", "Mariola", "Anna", "Anka", "Magda");
//        when(this.genderService.getTokens("female", paging)).thenReturn(tokens);
//        assertEquals(tokens, genderService.getTokens("female", paging));
//    }
}

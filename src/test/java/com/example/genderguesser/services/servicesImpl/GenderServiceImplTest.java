package com.example.genderguesser.services.servicesImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenderServiceImplTest {

    @Mock
    FlatFileServiceImpl flatFileServiceImpl;

    @InjectMocks
    GenderServiceImpl genderService = new GenderServiceImpl(flatFileServiceImpl);

//    @Test
//    void checkSingleName() {
//        when(this.flatFileServiceImpl.isNameExist("Adam", "male")).thenReturn(true);
//        assertEquals("MALE", this.genderService.checkSingleName("Adam Niedzielski"));
//
//        when(this.flatFileServiceImpl.isNameExist("Ewa", "female")).thenReturn(true);
//        assertEquals("FEMALE", genderService.checkSingleName("Ewa Zofia Maciąg"));
//
//        when(this.flatFileServiceImpl.isNameExist("Asdfsdfsdfsdf", "male")).thenReturn(false);
//        assertEquals("INCONCLUSIVE", genderService.checkSingleName("Asdfsdfsdfsdf Zofia Maciąg"));
//
//        verify(this.flatFileServiceImpl, times(3)).isNameExist(anyString(), anyString());
//    }
//
//    @Test
//    void checkMultipleName() {
//        when(this.flatFileServiceImpl.isNameExist("Maria", "female")).thenReturn(true);
//        when(this.flatFileServiceImpl.isNameExist("Konrad", "male")).thenReturn(true);
//        when(this.flatFileServiceImpl.isNameExist("Józef", "male")).thenReturn(true);
//        assertEquals("MALE", this.genderService.checkMultipleName("Maria Konrad Józef"));
//
//        when(this.flatFileServiceImpl.isNameExist("Adam", "male")).thenReturn(true);
//        when(this.flatFileServiceImpl.isNameExist("Ewa", "female")).thenReturn(true);
//        when(this.flatFileServiceImpl.isNameExist("Zofia", "female")).thenReturn(true);
//        assertEquals("FEMALE", this.genderService.checkMultipleName("Adam Ewa Zofia"));
//
//        when(this.flatFileServiceImpl.isNameExist("Adam", "male")).thenReturn(true);
//        when(this.flatFileServiceImpl.isNameExist("Ewa", "female")).thenReturn(true);
//        assertEquals("INCONCLUSIVE", this.genderService.checkMultipleName("Adam Ewa"));
//
//        when(this.flatFileServiceImpl.isNameExist("Anudfdf", "male")).thenReturn(false);
//        when(this.flatFileServiceImpl.isNameExist("Knuisdf", "male")).thenReturn(false);
//        assertEquals("INCONCLUSIVE", this.genderService.checkMultipleName("Anudfdf Knuisdf"));
//
//        verify(this.flatFileServiceImpl, times(10)).isNameExist(anyString(), anyString());
//    }
//
//    Pageable paging;
//    List<String> tokens;
//
//    @Test
//    void getTokens() {
//        paging = PageRequest.of(0, 4);
//        tokens = Arrays.asList("Adam", "Krzysztof", "Zenon", "Marcin");
//        when(this.flatFileServiceImpl.getGenderTokens("male", paging)).thenReturn(tokens);
//        assertEquals(tokens, this.genderService.getTokens("male", paging));
//
//        paging = PageRequest.of(5, 8);
//        tokens = Arrays.asList("Paulina", "Maria", "Nina", "Daria", "Edyta", "Magda", "Teresa", "Krystyna");
//        when(this.flatFileServiceImpl.getGenderTokens("female", paging)).thenReturn(tokens);
//        assertEquals(tokens, this.genderService.getTokens("female", paging));
//
//        verify(this.flatFileServiceImpl, times(2)).getGenderTokens(anyString(), any());
//    }
}

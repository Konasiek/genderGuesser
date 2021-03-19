package com.example.genderguesser.services.servicesImpl;

import com.example.genderguesser.models.Gender;
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

    @Test
    void checkSingleName() {
        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        assertEquals(Gender.MALE, this.genderService.checkSingleName("Adam Niedzielski"));

        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        assertEquals(Gender.FEMALE, genderService.checkSingleName("Ewa Zofia Maciąg"));

        when(this.flatFileServiceImpl.isNameExist("Asdfsdfsdfsdf", Gender.MALE)).thenReturn(false);
        assertEquals(Gender.INCONCLUSIVE, genderService.checkSingleName("Asdfsdfsdfsdf Zofia Maciąg"));

        verify(this.flatFileServiceImpl, times(3)).isNameExist(anyString(), any());
    }

    @Test
    void checkMultipleName() {
        when(this.flatFileServiceImpl.isNameExist("Maria", Gender.FEMALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Konrad", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Józef", Gender.MALE)).thenReturn(true);
        assertEquals(Gender.MALE, this.genderService.checkMultipleName("Maria Konrad Józef"));

        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Zofia", Gender.FEMALE)).thenReturn(true);
        assertEquals(Gender.FEMALE, this.genderService.checkMultipleName("Adam Ewa Zofia"));

        when(this.flatFileServiceImpl.isNameExist("Adam", Gender.MALE)).thenReturn(true);
        when(this.flatFileServiceImpl.isNameExist("Ewa", Gender.FEMALE)).thenReturn(true);
        assertEquals(Gender.INCONCLUSIVE, this.genderService.checkMultipleName("Adam Ewa"));

        when(this.flatFileServiceImpl.isNameExist("Anudfdf", Gender.MALE)).thenReturn(false);
        when(this.flatFileServiceImpl.isNameExist("Knuisdf", Gender.MALE)).thenReturn(false);
        assertEquals(Gender.INCONCLUSIVE, this.genderService.checkMultipleName("Anudfdf Knuisdf"));

        verify(this.flatFileServiceImpl, times(10)).isNameExist(anyString(), any());
    }

    Pageable paging;
    List<String> tokens;

    @Test
    void getTokens() {
        paging = PageRequest.of(0, 4);
        tokens = Arrays.asList("Adam", "Krzysztof", "Zenon", "Marcin");
        when(this.flatFileServiceImpl.getGenderTokens(Gender.MALE, paging)).thenReturn(tokens);
        assertEquals(tokens, this.genderService.getTokens(Gender.MALE, paging));

        paging = PageRequest.of(5, 8);
        tokens = Arrays.asList("Paulina", "Maria", "Nina", "Daria", "Edyta", "Magda", "Teresa", "Krystyna");
        when(this.flatFileServiceImpl.getGenderTokens(Gender.FEMALE, paging)).thenReturn(tokens);
        assertEquals(tokens, this.genderService.getTokens(Gender.FEMALE, paging));

        verify(this.flatFileServiceImpl, times(2)).getGenderTokens(any(), any());
    }
}

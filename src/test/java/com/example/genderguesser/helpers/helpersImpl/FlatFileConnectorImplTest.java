package com.example.genderguesser.helpers.helpersImpl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FlatFileConnectorImplTest {

    @InjectMocks
    FlatFileConnectorImpl flatFileConnectorImpl;

    @Test
    @Disabled
    void createFlatFileConnection() {
    }

    @Test
    @Disabled
    void loadFlatFile() {
    }

    @Test
    void loadCSVLength() {
        int maleCSTLength = 23349;
        assertEquals(maleCSTLength, this.flatFileConnectorImpl.loadCSVLength("male"));
        int femaleCSVLength = 17381;
        assertEquals(femaleCSVLength, this.flatFileConnectorImpl.loadCSVLength("female"));
    }
}

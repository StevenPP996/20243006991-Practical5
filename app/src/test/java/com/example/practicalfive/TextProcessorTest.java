package com.example.practicalfive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TextProcessorTest {
    @Test
    public void normalisesSupportedNamesRegardlessOfCase() {
        assertEquals("Beijing", TextProcessor.processMunicipality("beijing"));
        assertEquals("Shanghai", TextProcessor.processMunicipality("SHANGHAI"));
        assertEquals("Tianjin", TextProcessor.processMunicipality(" tIaNjIn "));
        assertEquals("Chongqing", TextProcessor.processMunicipality("ChOnGqInG"));
    }

    @Test
    public void rejectsNullBlankAndUnsupportedInput() {
        assertNull(TextProcessor.processMunicipality(null));
        assertNull(TextProcessor.processMunicipality(""));
        assertNull(TextProcessor.processMunicipality("  \t  "));
        assertNull(TextProcessor.processMunicipality("Guangzhou"));
    }
}

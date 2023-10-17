package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value1 = translator.translateNumber(5);
        assertEquals(value1, "fünf");

    }

    @Test
    void aNegativeTest() {
        GermanTranslator translator = new GermanTranslator();
        String value1 = translator.translateNumber(-15);
        assertEquals(value1, "Übersetzung der Zahl -15 nicht möglich (Version: 1.0)");

    }

    @Test
    void aNullTest() {
        GermanTranslator translator = new GermanTranslator();
        String value1 = translator.translateNumber(0);
        assertEquals(value1, "Übersetzung der Zahl 0 nicht möglich (Version: 1.0)");

    }
}
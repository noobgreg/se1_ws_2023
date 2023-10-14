package org.hbrs.se1.ws23.uebung1.control;

public class TranslatorFactory {
    // Neue Klasse erstellt um die INstanz innerhalb dieser Klasse aufrufen zu können. Eine Methode angelegt,
    // um ein GermanTranslator-Objekt zu erzeugen.
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}

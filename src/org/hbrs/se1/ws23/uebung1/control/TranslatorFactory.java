package org.hbrs.se1.ws23.uebung1.control;

public class TranslatorFactory {
    // Neue Klasse erstellt um die Instanz innerhalb dieser Klasse aufrufen zu können. Eine Methode angelegt,
    // die ein GermanTranslator-Objekt erzeugt.
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}

package org.hbrs.se1.ws23.uebung1.control;

public class TestingTranslator {

    public static void main(String[] args){
        GermanTranslator translator = new GermanTranslator();
        int input = -15;
        String output = translator.translateNumber(input);

        System.out.println("Eingabe: " + input + "\n" + "Ausgabe: " + output);
    }
}


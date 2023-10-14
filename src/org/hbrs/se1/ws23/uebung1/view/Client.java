package org.hbrs.se1.ws23.uebung1.view;
import org.hbrs.se1.ws23.uebung1.control.Translator;

public class Client {

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 * (auch bezeichnet als CLI, Terminal)
	 *
	 */


	// Hier muss zuerst die neue Klasse aus dem control package importiert werden um auf die Methode zugreifen zu können,
	// die die GermanTranslator Instanz erstellt.
	 void display( int aNumber ){

		 String translation = Translator.translateNumber(aNumber);
		 System.out.println("Das Ergebnis der Berechnung: " + translation);

	 }
}






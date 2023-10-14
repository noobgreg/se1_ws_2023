package org.hbrs.se1.ws23.uebung1.control;

/**
 * Das Translator Interface. Die Anzahl der Methoden ist fix
 * und darf NICHT erweitert werden. Sichtbarkeiten der Methoden koennen
 * unter Umstaenden angepasst werden.
 *
 * @author saschaalda
 *
 */
// Interface musste 'public' gemacht werden, damit man auch ausserhalb des control packages auf das Interface zugreifen kann.
// Die Methode translateNumber muss 'static' sein.

public interface Translator {
	
	double version = 1.0; // Version des Interface
	
	/*
	 * Uebersetzt eine numerische Zahl in eine String-basierte
	 * Repraesentation gemaess der Spezifikation in der Aufgabe 1-2 
	 */
	public static String translateNumber(int number) {
		return "null";
	}

} 









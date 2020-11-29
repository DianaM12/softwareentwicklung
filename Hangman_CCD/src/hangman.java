import java.util.Scanner;

/**
 * Hangman - Spiel
 * 
 * @author Diana
 *
 */
public class hangman {

	public static void main(String[] args) {
		String gesuchtesWort = generiereSuchwort();
		int fehlversuche = 0;
		String buchstabe = "";
		String fehler = "";
		String loesung = "";
		String eingabe = "";
		int maximaleFehlversuche = 6; // Nach 6 Fehlversuchen ist der Hangman gehängt und das Spiel vorbei
		
		while (fehlversuche < maximaleFehlversuche) {
			System.out.println(maleHangman(fehlversuche));
			loesung = suchVersuch(gesuchtesWort, buchstabe);
			System.out.println(loesung);
			
			if (loesung.indexOf("_") != -1) {
				System.out.println(fehler);
			} else {
				System.out.println("Gewonnen, das Wort lautete \"" + gesuchtesWort + "\".");
				break;
			}
			
			try {
				eingabe = buchstabenEingeben(buchstabe);
				buchstabe += eingabe;
				if (sucheBuchstaben(gesuchtesWort, eingabe) == false) {
					fehlversuche++;
				}
			} catch (Exception e) {
				fehler = e.getMessage();
				fehlversuche++;
			}
			
			System.out.println(saeubereSpielfeld());
		}
		
		if (fehlversuche >= maximaleFehlversuche) {
			System.out.println(maleHangman(fehlversuche));
			System.out.println("Verloren, das Wort lautete \"" + gesuchtesWort + "\".");
		}
		
	}

	/**
	 * Generiert einen String in welchem alle nicht enthaltene Buchstaben von
	 * Wort durch_ ersetzt werden
	 * @param wort das zu erratene Wort
	 * @param Zeichen alle eingegebenen Buchstaben
	 * @return Eine Zeichenkette, die mit dem Suchwort und dem ersetzten Zeichen
	 */

	public static String suchVersuch(String wort, String zeichen) {
		String gesuchtesWort = "Suchwort: ";
		boolean fund = false;
		
		for (String s : wort.split("")) {
			fund = false;
			for (String buchstaben : zeichen.split("")) {
				String suchwortBuchstaben = s.toLowerCase();
				String eingabeBuchstaben = buchstaben.toLowerCase();
				if (suchwortBuchstaben.equals(eingabeBuchstaben)) {
					gesuchtesWort += s + " ";
					fund = true;
				}
			}
			if (fund == false) {
				gesuchtesWort += "_ ";
			}
		}
		return gesuchtesWort;
	}

	/**
	 * Testet ob das Zeichen in dem Wort vorkommt
	 * @param wort das zu erratene Wort
	 * @param buchstaben das Zeichen als String das getestet werden soll
	 * @return true wenn das Zeichen enthalten ist, false wenn nicht
	 */
	
	public static boolean sucheBuchstaben(String wort, String buchstaben) {
		boolean fund = false;
		
		for (String s : wort.split("")) {
			for (String zeichen : buchstaben.split("")) {
				String suchwortBuchstaben = s.toLowerCase();
				String eingabeBuchstaben = zeichen.toLowerCase();
				if (suchwortBuchstaben.equals(eingabeBuchstaben)) {
					fund = true;
					break;
				}
			}
		}
		return fund;
	}

	/**
	 * Es wird ein Zeichen eingegeben welches spaeter getestet werden soll
	 * doppelte Ausgaben werfen Fehler aus mehr als 1 Zeichen werfen Fehler aus
	 * @param eingabe Die vorher eingegebene Zeichenkette
	 * @return Das eingegebene Zeichen
	 * @throws ArithmeticExpection Fehlerstring, wenn die eingabe nicht den Vorgaben entspricht
	 */
	public static String buchstabenEingeben(String eingabe) throws ArithmeticException {
		Scanner in = new Scanner(System.in);
		System.out.println("Welchen Buchstaben willst du testen?");
		String name = in.nextLine();
		in = null;
		
		if (name.length() == 1) {
			eingabe = eingabe.toLowerCase();
			if (eingabe.indexOf(name) != -1) {
				throw new ArithmeticException("Fehler! " + name + " hattest du schon probiert!");
			} else {
				return name;
			}
		} else {
			throw new ArithmeticException("Fehler! Bitte gib einen Buchstaben ein!");
		}
	}

	/**
	 * Scrollt die Console nach unten, so das das alte Spielfeld nicht mehr
	 * sichtbar ist
	 * @return Zeilenumbrueche
	 */
	public static String saeubereSpielfeld() {
		String umbrueche = String.format("%1$" + 20 + "s", "").replace(' ', '\n');
		return umbrueche;
	}

	/**
	 * Generiert ein zu erratenes Wort
	 * @return das zu erratene Wort
	 */
	public static String generiereSuchwort() {
		String suchwoerter = "java softwareentwicklung objektorientierung Python";
		String[] woerterArray = suchwoerter.split(" +");
		int Random = (int) (Math.random() * 100);
		
		while (Random >= woerterArray.length) {
			Random = (int) (Math.random() * 100);
		}
		return woerterArray[Random];
	}

	/**
	 * Gibt einen Hangman aus in dem Status je nach dem wieviele Fehler man hat
	 * @param i die Anzahl der Fehlversuche
	 * @return der hangman im jeweiligen Zustand
	 */
	public static String maleHangman(int i) {
		String hangman = "";
		hangman += "  +-----+\n";
		if (i >= 1) {
			hangman += "  |/    o\n";
		} else {
			hangman += "  |/\n";
		}
		if (i < 2) {
			hangman += "  |      \n";
		}
		if (i == 2) {
			hangman += "  |     +   \n";
		}
		if (i == 3) {
			hangman += "  |   --+\n";
		}
		if (i > 3) {
			hangman += "  |   --+--\n";
		}
		if (i >= 2) {
			hangman += "  |     |\n";
		} else {
			hangman += "  |   \n";
		}
		if (i == 5) {
			hangman += "  |    / \n";
		}
		if (i > 5) {
			hangman += "  |    / \\\n";
		}
		if (i < 5) {
			hangman += "  |  \n";
		}
		hangman += " ***\n";
		hangman += "***********\n";
		hangman += "\n";
		return hangman;
	}
}

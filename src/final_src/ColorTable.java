import java.util.*;
import java.awt.Color;

/**
 * Classe des corespondance Entier -> Couleur.
 */

class ColorTable {

	// Un environnement est codé à l'aide d'une table associative
	// qui associe des Couleur à des entiers.
	private Map<Integer, Color> table;

	/**
	 * Constructeur.
	 */
	public ColorTable() {
		table = new Hashtable<Integer, Color>();
	}

	/**
	 * Associe la couleur c à l'entier n.
	 */
	public void associer(int n, Color c) {
		table.put(n, c); // Autoboxing
	}

	/**
	 * Récupère la Couleur associée à l'entier n. Lève une exception si n n'est
	 * pas connue dans l'environnement.
	 */
	public Color obtenirColor(int n) {
		if (contient(n)) {
			return table.get(n); // Autoboxing 
		} else {
			throw new RuntimeException("cet état " + n +  " est inconnue dans l'environnement de Couleur");
		}
	}

	public boolean contient(int n) {
		return table.containsKey(n);
	}

	@Override
	public String toString() {
		return "ColorTable : " + table;
	}
}

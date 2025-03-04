package baseDonnees.bases;

/**
 * Cette interface spécifie les méthodes publique des colonnes d'une base de données.
 * 
 * Services publiques:
 * 	- ajouterValeur
 * 	- obtenirValeur
 * 	- obtenirIndex
 * 	- changerValeur
 * 	- getNbElements
 * 	- afficherContenu
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2025
 *
 * @param <V> le type de l'attribut contenue dans la colonne
 */

public interface InterfaceColonne<V> {

	/**
	 * ajoute une valeur à la colonne
	 * @param valeur valeur à ajouter
	 */
	public abstract void ajouterValeur(V valeur);
	
	/**
	 * obtient, sans retirer, une valeur de la colonne 
	 * @param index index de la valeur
	 * @return la valeur
	 * @throws Exception lève une exception si index invalide
	 */
	public abstract V obtenirValeur(int index) throws Exception;
	
	/**
	 * obtient l'index de la valeur demandé, ou -1 si non présent
	 * @param valeur valeur demandé
	 * @return index de la valeur
	 */
	public abstract int obtenirIndex(V valeur);
	
	/**
	 * remplace une valeur dans la colonne
	 * @param index index de la valeur à remplacer
	 * @param valeur valeur à insérer
	 * @throws Exception lève une exception si index invalide
	 */
	public abstract void changerValeur(int index, V valeur) throws Exception;
	
	/**
	 * retourne le nombre d'élément dans la colonne
	 * @return le nombre d'élément dans la colonne
	 */
	public abstract int getNbElements();

	/**
	 * affiche le contenu de la colonne (déverminage)
	 */
	public abstract void afficherContenu();
	
}

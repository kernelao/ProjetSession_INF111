package baseDonnees.bases;

/**
 * Cette interface sp�cifie les m�thodes publique des colonnes d'une base de donn�es.
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
	 * ajoute une valeur � la colonne
	 * @param valeur valeur � ajouter
	 */
	public abstract void ajouterValeur(V valeur);
	
	/**
	 * obtient, sans retirer, une valeur de la colonne 
	 * @param index index de la valeur
	 * @return la valeur
	 * @throws Exception l�ve une exception si index invalide
	 */
	public abstract V obtenirValeur(int index) throws Exception;
	
	/**
	 * obtient l'index de la valeur demand�, ou -1 si non pr�sent
	 * @param valeur valeur demand�
	 * @return index de la valeur
	 */
	public abstract int obtenirIndex(V valeur);
	
	/**
	 * remplace une valeur dans la colonne
	 * @param index index de la valeur � remplacer
	 * @param valeur valeur � ins�rer
	 * @throws Exception l�ve une exception si index invalide
	 */
	public abstract void changerValeur(int index, V valeur) throws Exception;
	
	/**
	 * retourne le nombre d'�l�ment dans la colonne
	 * @return le nombre d'�l�ment dans la colonne
	 */
	public abstract int getNbElements();

	/**
	 * affiche le contenu de la colonne (d�verminage)
	 */
	public abstract void afficherContenu();
	
}

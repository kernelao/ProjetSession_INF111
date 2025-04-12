package baseDonnees.bases;

/**
 * Cette classe d�finit une colonne de la base de donn�es
 * 
 * La colonne offre plusieurs services permettant d'ajouter et d'acc�der aux
 * �l�ments stock� dans la colonne.
 * 
 * Les services offerts:
 * 		- obtenirValeur
 * 		- obtenirIndex
 * 		- ajouterALaFin
 * 		- enleverValeur
 * 		- changerValeur
 *
 * @author Fred Simard | ETS
 * @version Automne 2020
 * @revision et commentaire Pierre B�lisle
 */

@SuppressWarnings("unchecked")
public class Colonne<V> implements InterfaceColonne<V> {

	// Retourn�e par obtenirIndex si la valeur cherch�e est absente.
	public static final int ELEMENT_ABSENT = -1;

	// Capacit� d'une colonne au d�part.
	public static final int CAPACITE = 10;
	
	// Le tableau contenant les donn�es d'une colonne et 
	// son nombre d'�l�ments significatifs.
	protected V[] tab;
	protected int nbElements = 0;
	
	/**
	 * Construteur par d�faut avec une capacit� de Colonne.CAPACITE
	 */
	public Colonne() {
		
		/*
		 * Strat�gie : Avec un tableau d'objets transtyp� pour le type g�n�rique.
		 * Il n'est pas possible d'instancier un type g�n�rique directement
		 *  (new V[])
		 */
		tab = (V[]) new Object[CAPACITE];
	}

	/**
	 * Ajoute une valeur � la fin de la colonne.
	 * 
	 * @param valeur � ajouter
	 */
	public void ajouterValeur(V valeur) {
		
		ajusterTailleListe();
		
		tab[nbElements] = valeur;
		nbElements++;
	}
	
	/**
	 * Retourne la valeur � l'index.
	 * 
	 * @param index La position pour laquelle ont veut la valeur
	 * @return La valeur � la posiiton de l'index.
	 */
	public V obtenirValeur(int index) throws Exception{	

		validerIndex(index);
		
		return tab[index];
	}
	
    /**
     * Proc�dure locale pour �viter de r�p�ter la condition de validation 
     * de l'index.
     * 
     * @param index index � valider
     * @throws Exception index <0 || index > nbElements
     */
	private void validerIndex(int index) throws Exception{
		
		if(index<0 || index>=nbElements) {
			throw new Exception("Index invalide");
		}
	}

	/**
	 * Retourne la position de la valeur dans la colonne ou Colonne.ELEMENT_ABSENT.
	 * 
	 * @param valeur La valeur � chercher.
	 * @return la position de la valeur dans la colonne ou Colonne.ELEMENT_ABSENT.
	 */
	public int obtenirIndex(V valeur) {
		
		/*
		 * Strat�gie : Impl�mentation de la fouille lin�aire
		 */
		int indexARetourner = ELEMENT_ABSENT;
		int i=0;
		
		while(i<nbElements && indexARetourner == ELEMENT_ABSENT) {
			if(tab[i].equals(valeur)){
				indexARetourner = i;
			}
			i++;
		}
		
		return indexARetourner;
	}
	
	
	/**
	 * Si l'index est valide, la valeur remplace celle � cette position.
	 * 
	 * @param index l'index de la valeur � changer
	 * @param valeur nouvelle valeur
	 */
	public void changerValeur(int index, V valeur) throws Exception{
		
		validerIndex(index);
		tab[index] = valeur;
	}

	/**
	 * Augmente la taille du tableau lorsqu'il est plein.
	 */
	private void ajusterTailleListe(){
		
		if(tab.length == nbElements) {
			
			V[] nouveauTab = (V[]) new Object[tab.length*2];

			copierTab(nouveauTab, tab);
			
			tab = nouveauTab;
			
		}
	}
	
	/**
	 * Proc�dure priv�e qui copie les valeurs d'un tableau dans un autre tableau
	 * 
	 * @param nouveauTab destination des donn�es
	 * @param tab origine des donn�es
	 */
	protected void copierTab(Object[] nouveauTab, Object[] tab) {
		
		for(int i=0;i<tab.length;i++) {
			nouveauTab[i] = tab[i];
		}
		
	}

	/**
	 * Accesseur du nombre d'�l�ments.
	 */
	public int getNbElements() {
		return nbElements;
	}

	/**
	 * Affiche le contenu des la liste dans la console.
	 * Sert essentiellement au d�baogage.
	 */
	public void afficherContenu(){
		
		for(int i=0;i<nbElements;i++) {
			System.out.println(tab[i]);
		}
	}

}

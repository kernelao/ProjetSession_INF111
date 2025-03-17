package draft;

import colonnes.Colonne;

/**
 * Ce fichier impl�mente la colonne index�e qui utilise la fouille binaire comme
 * technique de recherche. Il s'agit d'une classe d�riv� de colonne.
 *
 * La description des requis se trouve dans l'�nonc� du devoir, section 2.3.
 *
 * La classe interne ValIndexee est fournie.
 *
 * @author Fred Simard | ETS
 * @version Hiver 2025
 *
 * @param <V> le type de l'attribut contenue dans la colonne
 */


@SuppressWarnings("rawtypes")
public class ColonneIndexee<E extends Comparable<E>> extends Colonne<E> {

    public static final int PAS_UNIQUE = -1;
    public static final int ELEMENT_ABSENT = -1;

    private ValIndexee[] valIndexee;

    private class ValIndexee<E extends Comparable<E>> {

		public int index;
		public E valeur;

		public ValIndexee(int index, E valeur2) {
			this.index = index;
			this.valeur = valeur2;
		}

		public int comparer(E valeur) {
			return this.valeur.compareTo(valeur);
		}
	}

    // Constructeurs
    public ColonneIndexee(int capaciteInitiale) {
        super(capaciteInitiale);
    }
    public ColonneIndexee() {
        super(CAPACITE_INITIALE_PAR_DEFAUT);
    }

}

package colonnes;

/**
 * Ce fichier implémente la colonne indexée qui utilise la fouille binaire comme
 * technique de recherche. Il s'agit d'une classe dérivé de colonne.
 *
 * La description des requis se trouve dans l'énoncé du devoir, section 2.3.
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

    // Constantes
    public static final int PAS_UNIQUE = -1;
    public static final int ELEMENT_ABSENT = -1;

    // Attributs
    private ValIndexee[] valIndexee;

    // Système d'indexation
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
        this.valIndexee = new ValIndexee[capaciteInitiale];
    }
    public ColonneIndexee() {
        super(CAPACITE_INITIALE_PAR_DEFAUT);
        this.valIndexee = new ValIndexee[CAPACITE_INITIALE_PAR_DEFAUT];
    }

    // Vérifie si une valeur est unique
    public boolean estUnique(E valeur) {
        int debut = 0;
        int fin = tailleActuelle - 1;

        while (debut <= fin) {
            int milieu = (debut + fin) / 2;

            if (valIndexee[milieu] == null) {
                return true; // ✅ Si on trouve un emplacement vide, c'est unique
            }

            int comparaison = valIndexee[milieu].comparer(valeur);

            if (comparaison == 0) {
                return false;
            } else if (comparaison < 0) {
                debut = milieu + 1;
            } else {
                fin = milieu - 1;
            }
        }

        return true;
    }

    @Override
    public void ajouterValeur(E valeur) {

        // Ici on vient chercher le nombre d'élément actuelle, = indice oû sera stocké la nouvelle valeur
        // Classe hérité, donc au niveau de la colonne, super s'en occupe
        int index = super.getNbElements();
        super.ajouterValeur(valeur);

        // Redimensionner de valIndexee
        if (this.tailleActuelle == this.valIndexee.length) {

            int capaciteNouvelle = this.valIndexee.length * 2;
            ValIndexee[] nouveauTableau = new ValIndexee[capaciteNouvelle];

            for (int i = 0; i < valIndexee.length; i++) {
                nouveauTableau[i] = this.valIndexee[i];
            }
            this.valIndexee = nouveauTableau;
        }

        // Méthode pour trouver la position d'insertion (fouille binaire/dichotomique)
        int debut = 0;
        int fin = this.tailleActuelle - 1;
        int position = 0;

        while (debut <= fin) {
            int milieu = (debut + fin) / 2;

            if (this.valIndexee[milieu] == null) {
                fin = milieu - 1;
            } else {
                int comparaison = this.valIndexee[milieu].comparer(valeur);

                if (comparaison < 0) {
                    debut = milieu + 1;
                } else {
                    fin = milieu - 1;
                }
            }
        }
        position = debut;

        // Décaler les éléments pour insérer la valeur à la bonne position
        for (int i = this.tailleActuelle; i > position; i--) {
            this.valIndexee[i] = this.valIndexee[i - 1];
        }

        // Insérer la nouvelle valeur indexée
        valIndexee[position] = new ValIndexee<>(index, valeur);
    }

    @Override
    public int obtenirIndex(E valeur) {
        boolean trouvee = false;
        int debut = 0;
        int fin = tailleActuelle - 1;
        int milieu = -1;

        while (debut <= fin && !trouvee) {
            milieu = (debut + fin) / 2;

            if (valIndexee[milieu] == null) {
                return ELEMENT_ABSENT;
            }

            int comparaison = valIndexee[milieu].comparer(valeur);

            if (comparaison == 0) {
                trouvee = true; // valeur trouvée
            } else if (comparaison > 0) {
                fin = milieu - 1; // chercher à gauche
            } else {
                debut = milieu + 1; // chercher à droite
            }
        }

        if (trouvee) {
            return valIndexee[milieu].index;
        } else {
            return ELEMENT_ABSENT;
        }
    }


    @Override
    public void afficherContenu() {
        System.out.println("Paires index/valeurs : ");
        for (int i = 0; i < tailleActuelle; i++) {
            System.out.println("Index : " + this.valIndexee[i].index + " / Valeur : "
                    + this.valIndexee[i].valeur);
        }
        super.afficherContenu();
    }

    @Override
    public void changerValeur(int index, E valeur) throws Exception {
        throw new UnsupportedOperationException("changerValeur() n'est pas supporté dans ColonneIndexee.");
    }
}
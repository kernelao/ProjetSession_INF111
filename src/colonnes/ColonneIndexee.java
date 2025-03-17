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

    // Méthode pour ajouter une valeur. On ajoute la valeur dans un tableau hérité de la Colonne
    //
    @Override
    public void ajouterValeur(E valeur) {

        // Ici on vient chercher le nombre d'élément actuelle, = indice oû sera stocké la nouvelle valeur
        // Classe hérité, donc au niveau de la colonne, super s'en occupe
        int index = super.getNbElements();
        super.ajouterValeur(valeur);

        // Valider/ajuster la taille du tableau valIndexee
        validerTailleTableau();

        // Sous-méthode pour trouver la position d'insertion avec une fouille linéaire (contrainte)
        int position = trouverPositionInsertion(valeur);

        // Décaler les éléments pour insérer la valeur à la bonne position
        for (int i = this.nbElements; i > position; i--) {
            this.valIndexee[i] = this.valIndexee[i - 1];
        }

        // Insérer la nouvelle valeur indexée
        valIndexee[position] = new ValIndexee<>(index, valeur);
        this.nbElements++;
    }



    // Vérifie si une valeur est unique
    public boolean estUnique(E valeur) {
        int debut = 0;
        int fin = this.nbElements - 1;

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
    public int obtenirIndex(E valeur) {
        boolean trouvee = false;
        int debut = 0;
        int fin = nbElements - 1;
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
        for (int i = 0; i < this.nbElements; i++) {
            if (this.valIndexee[i] != null) {
                System.out.println("Index : " + this.valIndexee[i].index + " / Valeur : " + this.valIndexee[i].valeur);
            }
        }
        super.afficherContenu();
    }

    @Override
    public void changerValeur(int index, E valeur) throws Exception {
        throw new UnsupportedOperationException("changerValeur() n'est pas supporté dans ColonneIndexee.");
    }

    // Sous-méthode pour vérifier/agrandir tableau si besoin
    private void validerTailleTableau() {
        if (this.nbElements >= this.valIndexee.length) {
            this.capaciteActuelle = this.valIndexee.length * 2;
            ValIndexee[] nouveauTableau = new ValIndexee[this.capaciteActuelle];
            int compteur = 0;
            for (int i = 0; i < this.valIndexee.length; i++) {
                nouveauTableau[i] = this.valIndexee[i];
                compteur++;
            }
            this.valIndexee = nouveauTableau;
            this.nbElements = compteur;
        }
    }

    // Sous-méthode appelée par ajouterValeur pour trouver position d'insertion avec fouille linéaire
    private int trouverPositionInsertion(E valeur) {
        int position = 0;

        while (position < this.nbElements && valIndexee[position] != null) {
            if (this.valIndexee[position].valeur.compareTo(valeur) > 0) {
                break;
            }
            position++;
        }
        return position;
    }

    // Sous-méthode appelée par ajouterValeur pour trouver position d'insertion avec fouille linéaire
    private int rechercheBinaire(E valeur) {
        int debut = 0;
        int fin = this.nbElements - 1;
        int milieu = -1;
        boolean trouvee = false;
        while (debut <= fin && !trouvee) {
            milieu = (debut + fin) / 2;

            if (valIndexee[milieu] == null) {
                return ELEMENT_ABSENT; // gestion exception ICI
            }

            int comparaison = this.valIndexee[milieu].valeur.compareTo(valeur);

            if (comparaison == 0) {
                return milieu; // La valeur existe déjà dans `valIndexee`
            } else if (comparaison < 0) {
                debut = milieu + 1; // Chercher à droite
            } else {
                fin = milieu - 1; // Chercher à gauche
            }
        }

        return -1;
        }
    }


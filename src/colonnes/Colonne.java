package colonnes;

public class Colonne<V> implements InterfaceColonne<V> {

    /*
     * Implémenter d'un type de données abstrait avec une approche statique // pas de ArrayList/Vector
     * Donc, on utilise un tableau contigue V[] (fixe) qu'on redimensionne au préalable
     * */

    // Constantes
    public static final int CAPACITE_INITIALE_PAR_DEFAUT = 15;
    public static final int VALEUR_NON_TROUVEE = -1;

    // Attributs
    protected V[] liste;
    protected int nbElements;
    protected int capaciteActuelle;

    // Constructeur avec capacité initiale et un autre sans argument
    public Colonne(int capaciteInitiale) {
        // https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
        this.liste = (V[]) new Object[capaciteInitiale];
        this.capaciteActuelle = capaciteInitiale;
        this.nbElements = 0;
    }
    public Colonne() {
        // https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
        this.liste = (V[]) new Object[CAPACITE_INITIALE_PAR_DEFAUT];
        this.capaciteActuelle = CAPACITE_INITIALE_PAR_DEFAUT;
        this.nbElements = 0;
    }

    // Méthode pour ajouter une valeur à la fin de notre tableau
    // Sous-méthode : vérifier si le tableau est plein, si oui, le doubler et copier le contenu
    @Override
    public void ajouterValeur(V valeur) {
        validerTailleTableau();
        this.liste[this.nbElements] = valeur;
        this.nbElements++;
    }

    // Méthode qui retourne la valeur liée à l'index passé en paramètre
    @Override
    public V obtenirValeur(int index) throws Exception {
        if (index < 0 || index > nbElements) {
            throw new Exception("L'index " + index + " est invalide");
        }
        return liste[index];
    }

    // Méthode qui détermine l'index d'une valeur passée en paramètre
    @Override
    public int obtenirIndex(V valeur) {
        for (int i = 0; i < nbElements; i++) {
            if (liste[i].equals(valeur)) {
                return i;
            }
        }
        return VALEUR_NON_TROUVEE;
    }

    // Méthode qui modifie l'élément de l'index passé en paramètre par la valeur passée en paramètre
    @Override
    public void changerValeur(int index, V valeur) throws Exception {
        if (index < 0 || index > nbElements) {
            throw new Exception("L'index  " + index + " est invalide");
        }
        liste[index] = valeur;
    }

    // Retourne le nombre actuel d'éléments dans le tableau
    @Override
    public int getNbElements() {
        return this.nbElements;
    }

    // Méthodes non obligatoire, pour tester
    public int getCapaciteActuelle() {
        return this.capaciteActuelle;
    }

    // Méthode qui permet d'afficher le contenu de la liste
    @Override
    public void afficherContenu() {
        System.out.println("\n Valeurs de la colonne : ");
        for (int i = 0; i < this.nbElements; i++) {
            System.out.println("Élément : " + (i+1) + " -- " + liste[i]);
        }
    }

    // Sous-méthode privée utilisée par la méthode ajouterValeur, est utilisée dans le cas oû le tableau
    // ne permet pas l'ajout à cause du manque d'espace : créer un autre et copier le contenu
    private void validerTailleTableau() {
        if (this.nbElements == this.liste.length) {
            this.capaciteActuelle = liste.length * 2;
            V[] listeNouvelle = (V[]) new Object[this.capaciteActuelle];
            int compteur = 0;
            for (int i = 0; i < this.nbElements; i++) {
                listeNouvelle[i] = this.liste[i];
                compteur++;
            } // ou : System.arraycopy(this.liste, 0, listeNouvelle, 0, this.nbElements);
            this.liste = listeNouvelle;
            this.nbElements = compteur;
        }
    }
}

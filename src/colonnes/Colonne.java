package colonnes;

public class Colonne<V> implements InterfaceColonne<V> {

    /*
     * Implémenter d'un type de données abstrait avec une approche statique
     * Donc, on utilise un tableau contigue V[] (fixe) qu'on redimensionne au préalable
     * */

    // Constantes
    public static final int CAPACITE_INITIALE_PAR_DEFAUT = 15;

    // Attributs
    protected V[] liste;
    protected int tailleActuelle; // On aurait pu utiliser liste.length, mais on choisit d'utiliser un attribut propre pour cela
    protected int capaciteActuelle;

    // Constructeur avec capacité initiale et un autre sans argument
    public Colonne(int capaciteInitiale) {
        // https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
        this.liste = (V[]) new Object[capaciteInitiale];
        this.capaciteActuelle = capaciteInitiale;
        this.tailleActuelle = 0;
    }
    public Colonne() {
        // https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
        this.liste = (V[]) new Object[CAPACITE_INITIALE_PAR_DEFAUT];
        this.capaciteActuelle = CAPACITE_INITIALE_PAR_DEFAUT;
        this.tailleActuelle = 0;
    }

    @Override
    public void ajouterValeur(V valeur) {

        if (this.tailleActuelle == this.capaciteActuelle) {

            int capaciteNouvelle = this.capaciteActuelle * 2;
            V[] listeNouvelle = (V[]) new Object[capaciteNouvelle];

            for (int i = 0; i < this.tailleActuelle; i++) {
                listeNouvelle[i] = this.liste[i];
            }
            this.capaciteActuelle = capaciteNouvelle;
            this.liste = listeNouvelle;
        }

        this.liste[this.tailleActuelle++] = valeur;
    }

    @Override
    public V obtenirValeur(int index) throws Exception {
        if (index < 0 || index > tailleActuelle) {
            throw new Exception("Index invalide " + index);
        }
        return liste[index];
    }

    @Override
    public int obtenirIndex(V valeur) {
        for (int i = 0; i < tailleActuelle; i++) {
            if (liste[i].equals(valeur)) {
                return i;
            }
        }
        return -1; // Valeur non trouvée
    }

    @Override
    public void changerValeur(int index, V valeur) throws Exception {
        if (index < 0 || index > tailleActuelle) {
            throw new Exception("Index invalide " + index);
        }
        liste[index] = valeur;
    }

    @Override
    public int getNbElements() {
        return this.tailleActuelle;
    }

    // Méthodes non obligatoire, pour tester
    public int getCapaciteActuelle() {
        return this.capaciteActuelle;
    }

    @Override
    public void afficherContenu() {
        System.out.println("\n Valeurs de la colonne : ");
        for (int i = 0; i < this.tailleActuelle; i++) {
            System.out.println("Élément : " + (i+1) + " -- " + liste[i]);
        }
    }
}

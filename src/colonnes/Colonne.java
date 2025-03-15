package colonnes;
import java.util.ArrayList;

public class Colonne<V> implements InterfaceColonne<V> {

    private ArrayList<V> liste;

    // Constructeur
    public Colonne() {this.liste = new ArrayList<V>();}

    @Override
    public void ajouterValeur(V valeur) {
        this.liste.add(valeur);
    }

    @Override
    public V obtenirValeur(int index) throws Exception {
        if (index < 0 || index >= liste.size()) {
            throw new Exception("Index invalide : " + index);
        }
        return liste.get(index);
    }

    @Override
    public int obtenirIndex(V valeur) {
        return liste.indexOf(valeur);
    }

    @Override
    public void changerValeur(int index, V valeur) throws Exception {
        if (index < 0 || index >= liste.size()) {
            throw new Exception("Index invalide : " + index);
        }
        liste.set(index, valeur);
    }

    @Override
    public int getNbElements() {
        return liste.size();
    }

    @Override
    public void afficherContenu() {
        for (V v : this.liste) {
            System.out.println(v);
        }
    }
}

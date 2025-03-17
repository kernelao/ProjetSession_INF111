package tables;

import colonnes.ColonneIndexee;
import colonnes.Colonne;
import modeles.Utilisateur;

public class TableUtilisateurs {

    // Colonnes de la table
    private ColonneIndexee<String> nomsUtilisateurs;
    private ColonneIndexee<String> numerosComptes;
    private Colonne<Double> soldes;
    private Colonne<byte[]> salts;
    private Colonne<byte[]> hashesMotsDePasse;

    // Constructeur
    public TableUtilisateurs() {
        this.nomsUtilisateurs = new ColonneIndexee<>();
        this.numerosComptes = new ColonneIndexee<>();
        this.soldes = new Colonne<>();
        this.salts = new Colonne<>();
        this.hashesMotsDePasse = new Colonne<>();
    }

    // Ajouter un utilisateur si unique
    public boolean ajouterUnUtilisateur(Utilisateur utilisateur) {
        if (!nomsUtilisateurs.estUnique(utilisateur.getNomUtilisateur()) ||
                !numerosComptes.estUnique(utilisateur.getNumeroCompte())) {
            return false; // Nom ou numéro de compte déjà existant
        }

        nomsUtilisateurs.ajouterValeur(utilisateur.getNomUtilisateur());
        numerosComptes.ajouterValeur(utilisateur.getNumeroCompte());
        soldes.ajouterValeur(utilisateur.getSolde());
        salts.ajouterValeur(utilisateur.getSalt());
        hashesMotsDePasse.ajouterValeur(utilisateur.getSalt());

        return true;
    }

    // Récupérer un utilisateur par son nom
    public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur) {
        int index = nomsUtilisateurs.obtenirIndex(nomUtilisateur);
        if (index == -1) return null;

        try {
            return new Utilisateur(
                    nomsUtilisateurs.obtenirValeur(index),
                    hashesMotsDePasse.obtenirValeur(index),
                    salts.obtenirValeur(index),
                    numerosComptes.obtenirValeur(index),
                    soldes.obtenirValeur(index)
            );
        } catch (Exception e) {
            return null;
        }
    }

    // Récupérer un utilisateur par son numéro de compte
    public Utilisateur obtenirUtilisateurParCompte(String numeroCompte) {
        int index = numerosComptes.obtenirIndex(numeroCompte);
        if (index == -1) return null;

        try {
            return new Utilisateur(
                    nomsUtilisateurs.obtenirValeur(index),
                    hashesMotsDePasse.obtenirValeur(index),
                    salts.obtenirValeur(index),
                    numerosComptes.obtenirValeur(index),
                    soldes.obtenirValeur(index)
            );
        } catch (Exception e) {
            return null;
        }
    }

    // Mettre à jour le solde d'un utilisateur
    public void mettreAJourSoldeUtilisateur(String numeroCompte, double nouveauSolde) {
        int index = numerosComptes.obtenirIndex(numeroCompte);
        if (index != -1) {
            try {
                soldes.changerValeur(index, nouveauSolde);
            } catch (Exception e) {
                System.out.println("Erreur lors de la mise à jour du solde.");
            }
        }
    }
}

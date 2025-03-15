package modeles;

// Importation des méthodes salt/hash qui seront utilisée pour instancier l'objet et authentifier l'utilisateur
import utils.UtilitairesDB;

import java.util.Arrays;

public class Utilisateur {

    // Attributs à stocker dans la base de donnée
    private String nomUtilisateur;
    private String numeroCompte;
    private double solde;
    private byte[] salt;
    private byte[] hashMotDePasse;

    // Constructeurs
    public Utilisateur(String nomUtilisateur,
                       String motDePasse,
                       String numeroCompte,
                       double solde) {

        this.nomUtilisateur = nomUtilisateur;
        this.numeroCompte = numeroCompte;
        this.solde = solde;

        this.salt = UtilitairesDB.obtenirSalt();
        this.hashMotDePasse = UtilitairesDB.hashMotDePasse(motDePasse, this.salt);
    }

    public Utilisateur(String nomUtilisateur,
                       byte[] hashMotDePasse,
                       byte[] salt,
                       String numeroCompte,
                       double solde) {

        this.nomUtilisateur = nomUtilisateur;
        this.hashMotDePasse = hashMotDePasse;
        this.salt = salt;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
    }

    // Accesseurs informateur
    public String getNomUtilisateur() {return this.nomUtilisateur;}
    public String getNumeroCompte() {return this.numeroCompte;}
    public double getSolde() {return this.solde;}
    public byte[] getSalt() {return this.salt;}

    // Méthode toString() redéfinit
    @Override
    public String toString() {
        return  "Utilisateur : " + this.nomUtilisateur + "\n" +
                "Numéro de compte : " + this.getNumeroCompte() + "\n" +
                "Solde : " + this.getSolde() + "\n";
    }

    // Méthode qui ajoute (données négative qui est recu?) le différentiel au solde
    public void transactionSurSolde(double differentiel) {
        this.solde += differentiel;
    }

    // Méthode d'authentification
    // Va chercher
    public boolean authentifier(String nomUtilisateur, String motDePasse) {

        if ( !(this.nomUtilisateur.equals(nomUtilisateur)) ) {
            return false;
        }

        byte[] verification = UtilitairesDB.hashMotDePasse(motDePasse, this.salt);
        return Arrays.equals(this.hashMotDePasse, verification);

    }



}

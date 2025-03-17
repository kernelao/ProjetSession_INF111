package database;

import tables.TableUtilisateurs;
import tables.TableTransactions;
import modeles.Utilisateur;
import modeles.Transaction;
import java.util.List;

public class BaseDonnees {
    private TableUtilisateurs tableUtilisateurs;
    private TableTransactions tableTransactions;

    // Constructeur
    public BaseDonnees() {
        this.tableUtilisateurs = new TableUtilisateurs();
        this.tableTransactions = new TableTransactions();
    }

    // Vider la base de données
    public void viderLaBd() {
        this.tableUtilisateurs = new TableUtilisateurs();
        this.tableTransactions = new TableTransactions();
    }

    // Ajouter un utilisateur
    public boolean ajouterUtilisateur(Utilisateur nouvelUtilisateur) {
        return tableUtilisateurs.ajouterUnUtilisateur(nouvelUtilisateur);
    }

    // Obtenir un utilisateur par nom
    public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur) {
        return tableUtilisateurs.obtenirUtilisateurParNom(nomUtilisateur);
    }

    // Obtenir un utilisateur par numéro de compte
    public Utilisateur obtenirUtilisateurPourCompte(String numeroDeCompte) {
        return tableUtilisateurs.obtenirUtilisateurParCompte(numeroDeCompte);
    }

    // Mettre à jour le solde d’un utilisateur
    public void mettreAJourSoldeUtilisateur(String numeroDeCompte, double solde) {
        tableUtilisateurs.mettreAJourSoldeUtilisateur(numeroDeCompte, solde);
    }

    // Ajouter une transaction
    public void ajouterTransaction(Transaction transaction) {
        tableTransactions.ajouterUneTransaction(transaction);
    }

    // Obtenir les transactions associées à un compte
    public List<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
        return tableTransactions.obtenirTransactionsPourCompte(numeroDeCompte);
    }
}

package tables;

import colonnes.Colonne;
import modeles.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TableTransactions {

    // Colonnes des transactions
    private Colonne<String> comptesSources;
    private Colonne<String> comptesDestinations;
    private Colonne<Double> montants;
    private Colonne<String> statuts;

    // Constructeur
    public TableTransactions() {
        this.comptesSources = new Colonne<>();
        this.comptesDestinations = new Colonne<>();
        this.montants = new Colonne<>();
        this.statuts = new Colonne<>();
    }

    // Ajouter une transaction
    public void ajouterUneTransaction(Transaction transaction) {
        comptesSources.ajouterValeur(transaction.getNoCompteSource());
        comptesDestinations.ajouterValeur(transaction.getNoCompteDestination());
        montants.ajouterValeur(transaction.getMontant());
        statuts.ajouterValeur(transaction.getStatut());
    }

    // Récupérer toutes les transactions associées à un compte
    public List<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
        List<Transaction> transactionsAssociees = new ArrayList<>();

        for (int i = 0; i < comptesSources.getNbElements(); i++) {
            try {
                String source = comptesSources.obtenirValeur(i);
                String destination = comptesDestinations.obtenirValeur(i);
                double montant = montants.obtenirValeur(i);
                String statut = statuts.obtenirValeur(i);

                if (source.equals(numeroDeCompte) || destination.equals(numeroDeCompte)) {
                    transactionsAssociees.add(new Transaction(source, destination, montant, statut));
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la récupération des transactions.");
            }
        }

        return transactionsAssociees;
    }
}

package baseDonnees.tables;

import java.util.Vector;

import baseDonnees.bases.Colonne;
import baseDonnees.modeles.Transaction;

/**
 * Repr�sente une table qui retient les transactions bancaires entre un compte 
 * source et un compte destination.
 * 
 * @author Fr�d�ric simard | ETS
 * @version A2020
 * @revision et commentaire Pierre B�lisle
 *
 */
public class TableTransactions{

	// Les colonnes de la table.  
	private Colonne<String> noCompteSource;
	private Colonne<String> noCompteDestination;
	private Colonne<Double> montants;
	private Colonne<String> status;
	
	/**
	 * Constructeur par d�faut qui cr�e des colonnes vides.
	 */
	public TableTransactions() {
		
		/*
		 * Strat�gie : C'est ici qu'on fait l'instanciation explicite 
		 * des colonnes.
		 */
		noCompteSource = new Colonne<String>();
		noCompteDestination = new Colonne<String>();
		montants = new Colonne<Double>();
		status = new Colonne<String>();
	}
	
	/**
	 * Ajoute les infos de la transaction dans chacune de leur colonne respective.
	 * 
	 * @param transaction ajouter une transaction � la table
	 */
	public void ajouterUneTransaction(Transaction transaction) {
		
		this.noCompteSource.ajouterValeur(transaction.getNoCompteSource());
		this.noCompteDestination.ajouterValeur(transaction.getNoCompteDestination());
		this.montants.ajouterValeur(transaction.getMontant());
		this.status.ajouterValeur(transaction.getStatus());
		
	}

	/**
	 * Retourne une collection Vector content toutes les transactions pour<
	 * un num�ro de compte.
	 * 
	 * @param numeroDeCompte num�ro du compte pour lequel on veut les transactions
	 * @return vecteur des transactions pour le compte, vide si aucune
	 */
	public Vector<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
		
		Vector<Transaction> vecteurDeTransaction = new Vector<Transaction>();
		
		try {
			for(int i=0;i<noCompteSource.getNbElements();i++) {
				
				// V�rification du compte soit en source soit en destination.
				if(noCompteSource.obtenirValeur(i).equals(numeroDeCompte) ||
				   noCompteDestination.obtenirValeur(i).equals(numeroDeCompte)) {
					
					// ajoute la transaction au vecteur
					vecteurDeTransaction.add(new Transaction(
							noCompteSource.obtenirValeur(i),
							noCompteDestination.obtenirValeur(i),
							montants.obtenirValeur(i),
							status.obtenirValeur(i)));
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return vecteurDeTransaction;
	}
}

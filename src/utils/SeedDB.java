package baseDonnees.utils;

/**
 * Cette m�thode offre le service de remplissage d'une base de donn�es.
 * Elle ajoute des utilisateurs et des transactions.
 * 
 * Services publiques:
 * - seed, remplir une base de donn�es
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2025
 * @ref https://www.baeldung.com/java-password-hashing
 */
public class SeedDB {

	/**
	 * m�thode pour remplir une base de donn�es
	 * @param bd la base de donn�e � remplir
	 */
	/*
	public static void seed(BaseDonnees bd) {
		ajouterLesUtilisateurs(bd);
		ajouterLesTransactions(bd);
	}*/
	

	/**
	 * m�thode pour remplir les utilisateurs
	 * @param bd la base de donn�e � remplir
	 */
	/*
	private static void ajouterLesUtilisateurs(BaseDonnees bd) {
		
		Object[][] utilisateurs = {
				{"Fred Simard","fred12", "00-150-001", new Double(1500.0)},
				{"Pierre Belisle","pier12", "00-150-002", new Double(1500.0)},
				{"Jimmy Parent","jim12", "00-150-003", new Double(1500.0)},
				{"Claudia Marcy","clau12", "00-150-004", new Double(1500.0)},
				{"Lily Jacques","lil12", "00-150-005", new Double(1500.0)}
				};
		
		for(Object[] utilisateur : utilisateurs) {
			bd.ajouterUtilisateur(new Utilisateur((String)utilisateur[0], 
												  (String)utilisateur[1],
												  (String)utilisateur[2],
												  (double)utilisateur[3]));
		}
		
	}*/
	

	/**
	 * m�thode pour remplir les transactions
	 * @param bd la base de donn�e � remplir
	 */
	/*
	private static  void ajouterLesTransactions(BaseDonnees bd) {

		Object[][] transactions = {
				{"00-150-001","00-150-002", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-003","00-150-002", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-005","00-150-002", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-001","00-150-004", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-003","00-150-004", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-005","00-150-004", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-003","00-150-001", new Double(150.0),Transaction.ACCEPTE},
				{"00-150-005","00-150-001", new Double(150.0),Transaction.ACCEPTE}
				};
		
		for(Object[] transaction : transactions) {
			bd.ajouterTransaction(new Transaction((String)transaction[0],
												  (String)transaction[1],
												  (double)transaction[2],
												  (String)transaction[3]));
		}
	}
	*/
}

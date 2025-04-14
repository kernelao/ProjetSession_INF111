package vue;

import controleur.ControleurConnexion;
import controleur.ControleurCompte;
import vue.compte.CadreCompte;
import vue.connexion.CadreConnexion;

/**
 * Gestionnaire de la vue. Initialise les cadres et détermine quel cadre est actif.
 *
 * @author Frédéric Simard | ETS
 * @version H2025
 * @revision et commentaire Pierre Bélisle
 */
public class GestionnaireVue implements Runnable {

	private static GestionnaireVue instance;

	public static GestionnaireVue getInstance() {
		if (instance == null) {
			instance = new GestionnaireVue();
		}
		return instance;
	}

	CadreConnexion cadreConnexion;
	CadreCompte cadreCompte;

	@Override
	public void run() {
		// Crée les vues
		cadreConnexion = new CadreConnexion(this);
		cadreCompte = new CadreCompte(this);

		// Branche les contrôleurs
		new ControleurConnexion(cadreConnexion);
		new ControleurCompte(cadreCompte); // 👈 branchement du contrôleur manquant

		// Lance l'interface avec la vue de connexion visible
		activerModeConnexion();
	}

	/**
	 * Active la vue de connexion
	 */
	public void activerModeConnexion() {
		cadreCompte.setVisible(false);
		cadreConnexion.setVisible(true);
	}

	/**
	 * Active la vue du compte utilisateur
	 */
	public void activerModeCompte() {
		cadreConnexion.setVisible(false);
		cadreCompte.majInfos(); // Mise à jour des infos au moment de l'ouverture
		cadreCompte.setVisible(true);
	}

	/**
	 * Fournit un accès à la vue compte pour mise à jour
	 */
	public CadreCompte getCadreCompte() {
		return cadreCompte;
	}
}

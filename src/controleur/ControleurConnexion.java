package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Banque;
import vue.connexion.CadreConnexion;
import vue.GestionnaireVue;

public class ControleurConnexion implements ActionListener {

    private CadreConnexion vue;

    public ControleurConnexion(CadreConnexion vue) {
        this.vue = vue;
        this.vue.getBoutonConnexion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = vue.getNomUtilisateur();
        String motDePasse = vue.getMotDePasse();

        if (Banque.getInstance().verifier(nom, motDePasse)) {
            Banque.getInstance().setUtilisateurActif(nom);
            vue.viderChamps();
            vue.afficherErreur(false);
            GestionnaireVue.getInstance().activerModeCompte();
        } else {
            vue.afficherErreur(true);
        }
    }
}

package controleur;

import vue.compte.CadreCompte;
import modele.Banque;
import vue.GestionnaireVue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurCompte implements ActionListener {

    private CadreCompte vue;

    public ControleurCompte(CadreCompte vue) {
        this.vue = vue;

        // Brancher les boutons
        this.vue.getBoutonTransaction().addActionListener(this);
        this.vue.getBoutonDeconnexion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vue.getBoutonTransaction()) {
            vue.afficherTransactionDialog();
        }

        else if (source == vue.getBoutonDeconnexion()) {
            Banque.getInstance().deconnecterUtilisateur();
            GestionnaireVue.getInstance().activerModeConnexion();
        }
    }
}

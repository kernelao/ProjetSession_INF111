package controleur;

import modele.Banque;
import baseDonnees.modeles.Transaction;
import vue.compte.DialogTransaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurTransaction implements ActionListener {

    private DialogTransaction vue;

    public ControleurTransaction(DialogTransaction vue) {
        this.vue = vue;

        // Branche les boutons
        this.vue.getBoutonConfirmer().addActionListener(this);
        this.vue.getBoutonAnnuler().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vue.getBoutonAnnuler()) {
            vue.dispose(); // ferme la fenêtre
        }
        else if (source == vue.getBoutonConfirmer()) {
            String compteDest = vue.getCompteDestination().trim();
            String montantTexte = vue.getMontant().trim();

            try {
                double montant = Double.parseDouble(montantTexte);
                String compteSource = Banque.getInstance().getUtilisateurActif().getNumeroDeCompte();

                Transaction transaction = new Transaction(compteSource, compteDest, montant);
                Banque.getInstance().soumettreTransaction(transaction);

                String message = transaction.getStatus().equals(Transaction.ACCEPTE)
                        ? "Transaction effectuée avec succès !"
                        : "Transaction refusée.";

                JOptionPane.showMessageDialog(vue, message);
                vue.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vue,
                        "Montant invalide. Veuillez entrer un nombre.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

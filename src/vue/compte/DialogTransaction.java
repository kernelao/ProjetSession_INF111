package vue.compte;

import javax.swing.*;
import java.awt.*;

public class DialogTransaction extends JDialog {

    private JTextField champCompteDestination;
    private JTextField champMontant;
    private JButton boutonConfirmer;
    private JButton boutonAnnuler;

    public DialogTransaction(JFrame parent) {
        super(parent, "Nouvelle transaction", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ====== PANEL PRINCIPAL ======
        Color grisPale = new Color(240, 240, 240);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(grisPale);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ====== Label Compte (2 lignes) ======
        JPanel panelLabelCompte = new JPanel(new GridLayout(2, 1));
        panelLabelCompte.setBackground(grisPale);
        panelLabelCompte.add(new JLabel("Compte destination"));
        panelLabelCompte.add(new JLabel("(00-150-00X):"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelLabelCompte, gbc);

        // ====== Champ Compte ======
        champCompteDestination = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(champCompteDestination, gbc);

        // ====== Label Montant ======
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Montant:"), gbc);

        // ====== Champ Montant ======
        champMontant = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(champMontant, gbc);

        // ====== Boutons ======
        JPanel panelBoutons = new JPanel();
        boutonAnnuler = new JButton("Annuler");
        boutonConfirmer = new JButton("Confirmer");

        panelBoutons.add(boutonAnnuler);
        panelBoutons.add(boutonConfirmer);

        add(panel, BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    // ====== Méthodes pour le contrôleur ======

    public JButton getBoutonConfirmer() {
        return boutonConfirmer;
    }

    public JButton getBoutonAnnuler() {
        return boutonAnnuler;
    }

    public String getCompteDestination() {
        return champCompteDestination.getText();
    }

    public String getMontant() {
        return champMontant.getText();
    }
}

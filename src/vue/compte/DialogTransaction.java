package vue.compte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Banque;

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

        // panneau principal
        Color grisPale = new Color(240, 240, 240);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(grisPale);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // label Compte (2 lignes)
        JPanel panelLabelCompte = new JPanel(new GridLayout(2, 1));
        panelLabelCompte.setBackground(grisPale);
        panelLabelCompte.add(new JLabel("Compte destination"));
        panelLabelCompte.add(new JLabel("(00-150-00X):"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelLabelCompte, gbc);

        // champ Compte
        champCompteDestination = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(champCompteDestination, gbc);

        // label Montant
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Montant:"), gbc);

        // champ Montant
        champMontant = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(champMontant, gbc);

        // boutons
        JPanel panelBoutons = new JPanel();
        boutonAnnuler = new JButton("Annuler");
        boutonConfirmer = new JButton("Confirmer");

        panelBoutons.add(boutonAnnuler);
        panelBoutons.add(boutonConfirmer);

        add(panel, BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.SOUTH);

        // événements
        boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ferme la fenêtre
            }
        });

        boutonConfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String compte = champCompteDestination.getText().trim();
                String montantTexte = champMontant.getText().trim();

                try {
                    double montant = Double.parseDouble(montantTexte);
                    boolean succes = Banque.getInstance().traiterTransaction(compte, montant);

                    if (succes) {
                        JOptionPane.showMessageDialog(DialogTransaction.this,
                                "Transaction effectuée avec succès !");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(DialogTransaction.this,
                                "Transaction refusée. Vérifiez les informations.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DialogTransaction.this,
                            "Montant invalide. Veuillez entrer un nombre.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public String getCompteDestination() {
        return champCompteDestination.getText();
    }

    public String getMontant() {
        return champMontant.getText();
    }
}
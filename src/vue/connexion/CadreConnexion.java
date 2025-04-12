package vue.connexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Banque;
import vue.GestionnaireVue;

/**
 * Fenêtre de connexion initiale du programme.
 */
public class CadreConnexion extends JFrame {

    private JTextField champUtilisateur;
    private JPasswordField champMotDePasse;
    private JLabel labelErreur;

    private GestionnaireVue gestionnaire;

    public CadreConnexion(GestionnaireVue gestionnaire) {
        this.gestionnaire = gestionnaire;

        setTitle("Connexion");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        initComposants();
    }

    private void initComposants() {
        JLabel labelUtilisateur = new JLabel("Nom utilisateur");
        labelUtilisateur.setForeground(Color.WHITE);

        champUtilisateur = new JTextField();
        champUtilisateur.setPreferredSize(new Dimension(300, 25));
        JPanel panelUtilisateur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelUtilisateur.setOpaque(false); // transparent
        panelUtilisateur.add(champUtilisateur);

        JLabel labelMotDePasse = new JLabel("Mot de passe");
        labelMotDePasse.setForeground(Color.WHITE);

        champMotDePasse = new JPasswordField();
        champMotDePasse.setPreferredSize(new Dimension(300, 25));
        JPanel panelMotDePasse = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelMotDePasse.setOpaque(false);
        panelMotDePasse.add(champMotDePasse);

        JButton boutonConnexion = new JButton("Soumettre");

        labelErreur = new JLabel("Accès refusé");
        labelErreur.setForeground(Color.RED);
        labelErreur.setVisible(false);
        labelErreur.setHorizontalAlignment(SwingConstants.CENTER);

        boutonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierIdentifiants();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(labelUtilisateur)
                        .addComponent(panelUtilisateur)
                        .addComponent(labelMotDePasse)
                        .addComponent(panelMotDePasse)
                        .addComponent(boutonConnexion)
                        .addComponent(labelErreur)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(labelUtilisateur)
                        .addComponent(panelUtilisateur)
                        .addComponent(labelMotDePasse)
                        .addComponent(panelMotDePasse)
                        .addComponent(boutonConnexion)
                        .addComponent(labelErreur)
        );
    }

    private void verifierIdentifiants() {
        String nomUtilisateur = champUtilisateur.getText();
        String motDePasse = new String(champMotDePasse.getPassword());

        if (Banque.getInstance().verifier(nomUtilisateur, motDePasse)) {
            Banque.getInstance().setUtilisateurActif(nomUtilisateur);
            gestionnaire.activerModeCompte();
            champUtilisateur.setText("");
            champMotDePasse.setText("");
            labelErreur.setVisible(false);
        } else {
            labelErreur.setVisible(true);
        }
    }
}

package vue.connexion;

import javax.swing.*;
import java.awt.*;
import vue.GestionnaireVue;

public class CadreConnexion extends JFrame {

    private JTextField champUtilisateur;
    private JPasswordField champMotDePasse;
    private JLabel labelErreur;
    private JButton boutonConnexion;

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
        champUtilisateur.setPreferredSize(new Dimension(250, 25));
        JPanel panelUtilisateur = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelUtilisateur.setOpaque(false);
        panelUtilisateur.add(champUtilisateur);

        JLabel labelMotDePasse = new JLabel("Mot de passe");
        labelMotDePasse.setForeground(Color.WHITE);

        champMotDePasse = new JPasswordField();
        champMotDePasse.setPreferredSize(new Dimension(250, 25));
        JPanel panelMotDePasse = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelMotDePasse.setOpaque(false);
        panelMotDePasse.add(champMotDePasse);

        boutonConnexion = new JButton("Soumettre");

        labelErreur = new JLabel("acces refuse");
        labelErreur.setForeground(Color.RED);
        labelErreur.setVisible(false);
        labelErreur.setHorizontalAlignment(SwingConstants.CENTER);

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

    // ==== MÉTHODES POUR LE CONTRÔLEUR ====

    public String getNomUtilisateur() {
        return champUtilisateur.getText();
    }

    public String getMotDePasse() {
        return new String(champMotDePasse.getPassword());
    }

    public JButton getBoutonConnexion() {
        return boutonConnexion;
    }

    public void afficherErreur(boolean visible) {
        labelErreur.setVisible(visible);
    }

    public void viderChamps() {
        champUtilisateur.setText("");
        champMotDePasse.setText("");
    }
}

package vue.compte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

import baseDonnees.modeles.Utilisateur;
import baseDonnees.modeles.Transaction;
import modele.Banque;
import vue.GestionnaireVue;

public class CadreCompte extends JFrame {

    private GestionnaireVue gestionnaire;

    private JLabel nomLabel;
    private JLabel numeroLabel;
    private JLabel soldeLabel;

    private JTable tableauTransactions;
    private DefaultTableModel modeleTableau;

    private JButton boutonTransaction;
    private JButton boutonDeconnexion;

    public CadreCompte(GestionnaireVue gestionnaire) {
        this.gestionnaire = gestionnaire;

        setTitle("Compte Bancaire");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        Color vertPale = new Color(121, 176, 136);
        Color vertFonce = new Color(42, 71, 50);
        Color grisClair = new Color(220, 220, 220);
        Color bordureNoire = Color.BLACK;

        JPanel panneauGauche = new JPanel(new GridLayout(3, 1));
        panneauGauche.setPreferredSize(new Dimension(250, 0));
        panneauGauche.setBackground(vertPale);

        // Panneau logo
        JPanel panneauLogo = new JPanel(new GridBagLayout());
        panneauLogo.setBackground(vertPale);
        panneauLogo.setBorder(BorderFactory.createLineBorder(bordureNoire));
        ImageIcon logo = new ImageIcon("image/dollar.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        panneauLogo.add(logoLabel);

        // Panneau boutons
        JPanel panneauBoutons = new JPanel(new GridBagLayout());
        panneauBoutons.setBackground(vertPale);
        panneauBoutons.setBorder(BorderFactory.createLineBorder(bordureNoire));

        boutonTransaction = new JButton("Nouvelle transaction");
        boutonTransaction.setPreferredSize(new Dimension(140, 30));
        boutonTransaction.setBackground(grisClair);

        boutonDeconnexion = new JButton("Se deconnecter");
        boutonDeconnexion.setPreferredSize(new Dimension(140, 30));
        boutonDeconnexion.setBackground(grisClair);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 0;
        panneauBoutons.add(boutonTransaction, gbc);
        gbc.gridy = 1;
        panneauBoutons.add(boutonDeconnexion, gbc);

        // Panneau vide
        JPanel panneauVide = new JPanel();
        panneauVide.setBackground(vertPale);
        panneauVide.setBorder(BorderFactory.createLineBorder(bordureNoire));

        panneauGauche.add(panneauLogo);
        panneauGauche.add(panneauBoutons);
        panneauGauche.add(panneauVide);

        // Panneau droit
        JPanel panneauDroit = new JPanel(new BorderLayout());

        JPanel panneauInfos = new JPanel(new GridLayout(3, 1));
        panneauInfos.setBackground(vertPale);
        panneauInfos.setPreferredSize(new Dimension(0, 233));

        nomLabel = new JLabel();
        numeroLabel = new JLabel();
        soldeLabel = new JLabel();

        nomLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
        numeroLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        soldeLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));

        panneauInfos.add(nomLabel);
        panneauInfos.add(numeroLabel);
        panneauInfos.add(soldeLabel);

        JPanel panneauTableau = new JPanel(new BorderLayout());
        panneauTableau.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        String[] colonnes = {"Source", "Destination", "Montant", "Statut"};
        modeleTableau = new DefaultTableModel(colonnes, 0);
        tableauTransactions = new JTable(modeleTableau);
        tableauTransactions.getTableHeader().setBackground(vertFonce);
        tableauTransactions.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tableauTransactions);
        panneauTableau.add(scrollPane, BorderLayout.CENTER);

        panneauDroit.add(panneauInfos, BorderLayout.NORTH);
        panneauDroit.add(panneauTableau, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panneauGauche, panneauDroit);
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(3);
        add(splitPane);
    }

    // === Méthode appelée par le contrôleur pour afficher la transaction ===
    public void afficherTransactionDialog() {
        DialogTransaction dialog = new DialogTransaction(this);
        new controleur.ControleurTransaction(dialog);
        dialog.setVisible(true);
        majInfos();
    }

    // === Getters pour les contrôleurs ===
    public JButton getBoutonTransaction() {
        return boutonTransaction;
    }

    public JButton getBoutonDeconnexion() {
        return boutonDeconnexion;
    }

    public GestionnaireVue getGestionnaire() {
        return gestionnaire;
    }

    public void majInfos() {
        Utilisateur utilisateur = Banque.getInstance().getUtilisateurActif();
        if (utilisateur == null) return;

        nomLabel.setText("Nom : " + utilisateur.getNomUtilisateur());
        numeroLabel.setText("Numero de compte : " + utilisateur.getNumeroDeCompte());
        soldeLabel.setText("Solde : " + utilisateur.getSolde() + " $");

        Vector<Transaction> transactions = Banque.getInstance().obtenirTransactionsPourCompte();
        modeleTableau.setRowCount(0);
        for (Transaction t : transactions) {
            Object[] ligne = {
                    t.getNoCompteSource(),
                    t.getNoCompteDestination(),
                    t.getMontant(),
                    t.getStatus().equals(Transaction.ACCEPTE) ? "Acceptee" : "Refusee"
            };
            modeleTableau.addRow(ligne);
        }
    }
}

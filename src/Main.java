import modeles.Transaction;
import modeles.Utilisateur;
import utils.UtilitairesDB;
import colonnes.Colonne;

import java.util.ArrayList;
import java.util.Vector;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception {

        // testerUtilisateur();
        // testerTransaction();
        // testerColonne();

    }

    public static void testerUtilisateur() {
        Vector<Utilisateur> utilisateurs = new Vector<>();

        utilisateurs.add(new Utilisateur("Bob", "Bonjour", "001", 0));

        String motDePasse = "Bille";
        byte[] saltMotDePasse = UtilitairesDB.obtenirSalt();
        byte[] hashMotDePasse = UtilitairesDB.hashMotDePasse(motDePasse, saltMotDePasse);

        utilisateurs.add(new Utilisateur("Carl", hashMotDePasse, saltMotDePasse, "002", 200));
        utilisateurs.add(new Utilisateur("Jean", "Allo1234", "003", 2000));

        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }

        System.out.println(utilisateurs.get(0).authentifier("Bob", "Bonjourr"));
        System.out.println(utilisateurs.get(1).authentifier("Carl", "Billee"));
        System.out.println(utilisateurs.get(2).authentifier("Jean", "Allo12344"));

        ListIterator<Utilisateur> iter = utilisateurs.listIterator();
        while (iter.hasNext()) {
            Utilisateur user = iter.next();
            System.out.println(user.getNomUtilisateur());
            System.out.println(user.getNumeroCompte());
            System.out.println(user.getSalt());
            System.out.println(user.getSolde() + "\n");
        }
    }

    public static void testerTransaction() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("001", "002", 500, Transaction.REFUSE));
        transactions.add(new Transaction("002", "001", 500));

        for (Transaction transac : transactions) {
            System.out.println(transac.getNoCompteSource());
            System.out.println(transac.getNoCompteDestination());
            System.out.println(transac.getMontant());
            System.out.println(transac.getStatut() + "\n");
        }
    }

    public static void testerColonne() throws Exception {

        Colonne<Utilisateur> colonneUtilisateur = new Colonne<Utilisateur>();

        Utilisateur user1 = new Utilisateur("Bob", "Bille", "001", 0);
        Utilisateur user2 = new Utilisateur("Carl", "Bille", "002", 0);
        Utilisateur user3 = new Utilisateur("Jane", "Las", "003", 40);

        colonneUtilisateur.ajouterValeur(user1);
        colonneUtilisateur.ajouterValeur(user2);
        colonneUtilisateur.afficherContenu();

        System.out.println(colonneUtilisateur.obtenirValeur(1));
        System.out.println(colonneUtilisateur.obtenirValeur(0));
        System.out.println(colonneUtilisateur.obtenirIndex(user2));
        System.out.println(colonneUtilisateur.obtenirIndex(user1) + "\n");

        System.out.println(colonneUtilisateur.getNbElements() + "\n");

        colonneUtilisateur.changerValeur(1, user3);
        colonneUtilisateur.afficherContenu();


    }
}
import modeles.Transaction;
import modeles.Utilisateur;
import utils.UtilitairesDB;
import draft.ColonneArrayList;

import java.util.ArrayList;
import java.util.Vector;
import java.util.ListIterator;
import colonnes.Colonne;


public class Main {
    public static void main(String[] args) throws Exception {

        // testerUtilisateur();
        // testerTransaction();
        // testerColonneArrayList();
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

    public static void testerColonneArrayList() throws Exception {

        ColonneArrayList<Utilisateur> colonneArrayListUtilisateur = new ColonneArrayList<Utilisateur>();

        Utilisateur user1 = new Utilisateur("Bob", "Bille", "001", 0);
        Utilisateur user2 = new Utilisateur("Carl", "Bille", "002", 0);
        Utilisateur user3 = new Utilisateur("Jane", "Las", "003", 40);

        colonneArrayListUtilisateur.ajouterValeur(user1);
        colonneArrayListUtilisateur.ajouterValeur(user2);
        colonneArrayListUtilisateur.afficherContenu();

        System.out.println(colonneArrayListUtilisateur.obtenirValeur(1));
        System.out.println(colonneArrayListUtilisateur.obtenirValeur(0));
        System.out.println(colonneArrayListUtilisateur.obtenirIndex(user2));
        System.out.println(colonneArrayListUtilisateur.obtenirIndex(user1) + "\n");

        System.out.println(colonneArrayListUtilisateur.getNbElements() + "\n");

        colonneArrayListUtilisateur.changerValeur(1, user3);
        colonneArrayListUtilisateur.afficherContenu();


    }

    public static void testerColonne() throws Exception {
        // Les deux classes sont presque pareil, pour respecter les contraintes, on utilise une implémentation statique sans chainage (avec tableau 1D)
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

        System.out.println(colonneUtilisateur.getNbElements());
        System.out.println(colonneUtilisateur.getCapaciteActuelle());
        System.out.println((colonneUtilisateur.getCapaciteActuelle() - colonneUtilisateur.getNbElements()));
        int rajoute = colonneUtilisateur.getCapaciteActuelle() - colonneUtilisateur.getNbElements();

        // Taille de 2 et capacité de 15, rajoutons encore 13 utilisateur
        for (int i = 0; i < (rajoute); i++) {
            colonneUtilisateur.ajouterValeur(new Utilisateur("Bob", "Bille", "001", 0));
            System.out.println("Itération : " + i + " / " + colonneUtilisateur.getNbElements() + " / " + colonneUtilisateur.getCapaciteActuelle());
        }
        System.out.println(colonneUtilisateur.getNbElements());
        System.out.println(colonneUtilisateur.getCapaciteActuelle());

        // Rajoutons 1 utilisateur en plus
        colonneUtilisateur.ajouterValeur(new Utilisateur("Bob", "Bille", "001", 0));
        System.out.println(colonneUtilisateur.getNbElements());
        System.out.println(colonneUtilisateur.getCapaciteActuelle());
        // Sa fonctionne!
    }
}


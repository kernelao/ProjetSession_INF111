import colonnes.ColonneIndexee;
import modeles.Transaction;
import modeles.Utilisateur;
import tables.TableTransactions;
import tables.TableUtilisateurs;
import utils.UtilitairesDB;
import draft.ColonneArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.ListIterator;
import colonnes.Colonne;
import database.BaseDonnees;

public class Main {
    public static void main(String[] args) throws Exception {

        // testerUtilisateur();
        // testerTransaction();
        // testerColonneArrayList();
        // testerColonne();
        testerColonneIndexee();
        //testerTableUtilisateurs();
        //testerTableTransactions();
        //testerPerformanceColonnes();
        //testerBaseDonnees();
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

    public static void testerColonneIndexee() throws Exception {
        // Utilisation de l'enveloppeur/wrapper String, puisqu'il
        // faudrait implémenter Comparable pour la classe Utilisateur/Transaction pour permettre le tri
        ColonneIndexee<String> colonne1 = new ColonneIndexee<String>(8);
        ColonneIndexee<String> colonne2 = new ColonneIndexee<String>();

        colonne1.ajouterValeur("Dragon");
        colonne1.ajouterValeur("Euro");
        colonne1.ajouterValeur("Belier");
        colonne1.ajouterValeur("Arold");
        colonne1.ajouterValeur("Euro");
        colonne1.ajouterValeur("Gerald");
        colonne1.ajouterValeur("File");

        colonne1.afficherContenu();
        System.out.println("Nombre d'éléments : " + colonne1.getNbElements());
        System.out.println("Espace restant : " + (colonne1.getCapaciteActuelle() - colonne1.getNbElements()));

        // On rajoute 1 puis le deuxieme
        colonne1.ajouterValeur("Harold");
        System.out.println("Nombre d'éléments : " + colonne1.getNbElements());
        System.out.println("Espace restant : " + (colonne1.getCapaciteActuelle() - colonne1.getNbElements()));

        colonne1.ajouterValeur("John");
        System.out.println("Nombre d'éléments : " + colonne1.getNbElements());
        System.out.println("Capacité après ajout du 8ème (dépassement) : " + (colonne1.getCapaciteActuelle()));

        System.out.println("Index de \"Euro\" : " + colonne1.obtenirIndex("Euro") + " / Attendu : 2");
        System.out.println("Index de \"Belier\" : " + colonne1.obtenirIndex("Belier") + " / Attendu : 1");
        System.out.println("Index de \"Gerald\" : " + colonne1.obtenirIndex("Gerald") + " / Attendu : 6");
        System.out.println("Index de \"Arold\" : " + colonne1.obtenirIndex("Arold") + " / Attendu : 0");
        System.out.println("Index de \"Harold\" : " + colonne1.obtenirIndex("Harold") + " / Attendu : 7");

        // Fonctionne
        // colonne1.changerValeur(1, "Bob");

        System.out.println("estUnique de \"Euro\" : " + colonne1.estUnique("Euro") + " / Attendu : faux");
        System.out.println("estUnique de \"Belier\" : " + colonne1.estUnique("Belier") + " / Attendu : vrai");
        System.out.println("estUnique de \"Gerald\" : " + colonne1.estUnique("Gerald") + " / Attendu : vrai");

    }

    public static void testerPerformanceColonnes() {
        System.out.println("\n===== TEST PERFORMANCE Colonne vs ColonneIndexee =====");
        int nbElements = 10000;
        Colonne<Integer> colonneSimple = new Colonne<>();
        ColonneIndexee<Integer> colonneIndexee = new ColonneIndexee<>();

        // Remplissage des colonnes
        for (int i = 0; i < nbElements; i++) {
            int valeur = (int) (Math.random() * nbElements);
            colonneSimple.ajouterValeur(valeur);
            colonneIndexee.ajouterValeur(valeur);
        }

        // Test de recherche dans Colonne
        int valeurCherchee = (int) (Math.random() * nbElements);
        long debutRechercheSimple = System.nanoTime();
        colonneSimple.obtenirIndex(valeurCherchee);
        long finRechercheSimple = System.nanoTime();
        System.out.println("Temps de recherche dans Colonne : " + (finRechercheSimple - debutRechercheSimple) / 1e6 + " ms");

        // Test de recherche dans ColonneIndexee
        long debutRechercheIndexee = System.nanoTime();
        colonneIndexee.obtenirIndex(valeurCherchee);
        long finRechercheIndexee = System.nanoTime();
        System.out.println("Temps de recherche dans ColonneIndexee : " + (finRechercheIndexee - debutRechercheIndexee) / 1e6 + " ms");
    }

    public static void testerTableUtilisateurs() {
        System.out.println("\n===== TEST TableUtilisateurs =====");
        TableUtilisateurs tableUtilisateurs = new TableUtilisateurs();

        Utilisateur user1 = new Utilisateur("Alice", "password123", "001", 500.0);
        Utilisateur user2 = new Utilisateur("Bob", "securePass", "002", 1000.0);

        System.out.println("Ajout utilisateur 1: " + tableUtilisateurs.ajouterUnUtilisateur(user1));
        System.out.println("Ajout utilisateur 2: " + tableUtilisateurs.ajouterUnUtilisateur(user2));

        System.out.println("Recherche par nom (Alice): ");
        System.out.println(tableUtilisateurs.obtenirUtilisateurParNom("Alice"));

        System.out.println("Recherche par numéro de compte (002): ");
        System.out.println(tableUtilisateurs.obtenirUtilisateurParCompte("002"));

        System.out.println("Mise à jour du solde de Bob à 2000.0");
        tableUtilisateurs.mettreAJourSoldeUtilisateur("002", 2000.0);
        System.out.println(tableUtilisateurs.obtenirUtilisateurParCompte("002"));
    }


    public static void testerTableTransactions() {
        System.out.println("\n===== TEST TableTransactions =====");
        TableTransactions tableTransactions = new TableTransactions();

        Transaction t1 = new Transaction("001", "002", 150.0, Transaction.ACCEPTE);
        Transaction t2 = new Transaction("002", "001", 200.0);
        Transaction t3 = new Transaction("001", "003", 50.0, Transaction.REFUSE);

        tableTransactions.ajouterUneTransaction(t1);
        tableTransactions.ajouterUneTransaction(t2);
        tableTransactions.ajouterUneTransaction(t3);

        System.out.println("Transactions associées au compte 001:");
        for (Transaction t : tableTransactions.obtenirTransactionsPourCompte("001")) {
            System.out.println(t);
        }
    }
    public static void testerBaseDonnees() {
        System.out.println("\n===== TEST BaseDonnees =====");
        BaseDonnees baseDonnees = new BaseDonnees();

        // Ajout d'utilisateurs
        Utilisateur user1 = new Utilisateur("Alice", "password123", "001", 500.0);
        Utilisateur user2 = new Utilisateur("Bob", "securePass", "002", 1000.0);

        System.out.println("Ajout utilisateur 1: " + baseDonnees.ajouterUtilisateur(user1));
        System.out.println("Ajout utilisateur 2: " + baseDonnees.ajouterUtilisateur(user2));

        // Recherche d'utilisateurs
        System.out.println("Recherche par nom (Alice): ");
        System.out.println(baseDonnees.obtenirUtilisateurParNom("Alice"));

        System.out.println("Recherche par numéro de compte (002): ");
        System.out.println(baseDonnees.obtenirUtilisateurPourCompte("002"));

        // Mise à jour du solde
        System.out.println("Mise à jour du solde de Bob à 2000.0");
        baseDonnees.mettreAJourSoldeUtilisateur("002", 2000.0);
        System.out.println(baseDonnees.obtenirUtilisateurPourCompte("002"));

        // Ajout et récupération de transactions
        System.out.println("\nAjout de transactions...");
        baseDonnees.ajouterTransaction(new Transaction("001", "002", 150.0, Transaction.ACCEPTE));
        baseDonnees.ajouterTransaction(new Transaction("002", "001", 200.0));
        baseDonnees.ajouterTransaction(new Transaction("001", "003", 50.0, Transaction.REFUSE));

        System.out.println("Transactions associées au compte 001:");
        List<Transaction> transactions = baseDonnees.obtenirTransactionsPourCompte("001");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

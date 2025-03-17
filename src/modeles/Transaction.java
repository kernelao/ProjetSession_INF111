package modeles;

public class Transaction {

    // Constantes publiques
    public static final String ACCEPTE = "Accepté";
    public static final String REFUSE = "Refusé";
    public static final String A_DETERMINER = "À déterminer";

    // Attributs de classe
    private String noCompteSource;
    private String noCompteDestination;
    private double montant;
    private String statut;

    // Constructeurs
    public Transaction(String noCompteSource,
                       String noCompteDestination,
                       double montant,
                       String statut) {

        this.noCompteSource = noCompteSource;
        this.noCompteDestination = noCompteDestination;
        this.montant = montant;
        this.statut = statut;
    }

    public Transaction(String noCompteSource,
                       String noCompteDestination,
                       double montant) {

        this.noCompteSource = noCompteSource;
        this.noCompteDestination = noCompteDestination;
        this.montant = montant;
        this.statut = A_DETERMINER;
    }

    // Accesseurs informateur
    public String getNoCompteSource() {return this.noCompteSource;}
    public String getNoCompteDestination() {return this.noCompteDestination;}
    public double getMontant() {return this.montant;}
    public String getStatut() {return this.statut;}

    // Mutateur
    public void setStatut(String statut) {this.statut = statut;}

    @Override
    public String toString() {
        return "Transaction{" +
                "Compte Source='" + noCompteSource + '\'' +
                ", Compte Destination='" + noCompteDestination + '\'' +
                ", Montant=" + montant +
                ", Statut='" + statut + '\'' +
                '}';
    }

}

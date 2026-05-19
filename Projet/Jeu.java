import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    boolean tourdechauffe = true;
   

    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private int indexJoueurActuel;
    private Scanner scanner;
    private Random random;
    private Banque banque;

    public Jeu(Plateau plateau, ArrayList<Joueur> joueurs) {
        this.plateau = plateau;
        this.joueurs = joueurs;
        this.indexJoueurActuel = 0;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }
   

    // ============================================================
    // BOUCLE PRINCIPALE
    // ============================================================

    public void jouer() {
        
        System.out.println("\n=== Que la partie commence ! ===\n");
        while (!partieTerminee()) {
            Joueur joueurActuel = joueurs.get(indexJoueurActuel);
            if(!joueurActuel.enFaillite()){
                try {
                    Thread.sleep(0000); // Pause d'une seconde entre les tours pour une meilleure lisibilité
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                jouerTour(joueurActuel);
            }
            passerAuJoueurSuivant();
        }
        afficherGagnant();
    }

    // ============================================================
    // TOUR D'UN JOUEUR
    // ============================================================

    private void jouerTour(Joueur joueur) {
        Case caseActuelle = plateau.getCase(joueur.getPosition());
        System.out.println("\n--- Tour de " + joueur.getNom() + " ---");
        System.out.println("Position : " + caseActuelle.getNom() + (caseActuelle.getCouleur() != null && 
         !caseActuelle.getCouleur().equals("c") &&
          !caseActuelle.getCouleur().equals("g") ? " (" + caseActuelle.getCouleur() + ")" : "") 
          + " (case n°" + (joueur.getPosition()+1) + "/ 40)");
        System.out.println("Argent   : " + joueur.getArgent() + " ecus");

        // Si le joueur est en prison
        if (joueur.estEnPrison()) {
            gererPrison(joueur, plateau.getCase(joueur.getPosition()));
            return;
        }

        // Lancer les dés
        int tirage;
        if (tourdechauffe == true) {
            tirage = Des.tiragedesblancs();
        } else {
            tirage = Des.tiragedesrapide(joueur, plateau);
        }

        // Vérifier si le joueur passe par le Départ
        int nouvellePosition = (joueur.getPosition() + tirage) % 40;
        if (nouvellePosition < joueur.getPosition()) {
            tourdechauffe = false;
            joueur.ajouterArgent(200);
            System.out.println("Vous passez par la Comté (Départ) ! +200 ecus");
            System.out.println("Argent   : " + joueur.getArgent() + " ecus");
        }

        joueur.deplacer(tirage);
        caseActuelle = plateau.getCase(joueur.getPosition());
        System.out.println("Vous arrivez sur : " + caseActuelle.getNom() + 
        (caseActuelle.getCouleur() != null &&
         !caseActuelle.getCouleur().equals("c") &&
          !caseActuelle.getCouleur().equals("g") ? " (" + caseActuelle.getCouleur() + ")" : "") 
        + " (case n°" + (joueur.getPosition()+1) + "/ 40)");
        Banque.EffetCase(joueur, caseActuelle);
    }

    private void passerAuJoueurSuivant() {
        indexJoueurActuel = (indexJoueurActuel + 1) % joueurs.size();
    }

    private boolean partieTerminee() {
        int joueursSolvables = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getArgent() >= 0) joueursSolvables++;
        }
        return joueursSolvables <= 1;
    }

    private void afficherGagnant() {
        Joueur gagnant = joueurs.get(0);
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getArgent() > gagnant.getArgent()) gagnant = joueurs.get(i);
        }
        System.out.println("\n=== " + gagnant.getNom() + " remporte la partie avec " 
            + gagnant.getArgent() + " ecus ! ===");
        Leaderboard.enregistrerVictoire(gagnant.getNom(), gagnant.getArgent());
        System.out.println("Le leaderboard a été mis à jour avec succès !");
    }
    



private void gererPrison(Joueur joueur, Case CaseActuelle) {
    int nblancers = 0;
     if(nblancers >= 3){
        System.out.println("Vous avez tenté de sortir du Mordor 3 fois sans succès, vous devez payer 50 écus pour sortir.");
        boolean paiementReussi = Dette_Faillite.gererPaiement(joueur, 50, null);
        if (paiementReussi) {
            joueur.sortirDePrison();
            System.out.println("Vous sortez du Mordor !");
            jouerTour(joueur);
        } else {
            System.out.println("FAILLITE ! Vous ne pouvez pas payer pour sortir de prison.");
        }
     } else {
     Scanner scanner = new Scanner(System.in);
        System.out.println("Vous êtes emprisonné au Mordor !");
        System.out.println("1. Payer 50 écus pour sortir");
        System.out.println("2. Utiliser une carte de sortie" 
            + (joueur.getCartesSortiePrison() > 0 
                ? " (vous en avez " + joueur.getCartesSortiePrison() + ")" 
                : " (aucune)"));
        System.out.println("3. Tenter un double aux dés");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                System.out.println("Vous choisissez de payer 50 écus pour sortir du Mordor.");
                boolean paiementReussi = Dette_Faillite.gererPaiement(joueur, 50, null);
                if (paiementReussi) {
                    joueur.sortirDePrison();
                    System.out.println("Vous sortez du Mordor !");
                    jouerTour(joueur);
                } else {
                    System.out.println("FAILLITE ! Vous ne pouvez pas payer pour sortir de prison.");
                }
                break;
            case 2:
                if (joueur.getCartesSortiePrison() > 0) {
                    joueur.utilisercarte();
                    joueur.sortirDePrison();
                    System.out.println("Carte utilisée ! Vous sortez du Mordor !");
                    jouerTour(joueur);
                } else {
                    System.out.println("Vous n'avez pas de carte de sortie !");
                }
                break;
            case 3:
                int [] tirage = Des.tirageprison();
                int de1 = tirage[0];
                int de2 = tirage[1];
                if (de1 == de2) {
                    System.out.println("Double ! Vous sortez du Mordor !");
                    joueur.sortirDePrison();
                    joueur.deplacer(de1 + de2);
                    Banque.EffetCase(joueur, CaseActuelle);
                } else {
                    System.out.println("Pas de double, vous restez au Mordor !");
                    nblancers++;
                }
                break;
        }
    }
  }
}
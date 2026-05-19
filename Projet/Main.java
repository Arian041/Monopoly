import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Joueur> joueurs = new ArrayList<>();
    
    // MODIFICATION 1 : Le scanner unique est placé ici pour être partagé par tout le programme
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Suppression de la déclaration locale du Scanner pour utiliser la variable globale
        
        // 0 = Case Départ
        // 1 = Propriété
        // 2 = Caisse de communauté
        // 3 = Impôts
        // 6 = Compagnie (Palantír, Pierre du Voir)
        // 7 = Case neutre (Prison, Parking, Aller en Prison)

        // ============================================================
        // PROPRIÉTÉS CLASSIQUES (par couleur)
        // ============================================================

        // Marron
        Propriete p1 = new Propriete("Bag End", "marron", 60, 2);
        Propriete p2 = new Propriete("Cul-de-Sac", "marron", 60, 4);

        // Bleu clair
        Propriete p3 = new Propriete("Porte de Durin", "bleu clair", 100, 6);
        Propriete p4 = new Propriete("Salle de Balin", "bleu clair", 100, 6);
        Propriete p5 = new Propriete("Pont de Khazad-dûm", "bleu clair", 120, 8);

        // Rose
        Propriete p6 = new Propriete("Caras Galadhon", "rose", 140, 10);
        Propriete p7 = new Propriete("Source de Nimrodel", "rose", 140, 10);
        Propriete p8 = new Propriete("Miroir de Galadriel", "rose", 160, 12);

        // Orange
        Propriete p9 = new Propriete("Ents de Fangorn", "orange", 180, 14);
        Propriete p10 = new Propriete("Tour Orthanc", "orange", 180, 14);
        Propriete p11 = new Propriete("Isengard", "orange", 200, 16);

        // Rouge
        Propriete p12 = new Propriete("Gouffre de Helm", "rouge", 220, 18);
        Propriete p13 = new Propriete("Edoras", "rouge", 220, 18);
        Propriete p14 = new Propriete("Plaines du Rohan", "rouge", 240, 20);

        // Jaune
        Propriete p15 = new Propriete("Osgiliath", "jaune", 260, 22);
        Propriete p16 = new Propriete("Pelennor", "jaune", 260, 22);
        Propriete p17 = new Propriete("Minas Tirith", "jaune", 280, 24);

        // Vert
        Propriete p18 = new Propriete("Ithilien", "vert", 300, 26);
        Propriete p19 = new Propriete("Henneth Annûn", "vert", 300, 26);
        Propriete p20 = new Propriete("Forteresse de Cirith", "vert", 320, 28);

        // Bleu foncé
        Propriete p21 = new Propriete("Barad-dûr", "bleu foncé", 350, 35);
        Propriete p22 = new Propriete("Montagne du Destin", "bleu foncé", 400, 50);

        // ============================================================
        // GARES (4 gares)
        // ============================================================
        Propriete g1 = new Propriete("Chemin de Bree", 200, 0);
        Propriete g2 = new Propriete("Chemin de Rohan", 200, 0);
        Propriete g3 = new Propriete("Chemin de Gondor", 200, 0);
        Propriete g4 = new Propriete("Chemin du Mordor", 200, 0);

        // ============================================================
        // COMPAGNIES (2 compagnies)
        // ============================================================
        Propriete comp1 = new Propriete("Palantír", 150);
        Propriete comp2 = new Propriete("Pierre du Voir", 150);

        // ============================================================
        // CRÉATION DU PLATEAU
        // ============================================================
        Plateau plateau = new Plateau();

        Case c0 = new Case("Départ", 0);
        Case c1 = new Case(p1);
        Case c2 = new Case("Fraternité", 2);
        Case c3 = new Case(p2);
        Case c4 = new Case("Impôt de Guerre", 3);
        Case c5 = new Case(g1);
        Case c6 = new Case(p3);
        Case c7 = new Case("L'Anneau", 5);
        Case c8 = new Case(p4);
        Case c9 = new Case(p5);
        Case c10 = new Case("Prison (Mordor)", 0);
        Case c11 = new Case(p6);
        Case c12 = new Case(comp1);
        Case c13 = new Case(p7);
        Case c14 = new Case(p8);
        Case c15 = new Case(g2);
        Case c16 = new Case(p9);
        Case c17 = new Case("Fraternité", 2);
        Case c18 = new Case(p10);
        Case c19 = new Case(p11);
        Case c20 = new Case("Parking (Rivendell)", 0);
        Case c21 = new Case(p12);
        Case c22 = new Case("L'Anneau", 5);
        Case c23 = new Case(p13);
        Case c24 = new Case(p14);
        Case c25 = new Case(g3);
        Case c26 = new Case(p15);
        Case c27 = new Case(p16);
        Case c28 = new Case(comp2);
        Case c29 = new Case(p17);
        Case c30 = new Case("Aller en Prison", 0);
        Case c31 = new Case(p18);
        Case c32 = new Case(p19);
        Case c33 = new Case("Fraternité", 2);
        Case c34 = new Case(p20);
        Case c35 = new Case(g4);
        Case c36 = new Case("L'Anneau", 5);
        Case c37 = new Case(p21);
        Case c38 = new Case("Taxe de Sauron", 3);
        Case c39 = new Case(p22);
                 
        plateau.ajouterCase(c0);
        plateau.ajouterCase(c1);
        plateau.ajouterCase(c2);
        plateau.ajouterCase(c3);
        plateau.ajouterCase(c4);
        plateau.ajouterCase(c5);
        plateau.ajouterCase(c6);
        plateau.ajouterCase(c7);
        plateau.ajouterCase(c8);
        plateau.ajouterCase(c9);
        plateau.ajouterCase(c10);
        plateau.ajouterCase(c11);
        plateau.ajouterCase(c12);
        plateau.ajouterCase(c13);
        plateau.ajouterCase(c14);
        plateau.ajouterCase(c15);
        plateau.ajouterCase(c16);
        plateau.ajouterCase(c17);
        plateau.ajouterCase(c18);
        plateau.ajouterCase(c19);
        plateau.ajouterCase(c20);
        plateau.ajouterCase(c21);
        plateau.ajouterCase(c22);
        plateau.ajouterCase(c23);
        plateau.ajouterCase(c24);
        plateau.ajouterCase(c25);
        plateau.ajouterCase(c26);
        plateau.ajouterCase(c27);
        plateau.ajouterCase(c28);
        plateau.ajouterCase(c29);
        plateau.ajouterCase(c30);
        plateau.ajouterCase(c31);
        plateau.ajouterCase(c32);
        plateau.ajouterCase(c33);
        plateau.ajouterCase(c34);
        plateau.ajouterCase(c35);
        plateau.ajouterCase(c36);
        plateau.ajouterCase(c37);
        plateau.ajouterCase(c38);
        plateau.ajouterCase(c39);

        boolean enCours = true;
        while (enCours) {
            System.out.println("=== Bienvenue dans Lord of Capitalism ! ===");
            System.out.println("1. Voir le Leaderboard");
            System.out.println("2. Lancer une nouvelle partie");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            int choix;
            while (true) {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();

                    if (choix == 1 || choix == 2 || choix == 3) {
                        break; // Saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // Supprime l'entrée invalide
                }
            }
            if (choix == 1) {
                Leaderboard.afficherLeaderboard();
            } else if (choix == 2) {
                initialiserPartie();
                Jeu j1 = new Jeu(plateau, joueurs);
                j1.jouer();
                enCours = false; // Arrête le programme une fois la partie finie
            } else if (choix == 3) {
                System.out.println("Au revoir !");
                enCours = false;
            } else {
                System.out.println("Option invalide, réessayez.");
            }
        }
    }

    // ============================================================
    // Début de partie : initialisation et règles
    // ============================================================
    public static void initialiserPartie() {
        // MODIFICATION 2 : Suppression de la ligne "Scanner scanner = new Scanner(System.in);" pour utiliser le scanner partagé

        int nbJoueurs;
        while (true) {
            System.out.print("Combien de joueurs ? (2-8) : ");

            if (scanner.hasNextInt()) {
                nbJoueurs = scanner.nextInt();
                
                // MODIFICATION 3 : Vider le buffer immédiatement après nextInt() pour effacer la touche 'Entrée'
                scanner.nextLine(); 

                if (nbJoueurs >= 2 && nbJoueurs <= 8) {
                    break; // Saisie valide
                } else {
                    System.out.println("Nombre de joueurs invalide. Veuillez entrer un nombre entre 2 et 8.");
                }
            } else {
                System.out.println("Veuillez entrer un nombre.");
                scanner.next(); // Supprime l'entrée invalide
            }
        }

        for (int i = 0; i < nbJoueurs; i++) {
            String nom = "";
            
            while (true) {
                // 1. On affiche d'abord la demande
                System.out.print("Nom du joueur " + (i + 1) + " : "); 
                
                // 2. On récupère la saisie (on retire les espaces invisibles avec trim())
                nom = scanner.nextLine().trim();
                
                // 3. Si le nom est valide, on sort de la boucle de validation
                if (!nom.isEmpty()) {
                    break;
                }
                
                // Message d'erreur si l'utilisateur a juste appuyé sur Entrée
                System.out.println("Erreur : Le nom ne peut pas être vide !");
            }
            
            // 4. On enregistre le joueur une fois le nom valide obtenu
            joueurs.add(new Joueur(nom));
            System.out.println("Joueur " + nom + " enregistré !\n");
        }
    }
}
import java.util.ArrayList;
import java.util.Random;

public class Carte {
    // Cartes Caisse de Communauté
    public static String[] cartescommunautes= {
        "La Communauté se réunit à Bree pour fêter votre retour.",//"Festin au Poney Fringant" recevoir 30 pièces de chaque joueu
        "Sam soigne toute la compagnie avec ses herbes de la Comté.",//"Remède de Sam Gamegie" recevoir 100 pièces de la banque
        "Bilbon vous lègue une part de son butin nain.",//"Testament du Vieux Bilbon" recevoir 200 pièces de la banque
        "La Comté est ravagée par Saroumane. Il faut reconstruire chaque demeure.",//"Réparation des Trous de Hobbit" payer 40 par maison, 115 par hôtel
        "Le Roi Elessar monte sur le trône de Gondor. La paix apporte la prospérité.",//"Couronnement d'Aragorn" recevoir 50 pièces de chaque joueur
        "Piégé dans la tanière de l'araignée géante, vous ne pouvez vous libérer seul.",//"Toile de Shelob" déplacer le joueur case 10 + emprisonner
        "Les Elfes vous emmènent au-delà des mers. Vous rentrez purifié.",//"Voyage vers les Terres Immortelles" carte à conserver, libère de prison gratuitement
        "Pippin chante pour le Seigneur Denethor, votre réputation est rehaussée."//"Chant des Ménestrels" déplacer le joueur case 0 + encaisser 200
    };

    // Cartes Chance
    public static String[] cartesChance = {
        "Sauron vous a repéré. Avancez immédiatement jusqu'à la Montagne du Destin.",//"L'Appel de l'Anneau" déplacer le joueur case 10 + emprisonner
        "Gwaihir vous transporte hors du danger.",//"Les Aigles arrivent !" carte à conserver, libère de prison gratuitement
        "Le Mage Blanc vous guide vers la fortune.",//"Conseil de Gandalf" déplacer le joueur case 0 + encaisser 200
        "Vous avez combattu vaillamment à Helm's Deep.",//"La Bataille du Gouffre" recevoir 50 pièces de chaque joueur
        "Galadriel vous offre un présent elfique inestimable.",//"Don de la Lothlórien" recevoir 150 pièces de la banque
        "Le dragon a fondu sur vos richesses pendant votre absence.",//"Smaug a pillé votre trésor" payer 100 pièces à la banque
        "Vous découvrez une épée elfique enchantée.",//"Lame de Númenor" reculer de 3 cases
        "L'œil de Sauron s'est posé sur toutes vos propriétés."//"Les Feux du Mordor" payer 25 par maison, 100 par hôtel
    };



    // Méthode pour tirer une carte Chance aléatoire
     public static int tirerIndexCarteChance() {
        Random rand = new Random();
        return rand.nextInt(cartesChance.length);
    }
    
    public static String tirerCarteChance() {
        int index = tirerIndexCarteChance();
        return cartesChance[index];
    }
    
   

    // Méthode pour obtenir un index de carte Caisse de Communauté
    public static int tirerIndexCarteCommunaute() {
        Random rand = new Random();
        return rand.nextInt(cartescommunautes.length);
    }

    public static String tirerCarteCommunaute() {
        int index = tirerIndexCarteCommunaute();
        return cartescommunautes[index];
    }

   public static String actionCarteCommunaute(Joueur joueur, ArrayList<Joueur> tousLesJoueurs) {
    int index = tirerIndexCarteCommunaute();
    String carte = cartescommunautes[index];

    switch (index) {
        case 0:
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 30 pièces de chaque joueur.");
            // Recevoir 30 pièces de chaque autre joueur
            for (int i = 0; i < tousLesJoueurs.size(); i++) {
                Joueur autreJoueur = tousLesJoueurs.get(i);
                if (!autreJoueur.equals(joueur)) {
                    boolean paiementReussi = Dette_Faillite.gererPaiement(autreJoueur, 30, joueur);
                    if (!paiementReussi) {
                        System.out.println(autreJoueur.getNom() + " est en FAILLITE !");
                    }
                }
            }
            break;
        case 1:
            joueur.ajouterArgent(100);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 100 pièces.");
            break;
        case 2:
            joueur.ajouterArgent(200);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 200 pièces.");
            break;
        case 3:
            // Payer 40 par maison et 115 par hôtel
            int nbMaisons = joueur.getNbMaisons();
            int nbHotels = joueur.getNbHotels();
            int montant = (nbMaisons * 40) + (nbHotels * 115);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous payez " + montant + " pièces.");
            boolean paiement = Dette_Faillite.gererPaiement(joueur, montant, null);
            if (!paiement) {
                System.out.println("FAILLITE ! Vous ne pouvez pas payer vos réparations.");
            }
            
            break;
        case 4:
            // Recevoir 50 pièces de chaque autre joueur
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 50 pièces de chaque joueur.");

            for (int i = 0; i < tousLesJoueurs.size(); i++) {
                Joueur autreJoueur = tousLesJoueurs.get(i);
                if (!autreJoueur.equals(joueur)) {
                    boolean paiementReussi = Dette_Faillite.gererPaiement(autreJoueur, 50, joueur);
                    if (!paiementReussi) {
                        System.out.println(autreJoueur.getNom() + " est en FAILLITE !");
                    }
                }
            }
            break;
        case 5:
            System.out.println("Vous avez tiré la carte : " + carte + ", vous allez en prison.");
            joueur.allerEnPrison();
            break;
        case 6:
            System.out.println("Vous avez tiré la carte : " + carte + ", vous obtenez une  sortie de prison gratuite.");
            joueur.ajouterCarte();
            break;
        case 7:
            System.out.println("Vous avez tiré la carte : " + carte + ", vous avancez jusqu'à la case départ et recevez 200 pièces.");
            joueur.setPosition(0);
            joueur.ajouterArgent(200);
            break;
    }

    return carte;
  }

  public static String actionCarteChance(Joueur joueur, ArrayList<Joueur> tousLesJoueurs) {
    int index = tirerIndexCarteChance();
    String carte = cartesChance[index];

    switch (index) {
        case 0:
            joueur.allerEnPrison();
            System.out.println("Vous avez tiré la carte : " + carte + ", vous allez en prison.");
            break;
        case 1:
            joueur.ajouterCarte();
            System.out.println("Vous avez tiré la carte : " + carte + ", vous obtenez une  sortie de prison gratuite.");
            break;
        case 2:
            joueur.setPosition(0);
            joueur.ajouterArgent(200);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous avancez jusqu'à la case départ et recevez 200 pièces.");
            break;
        case 3:
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 50 pièces de chaque joueur.");
            for (int i = 0; i < tousLesJoueurs.size(); i++) {
                Joueur autreJoueur = tousLesJoueurs.get(i);
                if (!autreJoueur.equals(joueur)) {
                    boolean paiementReussi = Dette_Faillite.gererPaiement(autreJoueur, 50, joueur);
                    if (!paiementReussi) {
                        System.out.println(autreJoueur.getNom() + " est en FAILLITE !");
                    }
                }
            }
            break;
        case 4:
            joueur.ajouterArgent(150);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous recevez 150 pièces.");
            break;
        case 5:
            joueur.retirerArgent(100);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous payez 100 pièces.");
            break;
        case 6: 
            joueur.deplacer(-3);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous reculez de 3 cases.");
            break;
        case 7:
            // Payer 25 par maison et 100 par hôtel
            int nbMaisons = joueur.getNbMaisons();
            int nbHotels = joueur.getNbHotels();
            int montant = (nbMaisons * 25) + (nbHotels * 100);
            System.out.println("Vous avez tiré la carte : " + carte + ", vous payez " + montant + " pièces.");
            boolean paiement = Dette_Faillite.gererPaiement(joueur, montant, null);
            if (!paiement) {
                System.out.println("FAILLITE ! Vous ne pouvez pas payer vos réparations.");
            }
            break;
    }

    return carte;   
  }
}
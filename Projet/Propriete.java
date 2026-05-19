import java.util.ArrayList;
import java.util.Scanner;
public class Propriete{
    private String nom;
    private String couleur;
    private int prixAchat;
    private int loyer;
    private Joueur proprietaire;
    private boolean estHypothequee;
    private int nbMaisons;
    private boolean Hotel;
    
    // Le constructeur de la classe Propriete
    public Propriete(String nom, String couleur, int prixAchat, int loyer) {
        this.nom = nom;
        this.couleur = couleur;
        this.prixAchat = prixAchat;
        this.loyer = loyer;
        this.proprietaire = null; // La propriété n'a pas de propriétaire au départ
        this.estHypothequee = false;
        this.nbMaisons = 0;
        this.Hotel = false;
    }
    // le constructeur pour les gares
    public Propriete(String nom, int prixAchat, int loyer){
        this.nom = nom;
        this.couleur = "g"; 
        this.prixAchat = prixAchat;
        this.loyer = loyer;
        this.proprietaire = null; // La propriété n'a pas de propriétaire au départ
        this.estHypothequee = false;
        this.nbMaisons = 0;
        this.Hotel = false;
    }
    // le constructeur pour les compagnies
    public Propriete(String nom, int prixAchat){
        this.nom = nom;
        this.couleur = "c"; 
        this.prixAchat = prixAchat;
        this.loyer = 0; // Le loyer pour les compagnies est calculé différemment
        this.proprietaire = null; // La propriété n'a pas de propriétaire au départ
        this.estHypothequee = false;
        this.nbMaisons = 0;
        this.Hotel = false;
    }

    public String getNom() {
        return nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public int getLoyer() {
        switch (nbMaisons) {
            case 1: loyer = 30;
                    break;
                    
            case 2: loyer = 90;
                    break;
            case 3:loyer = 200;
                    break;
            case 4: loyer = 400;
                    break;
     }
        if (Hotel == true){
            return loyer = 550;
        }
        return loyer;
    }

    public boolean estHypothequee() {
        return estHypothequee;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public int getnbMaisons(){
        return nbMaisons;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void ajoutmaison(){
        if(nbMaisons < 5){
            nbMaisons++;
        }else if (nbMaisons==5){
                Hotel = true;
            System.out.println("Vous avez construit un hôtel sur la propriété " + this.nom);
        }else{
            System.out.println("vous avez déja un hotel sur cette propriété.");
        }
    }

    public static void enchere(Propriete propriete) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enchères pour la propriété : " + propriete.getNom());
        int montant = 10;
        ArrayList<Joueur> participants = new ArrayList<>(); // Liste des joueurs participants à l'enchère
        for (int i = 0; i < Main.joueurs.size(); i++) {
            participants.add(Main.joueurs.get(i));
            } 
        while (participants.size() > 1) {
            for(int i = 0; i < participants.size(); i++) {
                if (participants.size() <= 1){
                    break; // Si un seul participant reste, sortir de la boucle
                }
                Joueur joueur = participants.get(i);
                System.out.println(joueur.getNom() + ", voulez-vous enchérir ?" );
                int reponse;
                while (true) {
                System.out.println("1.oui/ 2.non\n Vous avez "+joueur.getArgent()+" sur votre compte en banque.");

                if (scanner.hasNextInt()) {
                    reponse = scanner.nextInt();

                    if (reponse == 1 || reponse == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
                }
                if (reponse == 1 ){
                    System.out.println("Entrez votre enchère (minimum : " + (montant+1) + ") ");
                    if(scanner.hasNextInt()){
                        int enchere = scanner.nextInt();
                        scanner.nextLine(); // Consommer le \n après l'entier
                    if (enchere > montant && enchere <= joueur.getArgent()) {
                        montant = enchere;
                    } else if (enchere > joueur.getArgent()) {
                        System.out.println("Vous n'avez pas assez d'argent pour cette enchère !");
                        i--; // Revenir au même joueur pour qu'il puisse enchérir à nouveau
                    }
                    else {
                        System.out.println("Votre enchère doit être supérieure à " + montant + " !");
                        i--; // Revenir au même joueur pour qu'il puisse enchérir à nouveau
                    }
                    }else{
                    System.out.println("Enchère invalide. Veuillez entrer un montant raisonnable.");
                    i--;
                    }
                } else if(reponse == 2) {
                    System.out.println(joueur.getNom() + " se retire de l'enchère.");
                    participants.remove(joueur); // Le joueur se retire de l'enchère
                    i--;
                }
            }
        }
        if (participants.size() == 1) {
            Joueur gagnant = participants.get(0);
            System.out.println("Le gagnant de l'enchère est : " + gagnant.getNom() + " avec une enchère de " + montant);
            propriete.setProprietaire(gagnant);
            gagnant.ajouterPropriete(propriete); // Ajouter la propriété à la liste des propriétés du gagnant
            gagnant.retirerArgent(montant); // Le gagnant paie le montant de l'enchère
        } else {
            System.out.println("Aucun gagnant pour cette enchère.");
        }

    } 
    public static void gererHypotheque(Joueur joueur) {
        ArrayList<Propriete> Hypothequables = new ArrayList<>();
        for (int i = 0; i < joueur.getProprietes().size(); i++) {
            Propriete propriete = joueur.getProprietes().get(i);
            if (!propriete.estHypothequee) {
                Hypothequables.add(propriete);
            }
        }
        if (Hypothequables.isEmpty()) {
            System.out.println("Vous n'avez aucune propriété hypothécable.");
            System.out.println("(Les propriétés avec des maisons doivent d'abord être vendues)");
            return;
        }

        System.out.println("\n=== Propriétés hypothécables ===");
        for (int i = 0; i < Hypothequables.size(); i++) {
            Propriete p = Hypothequables.get(i);
            String typeprop;
            if (p.getCouleur().equals("g")){
                typeprop = "Gare";
            } else if (p.getCouleur().equals("c")){
                typeprop = "Compagnie";
            } else {
                typeprop = "Propriété";
            }
            System.out.println(i + ". " + p.getNom() + " ( "+typeprop+" ) - " + p.getPrixAchat() + " écus");
        }
        System.out.println(Hypothequables.size() + ". Annuler");
        System.out.println("Entrez le numéro de la propriété à hypothéquer :");
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        int indice = Hypothequables.size();
        while (choix < 0 || choix > indice) {
            System.out.println("votre choix ( 0 - " + indice + " ) :");
            if(scanner.hasNextInt()){
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix == indice) {
                    System.out.println("hypothèque annulée.");
                    return;
                }
            } else {
                System.out.println("Choix invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Consommer l'entrée invalide
            }
        }
        
        
        Propriete propriete = Hypothequables.get(choix);
        if (!propriete.estHypothequee) {
            propriete.estHypothequee = true;
            int montantHypotheque = propriete.getPrixAchat() / 2; // Montant de l'hypothèque (50% du prix d'achat)
            propriete.getProprietaire().ajouterArgent(montantHypotheque); // Le propriétaire reçoit l'argent de l'hypothèque
            System.out.println("La propriété " + propriete.getNom() + " est maintenant hypothéquée. Vous avez reçu " + montantHypotheque + " pièces.");
        } else {
            System.out.println("La propriété " + propriete.getNom() + " est déjà hypothéquée.");
        }
    }

    public static void LeverHypotheque(Propriete propriete){
        if (propriete.estHypothequee){
            propriete.estHypothequee = false;
            int montantLeverHypotheque = (int)(propriete.getPrixAchat()/2 * 1.1); // Montant pour lever l'hypothèque (50% du prix d'achat + 10% d'intérêts)
            propriete.getProprietaire().retirerArgent(montantLeverHypotheque); // Le propriétaire paie pour lever l'hypothèque
            System.out.println("L'hypothèque de la propriété " + propriete.getNom() + " est levée. Vous avez payé " + montantLeverHypotheque + " pièces.");
        }
        
    }

    public static void vendremaison(Propriete propriete, int nbMaisonsAVendre){
        if ( propriete.getnbMaisons() > 0 && !propriete.Hotel){
            propriete.nbMaisons -= nbMaisonsAVendre;
            int montantVente = (propriete.getPrixAchat() / 4) * nbMaisonsAVendre; // Montant de la vente d'une maison (25% du prix d'achat)
            propriete.getProprietaire().ajouterArgent(montantVente); // Le propriétaire reçoit l'argent de la vente
            System.out.println(nbMaisonsAVendre + " maison(s) sur " + propriete.getNom() + " a/ont  été vendue(s). Vous avez reçu " + montantVente + " pièces.");
         } else if (propriete.Hotel){
            propriete.Hotel = false;
            propriete.nbMaisons -= nbMaisonsAVendre ; // Lorsque qu'un hotel est installé, cela revient a 5 maisons.
            int montantVenteHotel = (propriete.getPrixAchat() / 4) * nbMaisonsAVendre; // Montant de la vente d'un hôtel (25% du prix d'achat)
            propriete.getProprietaire().ajouterArgent(montantVenteHotel);
            System.out.println("Un hotel et "+ (nbMaisonsAVendre-1) + " maison(s) sur " + propriete.getNom() + " a/ont  été vendue(s). Vous avez reçu " + montantVenteHotel + " pièces.");
         } else {
            System.out.println("il n'y a aucunes maison à vendre sur cette propriété.");
         }
    }

    public static void gererventemaisons(Joueur joueur){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voici vos propriétés avec maisons :");
        for (int i = 0; i<joueur.getProprietes().size(); i++){
            System.out.println(i + ". " + joueur.getProprietes().get(i).getNom() + " - " + joueur.getProprietes().get(i).getnbMaisons() + " maisons");
        }
        System.out.println("Entrez le numéro de la propriété sur laquelle vous voulez vendre une maison :");
        int choix = scanner.nextInt();
        scanner.nextLine();
        Propriete propriete = null;
        if (choix >= 0 && choix < joueur.getProprietes().size() && joueur.getProprietes().get(choix).getnbMaisons() > 0 && !joueur.getProprietes().get(choix).getCouleur().equals("g") && !joueur.getProprietes().get(choix).getCouleur().equals("c")){
            propriete = joueur.getProprietes().get(choix);
            System.out.println("combien de maisons voulez vous vendre pour la propriete : " + propriete.getNom() + " ? \n voici votre nombre de maisons : " + propriete.getnbMaisons());
            int NbMaisonsaVendre = scanner.nextInt();
            scanner.nextLine();
            if (NbMaisonsaVendre > 0 && NbMaisonsaVendre <= propriete.getnbMaisons()){
                vendremaison(propriete, NbMaisonsaVendre);
            } else {
                System.out.println("Nombre de maisons à vendre invalide.");
            }
        } else {
            System.out.println("Choix invalide.");
        }
    }

    public static void vendrePropriete(Joueur vendeur) {
        if (vendeur.getProprietes().isEmpty()) {
            System.out.println("Vous n'avez aucune propriété à vendre.");
            return;
        }

        System.out.println("\n=== Vos propriétés ===");
        for (int i = 0; i < vendeur.getProprietes().size(); i++) {
            Propriete propriete = vendeur.getProprietes().get(i);
            String statut = propriete.estHypothequee() ? " [HYPOTHÉQUÉE]" : "";
            String maisons = propriete.getnbMaisons() > 0 ? 
                " [" + propriete.getnbMaisons() + " maisons]" : "";
            System.out.println(i + ". " + propriete.getNom() + " - Prix d'achat : " + propriete.getPrixAchat() + " écus" + statut + maisons);
        }
        System.out.println(vendeur.getProprietes().size() + ". Annuler");
        Scanner scanner = new Scanner(System.in);
        
        int choix = -1;
        int indice = vendeur.getProprietes().size();
        while (choix < 0 || choix > indice) {
            System.out.println("votre choix ( 0 - " + indice + " ) :");
            if(scanner.hasNextInt()){
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix == indice) {
                return;
                }
            } else {
                System.out.println("Choix invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Consommer l'entrée invalide
            }
        }
        

        Propriete proprieteAVendre = vendeur.getProprietes().get(choix);

        if (proprieteAVendre.getnbMaisons() > 0) {
            System.out.println("Vous devez d'abord vendre les maisons avant de vendre la propriété !");
            return;
        }

        System.out.println("\n=== Vente de " + proprieteAVendre.getNom() + " ===");
        System.out.println("À quel joueur voulez-vous vendre cette propriété ?");
        
        ArrayList<Joueur> autresJoueurs = new ArrayList<>();
        for (int i = 0; i < Main.joueurs.size(); i++) {
            Joueur joueur = Main.joueurs.get(i);
            if (!joueur.equals(vendeur) && joueur.getArgent() >= 0) {
                autresJoueurs.add(joueur);
            }
        }

        if (autresJoueurs.isEmpty()) {
            System.out.println("Aucun acheteur disponible.");
            return;
        }

        for (int i = 0; i < autresJoueurs.size(); i++) {
            Joueur joueur = autresJoueurs.get(i);
            System.out.println(i + ". " + joueur.getNom() + " (a " + joueur.getArgent() + " écus)");
        }
        System.out.println(autresJoueurs.size() + ". Annuler");
       
        int choixAcheteur = -1;
        int indiceAcheteur = autresJoueurs.size();
       
        while (choixAcheteur < 0 || choixAcheteur > indiceAcheteur) {
            System.out.println("votre choix ( 0 - " + indiceAcheteur + " ) :");
            if(scanner.hasNextInt()){
                choixAcheteur = scanner.nextInt();
                scanner.nextLine();
                 if (choixAcheteur == indiceAcheteur) {
                 return;
                 }
            
            } else {
                System.out.println("Choix invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Consommer l'entrée invalide
            }
        }    

        Joueur acheteur = autresJoueurs.get(choixAcheteur);

        System.out.println("À quel prix voulez-vous vendre ? (Prix conseillé : " + 
            proprieteAVendre.getPrixAchat() / 2 + " - " + proprieteAVendre.getPrixAchat() + " écus)");
        System.out.print("Prix : ");
        int prix = scanner.nextInt();
        scanner.nextLine();

        if (acheteur.getArgent() < prix) {
            System.out.println(acheteur.getNom() + " n'a pas assez d'argent !");
            return;
        }

        System.out.println(acheteur.getNom() + ", acceptez-vous d'acheter " + 
            proprieteAVendre.getNom() + " pour " + prix + " écus ?");
            int reponse;
            while (true) {
                System.out.println("1. Oui 2. Non");

                if (scanner.hasNextInt()) {
                    reponse = scanner.nextInt();

                    if (reponse == 1 || reponse == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
            }
        if (reponse == 1) {
            // Transaction
            acheteur.retirerArgent(prix);
            vendeur.ajouterArgent(prix);
            vendeur.getProprietes().remove(proprieteAVendre);
            acheteur.ajouterPropriete(proprieteAVendre);
            proprieteAVendre.setProprietaire(acheteur);
            
            System.out.println("Transaction effectuée ! " + acheteur.getNom() + " achète " + proprieteAVendre.getNom() + " pour " + prix + " écus.");
        } else {
            System.out.println("Transaction annulée.");
        }
    }

    public static void gererPropriete(Joueur joueur, Case caseActuelle) {
        Scanner scanner = new Scanner(System.in);
        String Couleur = caseActuelle.getCouleur();
        Propriete propriete = caseActuelle.getPropriete();
        if (Couleur == null || Couleur.equals("g") || Couleur.equals("c")){
            return;
        }

        Joueur proprietaire = propriete.getProprietaire();

        if (proprietaire == null) {
            // Propriété disponible : proposer achat ou enchère
            System.out.println("Cette propriété est disponible !");
            System.out.println("Prix d'achat : " + propriete.getPrixAchat() + " ecus");
           int choix = 0;
            while (true) {
                System.out.println("1. Acheter 2. Ne pas acheter");

                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();

                    if (choix == 1 || choix == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
            }

            switch (choix) {
                case 1:
                    if (joueur.getArgent() >= propriete.getPrixAchat()) {
                        joueur.retirerArgent(propriete.getPrixAchat());
                        joueur.ajouterPropriete(propriete);
                        propriete.setProprietaire(joueur);
                        System.out.println(joueur.getNom() + " achète " + propriete.getNom() + " !");
                    } else {
                        System.out.println("Vous n'avez pas assez d'argent !");
                        enchere(propriete);
                    }
                    break;
                case 2:
                        enchere(propriete);
                    break;
            }

        } else if (!proprietaire.equals(joueur)) {
            // Payer le loyer au propriétaire
            int loyer = propriete.getLoyer();
            System.out.println("Cette propriété appartient à " + proprietaire.getNom() 
                + " ! Vous payez " + loyer + " écus.");
            boolean paiementReussi = Dette_Faillite.gererPaiement(joueur, loyer, proprietaire);
            if (!paiementReussi) {
                System.out.println("FAILLITE ! Vous ne pouvez pas payer le loyer.");
            }
        } else {
            System.out.println("Vous êtes chez vous !");
            gererConstructions(joueur, propriete);
        }

    }

     
    public static void gererConstructions(Joueur joueur, Propriete propriete) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous construire une maison ?");
        System.out.println("Prix de construction : " + (propriete.getPrixAchat() / 2) + " écus");
        int choix = 0;
            while (true) {
                System.out.println("1. oui 2. Non");

                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();

                    if (choix == 1 || choix == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
            }
       
        if (choix == 1) {
            int prixMaison = propriete.getPrixAchat() / 2;
            if (joueur.getArgent() >= prixMaison) {
                joueur.retirerArgent(prixMaison);
                propriete.ajoutmaison();
                System.out.println("Maison construite sur " + propriete.getNom() 
                    + " ! (" + propriete.getnbMaisons() + "/5)");
            } else {
                System.out.println("Vous n'avez pas assez d'argent pour construire.");
            }
        } else {
            System.out.println("Vous avez choisi de ne pas construire.");
        }
    }


        public static void gererGare(Joueur joueur, Case caseActuelle) {
        Scanner scanner = new Scanner(System.in);
        Propriete gare = caseActuelle.getPropriete();
        String Couleur = caseActuelle.getCouleur();
        if (!Couleur.equals("g")){
            scanner.close();
            return;
        }

        Joueur proprietaire = gare.getProprietaire();
        if (proprietaire == null) {
            System.out.println("Cette gare est disponible ! \n Prix : 200 ecus");
            int choix = 0;
            while (true) {
                System.out.println("1. Acheter 2. Ne pas acheter");

                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();

                    if (choix == 1 || choix == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
            }
       
            if (choix == 1) {
                if (joueur.getArgent() >= 200) {
                    joueur.retirerArgent(200);
                    joueur.ajouterPropriete(gare);
                    gare.setProprietaire(joueur);
                    System.out.println(joueur.getNom() + " achète la gare " + gare.getNom() + " !");
                } else {
                    System.out.println("Vous n'avez pas assez d'argent !");
                    enchere(gare);
                }
            } else if (choix == 2) {
                enchere(gare);
            }
        } else if (!proprietaire.equals(joueur)) {
             int loyerGare = Propriete.LoyerGare(proprietaire);
            System.out.println("Gare appartenant à " + proprietaire.getNom() 
                + ". Vous devez payer " + loyerGare + " ecus.");
            boolean paiementReussi = Dette_Faillite.gererPaiement(joueur, loyerGare, proprietaire);
            if (!paiementReussi) {
                System.out.println("FAILLITE ! Vous ne pouvez pas payer le loyer de la gare.");
            }
            
        } else {
            System.out.println("Vous êtes dans votre propre gare !");
        }
    }

    public static int LoyerGare(Joueur proprietaire) {
        int nbGares = 0;
        for (int i = 0; i < proprietaire.getProprietes().size(); i++) {
            Propriete p = proprietaire.getProprietes().get(i);
            if (p.getCouleur().equals("g")) nbGares++; // La couleur des gares est a "g" par défaut
        }
        switch (nbGares) {
            default: return 0;
            case 1:  return 50;
            case 2:  return 100;
            case 3:  return 150;
            case 4:  return 200;
            
        }
    }

    public static void gererCompagnie(Joueur joueur, Case caseActuelle) {
        Scanner scanner = new Scanner(System.in);
        Propriete compagnie = caseActuelle.getPropriete();
        String Couleur = caseActuelle.getCouleur();
        if (!Couleur.equals("c")){
            scanner.close();
            return;
        }
        Joueur proprietaire = compagnie.getProprietaire();
        if (proprietaire == null) {
            System.out.println("Cette compagnie est disponible ! Prix : 150 ecus");
            int choix = 0;
            while (true) {
                System.out.println("1. Acheter   2. Ne pas acheter");

                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();

                    if (choix == 1 || choix == 2) {
                        break; // saisie valide
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // supprime l'entrée invalide
                }
            }
       
            if (choix == 1) {
                if (joueur.getArgent() >= 150) {
                    joueur.retirerArgent(150);
                    joueur.ajouterPropriete(compagnie);
                    compagnie.setProprietaire(joueur);
                    System.out.println(joueur.getNom() + " achète " + compagnie.getNom() + " !");
                } else {
                    System.out.println("Vous n'avez pas assez d'argent !");
                    enchere(compagnie);
                }
            }
            
        } else if (!proprietaire.equals(joueur)) {
            int loyer = 0;
            int des = Des.tirageCompagnie();
            int nbcomp=0;
            for(int i=0;i<proprietaire.getProprietes().size();i++){
                if(proprietaire.getProprietes().get(i).getCouleur().equals("c")){
                    nbcomp++;}}
            if(nbcomp==1){
                 loyer = des*4;}
            else if (nbcomp==2){
                 loyer = des * 10;}
            
            System.out.println("Compagnie de " + proprietaire.getNom() 
                + ". Dés : " + des + " , vous payez " + loyer + " ecus.");
            boolean paiementReussi = Dette_Faillite.gererPaiement(joueur, loyer, proprietaire);
            if (!paiementReussi) {
                System.out.println("FAILLITE ! Vous ne pouvez pas payer le loyer de la compagnie.");
            }
        } else {
            System.out.println("Vous êtes dans votre propre compagnie !");
        }
    }
    
}

    

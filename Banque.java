// ============================================================
    // EFFETS DES CASES
    // ============================================================
public class Banque{
    

   public static void EffetCase(Joueur joueur, Case caseActuelle) {
        switch (caseActuelle.getType()) {
            case 0: // Cases neutres
                gererCaseNeutre(joueur, caseActuelle);
                break;
            case 1: // Propriété
                if(caseActuelle.getCouleur().equals("g")){
                    Propriete.gererGare(joueur, caseActuelle);
                    break;
                }else if (caseActuelle.getCouleur().equals("c")){
                    Propriete.gererCompagnie(joueur, caseActuelle);
                    break;
                }else {
                Propriete.gererPropriete(joueur, caseActuelle);
                break;
                }
            case 2: // Caisse de communauté
                System.out.println("Carte Communauté !");
                Carte.actionCarteCommunaute(joueur, Main.joueurs);
                break;
            case 3: // Impôts
                payerImpots(joueur);
                break;
            case 4: // Gare
                Propriete.gererGare(joueur, caseActuelle);
                break;
            case 5: // Chance
                System.out.println("Carte Chance !");
                Carte.actionCarteChance(joueur, Main.joueurs);
                break;
            case 6: // Compagnie
                Propriete.gererCompagnie(joueur, caseActuelle);
                break;
        }
    }


     private static void gererCaseNeutre(Joueur joueur, Case caseActuelle) {
        if (caseActuelle.getNom().equals("Aller en Prison")) {
            System.out.println("Vous êtes envoyé au Mordor !");
            joueur.allerEnPrison();
        } else {
            System.out.println("Case neutre, rien ne se passe.");
        }
    }

    private static void payerImpots(Joueur joueur){
        int montant = 200; // Montant fixe pour l'impôt
        System.out.println("Vous payer " + montant + " écus d'impôts.");
        boolean paiement = Dette_Faillite.gererPaiement(joueur, montant, null);
        if(!paiement){
            System.out.println("FAILLITE ! Vous ne pouvez pas payer vos impôts.");
        }
     }
    }
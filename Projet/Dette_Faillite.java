import java.util.Scanner;

public class Dette_Faillite {
    public static boolean gererPaiement(Joueur joueur, int montant, Joueur beneficiaire){
        System.out.println("\n--- Paiement requis ---");
        System.out.println("Montant à payer : " + montant + " écus");
        System.out.println("Votre solde : " + joueur.getArgent() + " écus");

        if(joueur.getArgent() >= montant){
            joueur.retirerArgent(montant);
            if(beneficiaire == null){
                System.out.println("Vous payez " + montant + " écus à la banque.");
            } else {
                beneficiaire.ajouterArgent(montant);
                System.out.println("Vous payez " + montant + " écus à " + beneficiaire.getNom() + ".");
            }
            return true;
        }else {
            System.out.println("vous n'avez pas assez d'argent pour payer votre dette.");
            System.out.println("Votre déficit est de " + (montant - joueur.getArgent()) + " écus.");

            Scanner scanner = new Scanner(System.in);

            while(joueur.getArgent() < montant){
                // Vérifier si le joueur peut encore générer de l'argent
                if (!joueur.aDesBiens()) {
                    // Le joueur n'a plus de biens à vendre ET n'a pas assez d'argent
                    System.out.println("\nFAILLITE ! Vous n'avez plus d'actifs à vendre et pas assez d'argent.");
                    System.out.println("Dette totale : " + montant + " écus");
                    System.out.println("Argent disponible : " + joueur.getArgent() + " écus");
                    System.out.println("Déficit final : " + (montant - joueur.getArgent()) + " écus");
                    
                    // Transférer TOUT l'argent restant au bénéficiaire
                    int argentRestant = joueur.getArgent();
                    if (argentRestant > 0) {
                        if (beneficiaire != null) {
                            beneficiaire.ajouterArgent(argentRestant);
                            System.out.println("Transfert de vos " + argentRestant + " écus restants à " + beneficiaire.getNom());
                        } else {
                            System.out.println("Vos " + argentRestant + " écus restants retournent à la banque.");
                        }
                        joueur.retirerArgent(argentRestant);
                    }
                    joueur.declarerFaillite();
                    return false; 
                }

                System.out.println("Vous devez vendre des maisons, propriétés ou hypothéquer pour payer votre dette.");
                System.out.println("\n=== Que voulez-vous faire ? ===");
                System.out.println("1. Vendre des maisons/hôtels" + 
                    (joueur.getNbMaisons() + joueur.getNbHotels() > 0 ?
                        " (vous avez " + joueur.getNbMaisons() + " maisons et " + joueur.getNbHotels() + " hôtels)"
                        : " (aucune)"));
                System.out.println("2. Hypothéquer une propriété");
                System.out.println("3. Vendre une propriété à un autre joueur");
                System.out.println("4. Voir mon bilan");

                int choix = scanner.nextInt();
                scanner.nextLine();
                switch (choix) {
                    case 1:
                        Propriete.gererventemaisons(joueur);
                        break;
                    case 2:
                        Propriete.gererHypotheque(joueur);
                        break;
                    case 3:
                        Propriete.vendrePropriete(joueur);
                        break;
                    case 4:
                        System.out.println("\n--- Bilan de " + joueur.getNom() + " ---");
                        System.out.println("Argent : " + joueur.getArgent() + " écus");
                        System.out.println("Propriétés : " + joueur.getProprietes().size());
                        for (int i = 0; i < joueur.getProprietes().size(); i++) {
                            Propriete propriete = joueur.getProprietes().get(i);
                            System.out.println("- " + propriete.getNom() + (propriete.estHypothequee() ? " (hypothéquée)" : ""));
                        }
                        System.out.println("Maisons : " + joueur.getNbMaisons());
                        System.out.println("Hôtels : " + joueur.getNbHotels());
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
                 System.out.println("\nSolde actuel : " + joueur.getArgent() + " écus");
                 System.out.println("Montant restant à payer : " + Math.max(0, montant - joueur.getArgent()) + " écus");
            }
            joueur.retirerArgent(montant);
            if (beneficiaire != null) {
                beneficiaire.ajouterArgent(montant);
                System.out.println("Vous avez payé " + montant + " écus à " + beneficiaire.getNom());
            } else {
                System.out.println("Vous avez payé " + montant + " écus à la banque");
            }
            return true;

        }

        
    }

    

    
    
}
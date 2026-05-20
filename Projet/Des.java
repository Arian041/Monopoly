import java.util.Random;
import java.util.Scanner;

public class Des {

    public static int [] tiragedesblancs(){
        Random r = new Random();
        int des1 = r.nextInt(6) + 1;
        int des2 = r.nextInt(6) + 1;
        int [] total = {des1, des2, des1 + des2}; // [dé1, dé2, total_déplacement]
        System.out.println("Vous avez tiré : " + des1 + " et " + des2 + ", votre total est de : " + total[2] +".");
        return total;
    }

    public static int [] tirageprison(){
        Random r = new Random();
        int des1 = r.nextInt(6) + 1;
        int des2 = r.nextInt(6) + 1;
        int [] total = {des1, des2};
        System.out.println("Vous avez tiré : " + des1 + " et " + des2 + ".");
        return total;
    }

    public static int tirageCompagnie(){
        Random r = new Random();
        int des1 = r.nextInt(6) + 1;
        int des2 = r.nextInt(6) + 1;
        int des3 = r.nextInt(3) + 1; 
        int total = des1 + des2 + des3;
        System.out.println("Vous avez tiré : " + des1 + " et " + des2 + " et " + des3 + ", votre total est de : " + total +".");
        return total;
    }

    public static int[] tiragedesrapide(Joueur joueur, Plateau plateau){
        Random r = new Random();
        int des1 = r.nextInt(6) + 1;
        int des2 = r.nextInt(6) + 1;
        int desrapide = r.nextInt(6) + 1;
        int totalBlancs = des1 + des2;
        int choixDeplacement = totalBlancs;

        switch (desrapide) {
            case 1:
            case 2:
            case 3:
                choixDeplacement = totalBlancs + desrapide;
                System.out.println("Vous avez tiré : " + des1 + " et " + des2 + " et " + desrapide + ". Vous avancez de " + choixDeplacement + " cases." );  
                break;
            case 4:
            case 5:
                System.out.println("Vous avez tiré : " + des1 + " et " + des2 + " et BUS.");   
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Voulez vous avancer de : " + des1 + " ( " + plateau.getCase((joueur.getPosition() + des1) % 40).getNom() + " ) , de " + des2 +" ( " + plateau.getCase((joueur.getPosition() + des2) % 40).getNom() + " ) ou de " + totalBlancs +" ( " + plateau.getCase((joueur.getPosition() + totalBlancs) % 40).getNom() + " ) cases ?");

                    if (scanner.hasNextInt()) {
                        choixDeplacement = scanner.nextInt();
                        if (choixDeplacement == des1 || choixDeplacement == des2 || choixDeplacement == totalBlancs) {
                            break; 
                        } else {
                            System.out.println("Choix invalide. Veuillez entrer " + des1 + ", " + des2 + " ou " + totalBlancs + ".");
                        }
                    } else {
                        System.out.println("Veuillez entrer un nombre.");
                        scanner.next();
                    }
                }
                System.out.println("Vous avancez de " + choixDeplacement + " cases.");
                break;
            case 6:
                System.out.println("Vous avez tiré : " + des1 + " et " + des2 + " et Monsieur Monopoly. Vous avancez de " + totalBlancs + " cases.");
                choixDeplacement = totalBlancs;
                break;
        }
        
        return new int[]{des1, des2, choixDeplacement};
    }
}
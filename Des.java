
import java.util.Random;
import java.util.Scanner;

public class Des {

public static int tiragedesblancs(){
    Random r = new Random();
    int des1 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int des2 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int total = des1 + des2;
    System.out.println("Vous avez tiré : " + des1 + " et " + des2 + ", votre total est de : " + total +".");
    return total;
}

public static  int [] tirageprison(){
    Random r = new Random();
    int des1 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int des2 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int [] total = {des1,des2};
    System.out.println("Vous avez tiré : " + des1 + " et " + des2 + ".");
    return total;
}





public static int tirageCompagnie(){
    Random r = new Random();
    int des1 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int des2 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int des3 = r.nextInt(3) + 1; // Génère un nombre entre 1 et 6
    int total = des1 + des2 + des3;
    System.out.println("Vous avez tiré : " + des1 + " et " + des2 + " et " + des3 + ", votre total est de : " + total +".");
    return total;
}

public static int tiragedesrapide(){
    Random r = new Random();
    int des1 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int des2 = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int desrapide = r.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    int total = des1 + des2 ;
    switch (desrapide) {
        case 1:
        case 2:
        case 3:
            System.out.println("Vous avez tiré : " + des1 + " et " + des2 +" et " + desrapide + ", votre total est de : " + (total+desrapide) +". Vous avancez de " + (total + desrapide) + " cases." );  
            return total + desrapide; 
        case 4:
        case 5:
            System.out.println("Vous avez tiré : " + des1 + " et " + des2 +" et BUS. , votre total est de : " + total +". Voulez vous avancer de :"+ des1 + " , de " + des2 + " ou de " +total+ " cases ?");   
            Scanner scanner = new Scanner(System.in);
            int choix = scanner.nextInt();
            if(choix == des1){
                System.out.println("Vous avancez de " + des1 + " cases.");
            } else if(choix == des2){
                System.out.println("Vous avancez de " + des2 + " cases.");
            } else {
                System.out.println("Vous avancez de " + total + " cases.");
            }
            return choix;
        case 6:
            System.out.println("Vous avez tiré : " + des1 + " et " + des2 +" et " + desrapide + ", votre total est de : " + total +". Vous ne bougez pas.");
            return total;
    }
    return total;
}
}

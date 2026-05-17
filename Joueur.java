
import java.util.ArrayList;

public class Joueur {
    private String nom;
    private int position;
    private int argent;
    private boolean enPrison;
    private boolean enFaillite;
    private ArrayList<Propriete> proprietes; // Liste des propriétés possédées par le joueur
    private int cartesSortiePrison; // Cartes "Sortie de prison" possédées par le joueur


    public Joueur(String nom) {
        this.nom = nom;
        this.position = 0; // Position de départ
        this.argent = 1500; // Argent initial
        this.enPrison = false; // Le joueur n'est pas en prison au départ
        this.enFaillite = false; // Le joueur n'est pas en faillite au départ
        this.proprietes = new ArrayList<>(); // Initialiser la liste des propriétés
        this.cartesSortiePrison = 0; // Le joueur n'a pas de cartes "Sortie de prison" au départ
    }

    
    

    public String getNom() {
        return nom;
    }

    public int getPosition() {
        return position;
    }

    public int setPosition(int position) {
        this.position = position;
        return position;
    }

    public int getArgent() {
        return argent;
    }

    public boolean estEnPrison() {
        return enPrison;
    }

    public int getCartesSortiePrison() {
        return cartesSortiePrison;
    }

    public ArrayList<Propriete> getProprietes() {
        return proprietes;
    }

    public int getNbMaisons() {
        int totalMaisons = 0;
        for(int i = 0; i < proprietes.size();i++){
            if(proprietes.get(i).getnbMaisons() > 0){
                totalMaisons += proprietes.get(i).getnbMaisons();
            }
        }
        return totalMaisons;
    }

    public int getNbHotels() {
        int totalHotels = 0;
        for(int i = 0; i < proprietes.size();i++){
            if(proprietes.get(i).getnbMaisons() == 5){
                totalHotels += 1;
            }
        }
        return totalHotels;
    }

    public boolean aDesBiens() {
        if (this.getProprietes().isEmpty() == false || this.getNbMaisons() > 0 || this.getNbHotels() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean enFaillite() {
        return enFaillite;
    }


    public void ajouterCarte() {
        cartesSortiePrison++;
    }

    public void utilisercarte(){
        if(cartesSortiePrison > 0){
            cartesSortiePrison--;
        }
    }

    public void deplacer(int deplacement) {
        position = (position + deplacement + 40) % 40; // Assure que la position reste entre 0 et 39
    }

    public void ajouterArgent(int montant) {
        argent += montant;
    }

    public void retirerArgent(int montant) {
        argent -= montant;
    }
    public void allerEnPrison() {
        enPrison = true;
        position = 10; // Position de la prison
    }
    
    public void sortirDePrison() {
        enPrison = false;
    }

    public void ajouterPropriete(Propriete propriete) {
        proprietes.add(propriete);
    }
   
    public void declarerFaillite() {
        this.enFaillite = true;
        System.out.println("\n================================================");
        System.out.println("||  " + this.nom + " est en FAILLITE !          ||");
        System.out.println("||  Il/Elle est éliminé(e) du jeu.        ||");
        System.out.println("================================================\n");
    }
}



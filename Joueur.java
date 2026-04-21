import java.lang.reflect.Array;

public class Joueur {
    private String nom;
    private int position;
    private int argent;
    private boolean enPrison;
    private ArrayList<Case> proprietes; // Liste des propriétés possédées par le joueur


    public Joueur(String nom) {
        this.nom = nom;
        this.position = 0; // Position de départ
        this.argent = 1500; // Argent initial
        this.enPrison = false; // Le joueur n'est pas en prison au départ
    }

    public String getNom() {
        return nom;
    }

    public int getPosition() {
        return position;
    }
        this.argent = 1500; // Argent initial
    }

    public String getNom() {
        return nom;
    }

    public int getPosition() {
        return position;
    }

    public int getArgent() {
        return argent;
    }

    public boolean isEnPrison() {
        return enPrison;
    }


    public void deplacer(int deplacement) {
        position = (position + deplacement) % 40; // Assure que la position reste entre 0 et 39
    }

    public void ajouterArgent(int montant) {
        argent += montant;
    }

    public void retirerArgent(int montant) {
        argent -= montant;
    }

}

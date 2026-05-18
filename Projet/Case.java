

public class Case {

    String nom ; 
    String couleur ;
    int type ;
    Propriete propriete;


    public Case(String nom, int type) {
        this.nom = nom;
        this.type = type;
    }


    public Case(String nom, String couleur,int type) {
        this.nom = nom;
        this.couleur = couleur;
        this.type = type;
    }

    public Case(Propriete propriete) {
        this.nom = propriete.getNom();
        this.couleur = propriete.getCouleur();
        this.type = 1; // Type 1 pour les propriétés
        this.propriete = propriete;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    public void setType(int type) {
        this.type = type;
    }

    public Propriete getPropriete(){
        return propriete;
    }

    public String getNom() {
        return nom;
    }
    public String getCouleur() {
        return couleur;
    }
    public int getType() {
        return type;
    }

}



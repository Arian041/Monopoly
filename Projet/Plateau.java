import java.util.ArrayList;
public class Plateau {
    private ArrayList<Case> cases;

    public Plateau() {
        this.cases = new ArrayList<>();
    }

    public void ajouterCase(Case c) {
        cases.add(c);
    }

    public Case getCase(int index) {
        if (index >= 0 && index <= cases.size()) {
            return cases.get(index);
        }
        return null; // ou lancer une exception
    }

    public int getNombreCases() {
        return cases.size();
    }

    
    
}
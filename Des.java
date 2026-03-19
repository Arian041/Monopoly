import java.util.Random;
public class Des {
    private Random random;

    public Des() {
        this.random = new Random();
    }

    public int lancer(int nbDes) {
        int total = 0;

        for (int i = 0; i < nbDes; i++) {
            total += random.nextInt(6) + 1;
        }

        return total;
    }
}

import java.io.*;
import java.util.*;

public class Leaderboard{
    private static final String FILE_NAME = "leaderboard.csv";

    // Classe interne pour structurer les données d'un joueur du classement
    public static class Entry {
        String nom;
        int victoires;
        int meilleurScore;

        public Entry(String nom, int victoires, int meilleurScore) {
            this.nom = nom;
            this.victoires = victoires;
            this.meilleurScore = meilleurScore;
        }
    }

    // Charge les données depuis le fichier CSV
    public static List<Entry> chargerLeaderboard() {
        List<Entry> entries = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            return entries; // Retourne une liste vide si le fichier n'existe pas encore
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Ignore la ligne d'en-tête
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nom = parts[0];
                    int victoires = Integer.parseInt(parts[1]);
                    int meilleurScore = Integer.parseInt(parts[2]);
                    entries.add(new Entry(nom, victoires, meilleurScore));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors de la lecture du leaderboard : " + e.getMessage());
        }
        return entries;
    }

    // Sauvegarde la liste mise à jour dans le fichier CSV
    public static void sauvegarderLeaderboard(List<Entry> entries) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write("Nom,Victoires,MeilleurScore");
            bw.newLine();
            for (Entry entry : entries) {
                bw.write(entry.nom + "," + entry.victoires + "," + entry.meilleurScore);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du leaderboard : " + e.getMessage());
        }
    }

    // Enregistre ou met à jour le gagnant d'une partie
    public static void enregistrerVictoire(String nom, int argent) {
        List<Entry> entries = chargerLeaderboard();
        boolean trouve = false;

        for (Entry entry : entries) {
            if (entry.nom.equalsIgnoreCase(nom)) {
                entry.victoires++;
                // Met à jour le meilleur score si le score actuel est plus élevé
                if (argent > entry.meilleurScore) {
                    entry.meilleurScore = argent;
                }
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            entries.add(new Entry(nom, 1, argent));
        }

        sauvegarderLeaderboard(entries);
    }

    // Affiche le classement trié de manière élégante
    public static void afficherLeaderboard() {
        List<Entry> entries = chargerLeaderboard();
        if (entries.isEmpty()) {
            System.out.println("\nLe leaderboard est actuellement vide. Jouez une partie pour y figurer !");
            return;
        }

        // Tri : d'abord par victoires (décroissant), puis par meilleur score (décroissant)
        entries.sort((e1, e2) -> {
            if (e2.victoires != e1.victoires) {
                return Integer.compare(e2.victoires, e1.victoires);
            }
            return Integer.compare(e2.meilleurScore, e1.meilleurScore);
        });

        System.out.println("\n=============================================");
        System.out.println("         LEADERBOARD DE LA TERRE DU MILIEU    ");
        System.out.println("=============================================");
        System.out.printf("%-15s | %-10s | %-15s\n", "Nom", "Victoires", "Meilleur Score");
        System.out.println("---------------------------------------------");
        for (Entry entry : entries) {
            System.out.printf("%-15s | %-10d | %-15d écus\n", entry.nom, entry.victoires, entry.meilleurScore);
        }
        System.out.println("=============================================\n");
    }
}
    


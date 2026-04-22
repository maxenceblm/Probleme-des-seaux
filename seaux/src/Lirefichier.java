import java.io.BufferedReader;
import java.io.FileReader;

public class Lirefichier {
    

    public Probleme lire(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath)) ; 
            
            // Ligne 1 : lire n (nombre de seaux)
            String line1 = reader.readLine();
            int n = Integer.parseInt(line1.trim());
            
            // Ligne 2 : lire les capacités ci
            int[] ci = new int[n]; 
            String line2 = reader.readLine() ; 
            String[] tokens_ci = line2.trim().split("\\s+"); // séparation par espace
            for (int i = 0; i < n; i++) {
                ci[i] = Integer.parseInt(tokens_ci[i]);
            }
            
            // Ligne 3 : lire les états finaux fi
            int[] fi = new int[n];
            String line3 = reader.readLine();
            String[] tokens_fi = line3.trim().split("\\s+"); // séparation par espace
            for (int i = 0; i < n; i++) {
                fi[i] = Integer.parseInt(tokens_fi[i]);
            }
            
            reader.close();
            
            // Créer et retourner le problème
            Probleme probleme = new Probleme(ci, fi);
            return probleme;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

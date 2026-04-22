import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       StringBuilder sb = new StringBuilder();
       sb.append("instances/");
       Scanner scanner = new Scanner(System.in);
       try {
        System.out.print("Entrez un nom de fichier :  ");
        String in = scanner.nextLine();
        sb.append(in);
        Lirefichier buffer = new Lirefichier();
        Probleme probleme = buffer.lire(sb.toString());
        // Parcours en Largeur
        System.out.println("--- BFS ---");
        Long start = System.currentTimeMillis();
        probleme.parcoursLargeur() ; 
        System.out.println("Durée : " + (System.currentTimeMillis() - start) + " ms");
        //Parcours en Profondeur
        System.out.println("--- DFS ---");
        start = System.currentTimeMillis();
        probleme.parcoursProfondeur(); 
        System.out.println("Durée : " + (System.currentTimeMillis() - start) + " ms");
        //Parcours Le Meilleur D'Abord
        System.out.println("-- Best-First ---");
        start = System.currentTimeMillis();
        probleme.parcoursBestFirst();
        System.out.println("Durée : " + (System.currentTimeMillis() - start) + " ms");
        }
        finally {
           scanner.close();
       }
    }   
}

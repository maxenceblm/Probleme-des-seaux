import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.HashSet ;

public class Probleme {
    private int[] ci ; 
    private int[] fi ;
    private Etat current_etat; 


    public Probleme (int[] capacites ,int[] finaux) {
        this.ci = capacites ; 
        this.fi = finaux ;
        this.current_etat = new Etat(ci.length) ;
    }

    // Remplir le seau i 
    public Etat remplir(Etat current_etat, int i) {
        Etat nouvelEtat = new Etat(current_etat) ; 
        int litresAjoutes = this.ci[i] - current_etat.get_si(i) ; // Litres ajoutés
        nouvelEtat.set_si(i, this.ci[i]) ; // Remplir le seau i 
        nouvelEtat.ajouterLitres(litresAjoutes) ; 
        return nouvelEtat ;
    }
    // Transvaser i dans j 
    public Etat transvaser(Etat curent_etat , int i , int j ) {
        Etat nouvelEtat = new Etat(curent_etat) ;
        
        int contenu_i = curent_etat.get_si(i) ; // Contenu du seau i
        int espace_j = this.ci[j] - curent_etat.get_si(j) ; // Espace dispo dans j
        
        // Quantité à transvaser = min(contenu de i, espace dans j)
        int quantite = Math.min(contenu_i, espace_j) ;
        
        nouvelEtat.set_si(i, contenu_i - quantite) ; // On retire du seau i
        nouvelEtat.set_si(j, curent_etat.get_si(j) + quantite) ; // On ajoute au seau j
        
        return nouvelEtat ; 
    }
    // Vider un seau
    public Etat vider(Etat current_etat ,int i ) {

        Etat nouvelEtat = new Etat(current_etat) ; 
        nouvelEtat.set_si(i, 0);
        return nouvelEtat ; 
    }
    // Test de finalité pour un état
    public boolean estfinal(Etat current_etat) {
        for (int i =0 ; i < current_etat.getNombreSeaux(); i++) {
            if (fi[i] != current_etat.get_si(i)) {  
                return false ;  
            }
        }
        return true ; 
    }
    // Calcul des successeurs possibles à partir d'un état 
    public List<Etat> successeurs(Etat current_etat) {
        Open open = new Open() ;  
        for (int i=0 ; i < current_etat.getNombreSeaux() ; i++) {
            Etat vide = vider(current_etat, i);
            // Cas dans lequel on vide un seau
            if (vide.equals(current_etat) == false)  {
                open.ajoute_etat(vide);
            }
            // Cas dans lequel on remplit un seau
            Etat rempli = remplir(current_etat, i);
            if (rempli.equals(current_etat) == false) {
                open.ajoute_etat(rempli);
            }
            // Cas ou on transvase d'un i à un j 
            for (int j=0 ; j < current_etat.getNombreSeaux(); j++) {
                if (i != j) {
                    Etat transv = transvaser(current_etat, i, j);
                    if (transv.equals(current_etat) == false) {
                        open.ajoute_etat(transv);
                    }
                }
            }
        }
        return open.get_etats(); 
        
    }

    // getter
    public Etat getCurent_etat() {
        return current_etat ; 
    }
    //BFS
    public Etat parcoursLargeur() {
        Open open = new Open();
        HashSet<Etat> close = new HashSet<>();
        open.ajoute_etat(current_etat);
        close.add(current_etat);
        while (open.get_etats().isEmpty() == false) {
            Etat courant = open.retirerPremier();
            if (estfinal(courant)) {
                System.out.println("Solution trouvée, "+ close.size()+ " états explorés pour un parcours en largeur") ; 
                return courant; // Solution trouvée
            }
            for (Etat succ : successeurs(courant)) {
                if (close.contains(succ) == false) {
                    open.ajoute_etat(succ);
                    close.add(succ);
                }
            }
        }
        System.out.println("Pas de Solution trouvée ici") ; 
        return null; // Pas de solution

    }
    
    public Etat parcoursProfondeur() {
        Open open = new Open() ; 
        HashSet<Etat> close = new HashSet<>();
        open.ajoute_etat(current_etat);
        close.add(current_etat);
        while (open.get_etats().isEmpty() == false) {
            Etat courant = open.retirerDernier();
            if (estfinal(courant)) {
                System.out.println("Solutions trouvée, "+close.size()+ " états explorés pour un parcours en profondeur") ; 
                return courant;
            }
            for (Etat succ : successeurs(courant)) {
                if (close.contains(succ) == false) {
                    open.ajoute_etat(succ);
                    close.add(succ);
                }
            }
        }
        System.out.println("Pas de Solution trouvée ici") ; 
        return null; // Pas de solution

    }

    // Heuristique améliorée : somme des diff + nombre de seaux incorrects
    public int heuristique(Etat etat) {
        int sum = 0;
        int actions = 0;
        for (int i = 0; i < etat.getNombreSeaux(); i++) { // somme des diff par rapport à leur valeur finale
            int diff = Math.abs(fi[i] - etat.get_si(i)); // diff absolu 
            sum += diff; 
            if (diff != 0) { // Si la diff non nulle : +1 seau incorrect
                 actions++;
            }
        }
        return sum + 2 * actions;
    }

    // Heuristique2 : litres remplis depuis le début 
    public int heuristique2(Etat etat) {
        return etat.getLitresRemplis() ;
    }

    // Heuristique A* : g(n) + h(n) = litres remplis + estimation restante
    public int heuristiqueAStar(Etat etat) {
        int g = etat.getLitresRemplis() ; // Coût réel
        int h = 0 ;
        for (int i = 0; i < etat.getNombreSeaux(); i++) {
            h += Math.abs(fi[i] - etat.get_si(i)) ; // Estimation
        }
        return g + h ;
    }

    
    public Etat parcoursBestFirst() {
    PriorityQueue<Etat> open = new PriorityQueue<>(Comparator.comparingInt(this::heuristique)); //Tri en comparant l'heuristique 1
    HashSet<Etat> close = new HashSet<>();
    open.add(current_etat);
    close.add(current_etat);
    while (open.isEmpty() == false) {
        Etat courant = open.poll(); 
        if (estfinal(courant)) {
            System.out.println("Solution trouvée ,  " + close.size() + " états explorés pour un parcours Meilleur d'Abord");
            return courant;
        }
        for (Etat succ : successeurs(courant)) {
            if (close.contains(succ) == false ) { // Si le successeur n'a pas déjà été ajouté à Close
                open.add(succ);
                close.add(succ);
            }
        }
    }
     System.out.println("Pas de Solution trouvée ici") ; 
        return null; // Pas de solution

}
}
import java.util.ArrayList;
import java.util.List;

public class Open {
    
    List<Etat> etats ; 

    public Open() {
        this.etats = new ArrayList<>() ; 
    }
    
    public void ajoute_etat(Etat etat) {

        if (etats.contains(etat) == false) {
            etats.add(etat) ; 
        }
    }

    public List<Etat> get_etats () {
        return etats ; 
    }
    
    public Etat retirerPremier() {
        if (etats.isEmpty()) {
            return null;
        }
        return etats.removeFirst(); 
    }

    public Etat retirerDernier() {
        if (etats.isEmpty()) {
            return null ; 
        }
        return etats.removeLast();
    }
}

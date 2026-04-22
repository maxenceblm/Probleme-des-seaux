import java.util.Arrays;

public class Etat {
    private int[] s ;
    private int litresRemplis ; // Cumul des litres versés depuis le début

    public Etat(int n) {
        this.s = new int[n] ; //Initialisation des n seaux 
        this.litresRemplis = 0 ;
    }

    // Constructeur par copie d'un tableau
    public Etat(int[] tableau) {
        this.s = tableau.clone() ;
        this.litresRemplis = 0 ;
    }

    // Constructeur par copie d'un état
    public Etat(Etat autre) {
        this.s = autre.s.clone() ;
        this.litresRemplis = autre.litresRemplis ;
    }

    public int getLitresRemplis() {
        return litresRemplis ;
    }

    public void ajouterLitres(int litres) {
        this.litresRemplis += litres ;
    }

    public int get_si(int i) {
        return s[i] ; 
    }

    public void set_si(int i, int valeur) {
        this.s[i] = valeur ;
    }

    public int getNombreSeaux() {
        return s.length ;
    }

    public int[] getTableau() {
        return s.clone() ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
            if (i < s.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Etat autre = (Etat) obj;
        if (s.length != autre.s.length) return false;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != autre.s[i]) return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(s) ; 
    }
}

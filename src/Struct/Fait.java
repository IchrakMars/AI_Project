package Struct;

import java.util.ArrayList;

/**
 * @author Ichrak
 *
 */
public class Fait {

    String name;
    ArrayList<String> composant = new ArrayList<String>();

    public Fait(String nom, ArrayList<String> par) {
        this.name = nom;
        this.composant = par;
    }

    Fait() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComposant(ArrayList<String> composant) {
        this.composant = composant;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getComposant() {
        return composant;
    }

   /* public void affiche(String nom, ArrayList<String> param) {
    	String s = nom + "(";
        for (int i = 0; i < param.size(); i++) {
            s = s + param.get(i) + ",";
        }
        int pos = s.lastIndexOf(",");
        s = s.substring(0, pos);
        s = s + ")";
        System.out.println(s);

    }*/

}

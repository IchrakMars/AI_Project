package Algorithmes;

import Frames.Accueil;
import Struct.BR;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ichrak
 *
 */
public class Map {

    Accueil a;
    Moteur mi;
    String BR;
    List<String> regles;
    
    /*public String getBR() {
        return BR;
    }*/

    public Map() {

       
        this.a = new Accueil(this);
        this.mi = new Moteur(a);
        BR br = new BR();
        regles = new ArrayList<String>();
        regles = br.GetRegles(BR);
        a.setVisible(true);
    }

    public void ImporterBR() {
    	 this.BR = "C:\\Users\\Ichrak\\Documents\\Workspace\\ProjetIA\\BR.txt";
    }

    public void RsltAffichageBR() {
      for(int i=0;i<regles.size();i++)
            a.Ecrire(regles.get(i));
    }

    public void RechercheHeureustique(String but, String etat) {
         this.mi.RechercheHeureustique(but, BR, etat);
         //this.mi.Ebut, BR, etat);
    }

    public void RechercheIterative(String but, String etat) {
        this.mi.RechercheIterative(but, BR, etat);
       // this.mi.ExeRechercheIterative(but, BR, etat);
    }

}

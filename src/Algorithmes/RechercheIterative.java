
package Algorithmes;

import Unification.Unification;
import Struct.BR;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ichrak
 *
 */
public class RechercheIterative implements IntAlgo, IntRI {

    String but;
    String pathR;
    String etaCourant;
    
    public RechercheIterative() {} 
    public RechercheIterative(String but, String pathR, String etaCourant) {
        this.but=but;
        this.pathR=pathR;
        this.etaCourant=etaCourant;
    }
/*
    algo zeyed mawjoud deja fl MOteur 
    public boolean ExecuterRechercheIterative(String but, String pathR, String etaCourant) {
       // this.but = but;
        boolean res = false;
        BR br = new BR();
        List<String> regles = new ArrayList<String>();
        regles = br.GetRegles(pathR);
        int niveau = 0;
        //liste contenant les noeuds a examiner 
        List<String> listenoeud = new ArrayList<String>();
        listenoeud.add(etaCourant);
        List<String> liste = new ArrayList<String>();
        int i = 0;
        List<String> noefExamines = new ArrayList<String>();
        while ((!listenoeud.isEmpty())) {

            for (int m = 0; m < listenoeud.size(); m++) {

                String etat = listenoeud.get(i);
                noefExamines.add(etat);
                System.out.println("etat:" + etat);
                listenoeud.remove(i);
                String s = br.RegleDeclanchable(pathR, etat);
                liste = br.ListeModif(s);
                for (int k = 0; k < liste.size(); k++) {
                    System.out.println(liste.get(k) + "\n");
                    String indregle = liste.get(k).substring(0, liste.get(k).trim().indexOf(":"));
                    System.out.println(indregle);
                    int ind = Integer.parseInt(indregle.trim()) - 1;
                    System.out.print(ind);
                    String unif = liste.get(k).trim().substring(liste.get(k).indexOf(":") + 1);
                    System.out.println("unif:" + unif + "\n");
                    String regle = regles.get(ind);
                    System.out.println(regle + "\n");
                    String resConc = br.ReturnResDeclancheRegle(unif, regle);
                    String con = br.ReturnConclusion(resConc);
                    System.out.println("nouveau fait:" + con + "\n");
                    con = con.replaceAll(" ", "");
                    if ((!this.NoeudExist(con, listenoeud)) && (!this.NoeudExist(con, noefExamines))) {
                        listenoeud.add(con);
                    }
                }
            }
            System.out.println("les noeuds enregistes:\n");
            for (int h = 0; h < listenoeud.size(); h++) {
                System.out.println(listenoeud.get(h));
            }

            System.out.println("les noeuds examinees\n");
            for (int g = 0; g < noefExamines.size(); g++) {
                System.out.println(noefExamines.get(g));
            }

            if (!this.ExisteBut(but, listenoeud)) {
                niveau++;
            } else if (this.ExisteBut(but, listenoeud)) {
                res = true;
                System.out.println("but atteint :)\n");
                break;
            }

            listenoeud = listenoeud;
        }

        if (listenoeud.isEmpty()) {
            System.out.println("But non atteint \n");
        }
        return res;

    }*/

    @Override
    public boolean NoeudExist(String noeud, List<String> liste) {
        
        boolean res = false;
        for (int i = 0; i < liste.size(); i++) {
        	System.out.println("BEG - TESTER SI BUT EXISTE");
        	 boolean b = this.ButOk(noeud, liste.get(i));
             if (b == true) {
                 res = true;
                break;
            }
             System.out.println("END - TESTER SI BUT EXISTE\n");
        }
        
        return res;
    }

    @Override
    public boolean ButOk(String but, String col) {
        boolean res = false;
        BR br = new BR();
        Unification t = new Unification();

        List<String> liste = new ArrayList<String>();
        liste = br.GetListParametresPredicat(but);
        System.out.println(liste);

        List<String> liste2 = new ArrayList<String>();
        liste2 = br.GetListParametresPredicat(col);
        System.out.println(liste2);
        String unif = t.unifier1(liste, liste2);
        System.out.println("Unification = " + unif + "\n");
        if (!unif.contains("echec")) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean ExisteBut(String but, List<String> liste) {
        boolean res = false;
        for (int i = 0; i < liste.size(); i++) {
            boolean b = this.ButOk(but, liste.get(i));
            if (b == true) {
                res = true;
                break;
            }
        }

        return res;
    }
}

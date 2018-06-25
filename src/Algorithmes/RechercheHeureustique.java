
package Algorithmes;

import Unification.Unification;
import Struct.BR;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ichrak
 *
 */
public class RechercheHeureustique implements IntAlgo, IntRH {

    String but;

    /*public boolean RechercheHeureustique(String but, String pathR, String etaCourant) {
        this.but = but;
        boolean rslt = false;
        BR br = new BR();
        List<String> regles = new ArrayList<String>();
        regles = br.GetRegles(pathR);
        String noeud1 = this.AssociateH(etaCourant);
        List<String> listeaExaminer = new ArrayList<String>();
        List<String> listedejaexaminer = new ArrayList<String>();
        listeaExaminer.add(noeud1);
        
        System.out.println("Noeud 1"+noeud1);
        System.out.println("Liste a examiner"+listeaExaminer);
        
        while (!listeaExaminer.isEmpty()) {

            String etat = this.ReturnNoeudaTester(listeaExaminer);
            System.out.println("neu a examiner :" + etat + "\n");
            String noeudAenlever = this.AssociateH(etat);
            System.out.println("le neud à enlever:" + noeudAenlever + "\n");
            listeaExaminer.remove(noeudAenlever);
            listedejaexaminer.add(noeudAenlever);
            System.out.println(listeaExaminer + "\n");
            System.out.println(listedejaexaminer + "\n");

            String s = br.RegleDeclanchable(pathR, etat);
            List<String> liste = br.ListeModif(s);
            System.out.println("liste de unif\n");
            System.out.println(liste + "\n");
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
                System.out.println(resConc);
                String con = br.ReturnConclusion(resConc);

                con = con.replaceAll(" ", "");
                System.out.println("nouveau fait:" + con + "\n");
                String nn = this.AssociateH(con);
                System.out.println("le noeus with h:" + nn + "\n");
                if ((!this.NoeudExist(nn, listeaExaminer)) && (!this.NoeudExist(nn, listedejaexaminer))) {
                    listeaExaminer.add(nn);
                }

            }

            System.out.println("les noeuds enregistées:\n");
            System.out.println(listeaExaminer);
            System.out.println("les neuds examinées\n");
            System.out.println(listedejaexaminer);
            System.out.println("---------------\n");
            if ((this.NoeudExist(but, listeaExaminer)) || (this.NoeudExist(but, listedejaexaminer))) {
                System.out.println("But atteind\n");
                rslt = true;
                break;
            } else {
                System.out.println("But nn  atteind\n");
                rslt = false;
            }
            listeaExaminer = listeaExaminer;
        }

        if (listeaExaminer.isEmpty()) {
            System.out.println("But nn atteind\n");
        }
        return rslt;
    }*/

    @Override
    public String AssociateH(String noeud) {
        String res = null;
        BR br = new BR();
        int h;
        //System.out.println("CALCUL H BEG");
        List<String> param = br.GetListParametresPredicat(noeud);
        if (Integer.parseInt(param.get(0).trim()) == 2) {
            h = 0;
        } else if (Integer.parseInt(param.get(0).trim()) + Integer.parseInt(param.get(1).trim()) < 2) {
            h = 7;
        } else if (Integer.parseInt(param.get(1).trim()) > 2) {
            h = 3;
        } else {
            h = 1;
        }
        res = noeud + ":" + String.valueOf(h);
        //System.out.println(res);
        //System.out.println("CALCUL H END");
        return res;
    }

    @Override
    public boolean NoeudExist(String noeud, List<String> liste) {
        
        boolean res = false;
        for (int i = 0; i < liste.size(); i++) {
        	System.out.println("BEG - TESTER SI NOEUD EXISTE");
        	 boolean b = this.ButOk(noeud, liste.get(i));
             //System.out.println("but---------\n");
             //a.Ecrire("Le but: " + b);
             //System.out.println(b);
             if (b == true) {
                 //System.out.println("but existant\n");
                 res = true;
                break;
            }
             System.out.println("END - TESTER SI NOEUD EXISTE\n");
        }
        
        return res;
    }

    @Override
    public String ReturnNoeudaTester(List<String> listen) {
        String noeud = null;
        int i = 0;
        int pos = 0;
        int min;
        if (listen.size() == 1) {
            pos = 0;
        } else {
        	min =this.ExtaractH(listen.get(i)); pos=i; i++;
            while (i < listen.size()) {
                if ((this.ExtaractH(listen.get(i))) < min ) {
                    min =this.ExtaractH(listen.get(i)); 
                	pos = i;
                }
                i++;
            }
        }

        String n = listen.get(pos);
        noeud = n.substring(0, n.indexOf(":"));
        return noeud ;
    }

    @Override
    public int ExtaractH(String noeud) {
        int h = 0;
        String s = noeud.substring(noeud.indexOf(":")+1, noeud.length());
        h = Integer.parseInt(s);
        return h;
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
}

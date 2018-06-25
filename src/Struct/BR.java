package Struct;

import Unification.Unification;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ichrak
 *
 */
public class BR {

    List<String> Liste = new ArrayList<String>();

    //extraire le nom du struct2 d'une condition
    public String GetNomPredicat(String cond) {
        Struct2 s2 = new Struct2() ; 
        return s2.ExtractName(cond); 
    }

    //extraire les parametres d'un Struct2
    public ArrayList<String> GetListParametresPredicat(String cond) {
        ArrayList<String> liste = new ArrayList<String>();
        int pos = cond.indexOf("(");
        int pos1 = cond.indexOf(")");
        String param = cond.substring(pos + 1, pos1);

        String[] Splits1 = param.split(",");
        for (int i = 0; i < Splits1.length; i++) {
            liste.add(Splits1[i].trim());
        }
        return liste;
    }

    //extraire la liste des regle de la base de regle donnee en parametre
    public List<String> GetRegles(String path) {
        String chaine = "";
        List<String> liste=new ArrayList<String>();
        int i = 0;
        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                //  System.out.println(ligne);
                chaine += ligne + "\n";
                i++;
                liste.add(ligne);

            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return liste;
    }

    //extraire la conclusion d'une regle
    public Concl ExtractConclusion(String regle) {
        Concl c = new Concl();

        int ind = regle.indexOf("alors");
        String conc = regle.substring(ind + "alors".length() + 1);
        c.setName(this.GetNomPredicat(conc).trim());
        c.setComposant(this.GetListParametresPredicat(conc));
        return c;

    }

    public Struct2 ExtractStruct2(String regle) {
        Struct2 p = new Struct2();
        String pred = regle.substring(regle.indexOf("si") + 3, regle.indexOf(")") + 1);
        p.setNom(this.GetNomPredicat(pred).trim());
        //System.out.println("**"+p.nom);
        p.setParametres(this.GetListParametresPredicat(pred));
        //System.out.println("**"+p.parametres.get(0)+p.parametres.get(1));
        return p;
    }

    //extraire l'outil de comparaison d'une Struct1
    public String ExtractCompare(String prem) {
        String comp = "";
        if (prem.contains("<")) {
            comp = prem.substring(prem.indexOf("<"), prem.indexOf("<") + 1).trim();
            // System.out.println(comp);
        }

        else if (prem.contains(">")) {
            comp = prem.substring(prem.indexOf(">"), prem.indexOf(">") + 1).trim();
            //System.out.println(comp);
        }

        else if (prem.contains("=")) {
            comp = prem.substring(prem.indexOf("="), prem.indexOf("=") + 1).trim();
        }
        else if (prem.contains("<=")) {
            comp = prem.substring(prem.indexOf("<="), prem.indexOf("<=") + 2).trim();
        }
        else if (prem.contains(">=")) {
            comp = prem.substring(prem.indexOf(">="), prem.indexOf(">=") + 2).trim();
        }

        return comp;

    }

    //extraire le chiffre contenu dans une chaine de caractere
    public int ExtraireChiffre(String s) {
        Pattern pattern = Pattern.compile("\\d+((,|\\.)\\d+)?");
        Matcher matcher = pattern.matcher(s);
        String h = null;
        while (matcher.find()) {
            //System.out.println(matcher.group(0));
            h = matcher.group(0);

        }
        int res = Integer.parseInt(h);
        return res;
    }

    //extraire une Struc1
    public Struc1 ExtrairePrem(String cond) {
        Struc1 p = new Struc1();

        String comp = this.ExtractCompare(cond).trim();
        //System.out.println(comp);
        p.setCompare(comp);
        String val = cond.substring(cond.indexOf(comp) + 1).trim();
        //System.out.println(val);

        int v = this.ExtraireChiffre(val);
        p.setValue(v);
        p.setConector("+");
        String att = cond.substring(0, cond.indexOf(comp)).trim();
        // System.out.println(att);
        List<String> at1 = new ArrayList<String>();
        if (!att.contains("+")) {
            at1.add(att.trim());
        } else {
            String[] Splits3 = att.split(" +");
            for (int h = 0; h < Splits3.length; h++) {
                if (!Splits3[h].trim().contains("+")) {
                    at1.add(Splits3[h].trim());
                }
            }
        }

        p.setAttribut(at1);
        return p;

    }
    
    //extraire la liste des Struc1 d'une regle
    public List<Struc1> ExtractPremise(String regle) {
        List<Struc1> liste = new ArrayList<Struc1>();

        Struc1 p = new Struc1();
        String s = regle.substring(regle.indexOf(")"), regle.indexOf("alors")).trim();
        if (s.length() > 1) {
            // System.out.println(s);
            String s1 = s.substring(s.indexOf("?")).trim();
            // System.out.println(s1);
            String[] Splits1 = s1.split("et");
            for (int i = 0; i < Splits1.length; i++) {
                liste.add(this.ExtrairePrem(Splits1[i].trim()));
            }
        } else {
            liste.add(null);
        }

        return liste;
    }

    //renvoi un objet de type regle
    public Regle ExtractRegle(String regle) {
        Regle r = new Regle();
        List<Struc1> listeP = new ArrayList<Struc1>();
        Concl conc = new Concl();
        Struct2 pred = new Struct2();
        listeP = this.ExtractPremise(regle);
        conc = this.ExtractConclusion(regle);
        pred = this.ExtractStruct2(regle);
        r.setConc(conc);
        r.setStruc2(pred);
        r.setStruc1(listeP);

        return r;
    }

    //extraire l'attribut d'une chaine exple ?x
    public String ExtractX(String p) {
        String res = "";
        if (p.contains("/")) {
            res = p.substring(0, p.indexOf("/")).trim();
        }
        return res;
    }

    // extraire la valeur attribuee a  un attribut apres unification
    public int ExtactVal(String p) {
        int val = 0;
        if (p.contains("/")) {
            String res = p.substring(p.indexOf("/") + 1).trim();
            val = Integer.parseInt(res);
        }
        return val;
    }

    //avoir la liste des attributs sur lesquels s'effectue l'unification
    public List<String> ListAttUnif(String unis) {
        List<String> att = new ArrayList<String>();
        if (unis.contains("*")) {
            String[] Splits1 = unis.split(" * ");

            for (int i = 0; i < Splits1.length; i++) {  
                if (Splits1[i].length() >= 4) {
                    String at = this.ExtractX(Splits1[i]).trim();
                    att.add(at);

                }

            }

        } else {
            att.add(this.ExtractX(unis).trim());
            int va = this.ExtactVal(unis);
        }

        return att;
    }

    //avoir la liste des valeurs attribuees aux attributs apres unification
    public List<String> ListValUnif(String unis) {
        List<String> val = new ArrayList<String>();
        if (unis.contains("*")) {//System.out.println("ok\n");
            String[] Splits1 = unis.split(" * ");

            // System.out.print(Splits1.length);
            // for(int h=0;h<Splits1.length;h++)
            for (int i = 0; i < Splits1.length; i++) {   //if(!Splits1[i].isEmpty())
                //System.out.print(Splits1[i].length());
                if (Splits1[i].length() >= 4) {//System.out.println(Splits1[i]+"\n");
                    String at = this.ExtractX(Splits1[i]).trim();
                    // System.out.println("X:"+at+"\n");
                    int va = this.ExtactVal(Splits1[i]);
                    String attt = String.valueOf(va).trim();
                    // System.out.println("val: "+String.valueOf(va)+"\n");
                    //att.add(at);
                    val.add(attt);

                }

            }

        } else {//System.out.println(" not ok\n");
            // att.add(this.ExtractX(unis).trim());
            int va = this.ExtactVal(unis);
            val.add(String.valueOf(va).trim());
        }

        return val;

    }

    //tester si une Struc1 est ok
    public boolean PremiseOk(Struc1 p, List<String> att, List<String> val) {
        boolean res = false;
//        System.out.println(p.att);
        if (p.getAttribut().size() == 1) {
            String at1 = p.getAttribut().get(0).trim();
//            System.out.println(at1);

            /*String compar = p.getCompare();
            int value = p.getValue();*/
//            System.out.println(at1 + compar + String.valueOf(value));
            for (int i = 0; i < att.size(); i++) {
                if (at1.trim().equals(att.get(i).trim())) {
                	//System.out.println("the same\n");
                    String eq = val.get(i).trim() + p.compare + String.valueOf(p.value);
                    System.out.println("Tester Condition -- "+eq);
                    int valeur = Integer.parseInt(val.get(i));
                    if ((p.compare.trim().equals("<")) && (valeur < p.value)) {
                        res = true;
                    }
                    if ((p.compare.equals(">")) && (valeur > p.value)) {
                        res = true;
                    }
                    if ((p.compare.equals("=")) && (valeur == p.value)) {
                        res = true;
                    }
                    if ((p.compare.equals("<=")) && (valeur <= p.value)) {
                        res = true;
                    }
                    if ((p.compare.equals(">=")) && (valeur >= p.value)) {
                        res = true;
                    }

                }
            }

        } else {
            //System.out.println("plus que val \n");
            String eq = "";
            int som = 0;
            for (int k = 0; k < att.size(); k++) {
                if (att.get(k).trim().equals(p.attribut.get(k).trim())) {
                    eq = eq + "+" + att.get(k).trim();
                    int vall = Integer.parseInt(val.get(k));
                    som = som + vall;

                }
            }
            eq = eq.substring(1);
            System.out.println("Tester Somme condition -- "+som+p.compare+p.value);
            if ((p.compare.trim().equals("<")) && (som < p.value)) {
                res = true;
            }
            if ((p.compare.equals(">")) && (som > p.value)) {
                res = true;
            }
            if ((p.compare.equals("=")) && (som == p.value)) {
                res = true;
            }
            if ((p.compare.equals("<=")) && (som <= p.value)) {
                res = true;
            }
            if ((p.compare.equals(">=")) && (som >= p.value)) {
                res = true;
            }
        }
        return res;
    }
 
    
    //Generer les regles applicables 
    public String RegleDeclanchable(String pathR, String etatcourant) {
        String res = "";
        List<String> liste = this.GetRegles(pathR);

        //System.out.print("Nb de regles = "+liste.size()+"\n");
        
        for (int i = 0; i < liste.size(); i++) {
            String regle = liste.get(i);
              System.out.println("------------R"+String.valueOf(i)+"------------");
            String res1 = this.generer(pathR, etatcourant, regle);
//            System.out.println("RES1" + res1 + "\n");
            if (res1.contains("*")) {
                int h = i + 1;
                res = res + "R" + String.valueOf(h) + " " + res1 + ",";
            }
            if (res1=="") System.out.println("------------Resultat de R"+String.valueOf(i)+": Non applicable------------\n");
            else System.out.println("------------Resultat de R"+String.valueOf(i)+":"+res1+"------------\n");
        }
        res = res.substring(0, res.length() - 1);
        res = "{ " + res + " }";
        System.out.println("Toutes les ReglDec de '"+etatcourant+"'= "+res);
        return res;
    }

    // pour tester si une regle est declanchable
   /* public String genereOperateurApplicable(String pathR, String etatcourant, String regle) {
        String res = "";
        List<String> expr2 = new ArrayList<String>();
        expr2 = this.GetListParametresPredicat(etatcourant);

//        System.out.println(regle + "\n");

        Struct2 pred = this.ExtractStruct2(regle);
        pred.AfficheStruct2(pred);
        Unification t = new Unification();
        List<String> expr1 = pred.getParametres();
        String unif = t.unifier1(expr1, expr2);
//        System.out.println(unif);
//        System.out.print(unif.length());
//        System.out.print("\n");
//        if(unif.contains("NIL"))
//        {System.out.println("contenant NIL\n");}
        boolean ok = true;
        if ((unif.contains("/")) && (this.ExtractPremise(regle).size() > 0)) {
            List<String> att = new ArrayList<String>();
            List<String> val = new ArrayList<String>();
            att = this.ListAttUnif(unif);
            val = this.ListValUnif(unif);

            List<Struc1> listeP = new ArrayList<Struc1>();
            listeP = this.ExtractPremise(regle);

            if (listeP.size() > 0) {
                for (int l = 0; l < listeP.size(); l++) {
                    Struc1 p = new Struc1();

                    p = listeP.get(l);
                    // p.AffichePremise(p);
                    boolean b = this.PremiseOk(p, att, val);
                    ok = ok && b;
                }
            }

        } else if (unif.contains("echec")) {
            ok = false;
        }

//       
        if (ok) {
            res = res + "(" + unif + ")";
        }

        return res;

    }*/

    public String generer(String pathR, String etatcourant, String regle) {
        String res = "";
        boolean ok = true;
        List<String> expr2 = new ArrayList<String>();
        expr2 = this.GetListParametresPredicat(etatcourant);
        Struct2 pred = this.ExtractStruct2(regle);
        //pred.AfficheStruct2(pred);
        Unification t = new Unification();
        List<String> expr1 = pred.getParametres();

        String unif = t.unifier1(expr1, expr2);
        List<Struc1> pp = new ArrayList<Struc1>();
        pp = this.ExtractPremise(regle);

        if ((unif.contains("NIL")) && (!unif.contains("/"))) {
            System.out.println("==> Contient que NIL\n");
            ok = true;
        } else if (unif.contains("echec")) {
            System.out.println("==> Echec\n");
            ok = false;
        } else if ((unif.contains("/")) && (!unif.contains("NIL"))) {
            System.out.println("==> Unifies\n");
            List<String> att = new ArrayList<String>();
            List<String> val = new ArrayList<String>();
            att = this.ListAttUnif(unif);
            val = this.ListValUnif(unif);

            List<Struc1> listeP = new ArrayList<Struc1>();
            listeP = this.ExtractPremise(regle);

            if (listeP.size() > 0) {
                for (int l = 0; l < listeP.size(); l++) {
                    Struc1 p = new Struc1();

                    p = listeP.get(l);
                    p.AfficheStruct1(p);
                    boolean b = this.PremiseOk(p, att, val);
                    ok = ok && b;
                }
            }

        } else if ((unif.contains("/")) && (unif.contains("NIL"))) {
            ok = true;
        }

        if (ok) {
            res = res + "(" + unif + ")";
        }
        return res;
    }

    public boolean ContainValeur(List<String> liste1) {
        List<String> liste = liste1;

        boolean res = false;
        for (int i = 0; i < liste1.size(); i++) {
            if (!liste1.get(i).contains("?")) {
                res = true;
                break;
            }
        }

        return res;

    }

    public boolean ChiffreOk(String s) {
        boolean res = false;
        res = s.matches("[^0-9]*[0-9]+.*");
        return res;
    }

    boolean PredicatOK(Struct2 p, List<String> test) {
        boolean res = true;

        for (int i = 0; i < test.size(); i++) {
            if ((this.ChiffreOk(p.parametres.get(i).trim())) && (this.ChiffreOk(test.get(i).trim()))) {
                if (!p.parametres.get(i).trim().equals(test.get(i).trim())) {
                    res = false;
                    break;
                }
            }

        }
        return res;
    }

    //retourner le resultat de declanchement d'une regle, remplacer ?x et ?y par leur valeurs 
    public String ReturnResDeclancheRegle(String unif, String regle) {
        String res = "";
        List<String> att = this.ListAttUnif(unif);
        List<String> val = this.ListValUnif(unif);
        String conc = regle.substring(regle.indexOf("alors") + "alors".length() + 1);
//        System.out.println(conc);
        if(conc.contains("?"))
        {
        for (int i = 0; i < conc.length(); i++) {
            if (conc.charAt(i) == '?') {
//                System.out.println("contain ?\n");
                String s = conc.substring(conc.indexOf("?"), conc.indexOf("?") + 2);
               // System.out.println(s);
                for (int k = 0; k < att.size(); k++) {
                    if (att.get(k).equals(s)) {
                       // System.out.println(s + "= " + val.get(k));

                        res = conc.substring(0, conc.indexOf("?") - 1) + " " + val.get(k) + " " + conc.substring(conc.indexOf("?") + 2);
                        conc = conc.substring(0, conc.indexOf("?") - 1) + " " + val.get(k) + " " + conc.substring(conc.indexOf("?") + 2);
                        //System.out.println("res conclusion: " + res);
                    }
                }
            }
        }}
        else
        {res=conc;}
        return res;
    }
    
    //calculer lla valeur s'il existe des parentheses
    public int CalculOperationwithparent(String conc) {
        int rslt = 0;
       // System.out.print(conc);
        conc = conc.replaceAll("  ", " ");
       // System.out.println("\n");
        //System.out.println(conc + "\n");
        String[] Splits1 = conc.split(" ");

        List<String> maliste = new ArrayList<String>();
        //System.out.println("before change\n");

        for (int m = 0; m < Splits1.length; m++) {
            //System.out.println(Splits1[m].trim() + "\n");
        }

        int i = 0;
        while (i < Splits1.length) {
            // System.out.println(Splits1[i] + "\n");
            if ((Splits1[i].trim().equals("-")) && (Splits1[i + 1].trim().equals("("))) {
                //  System.out.println("moin\n");
                if (Splits1[i + 3].trim().equals("+")) {
                    Splits1[i + 3] = "-";
                } else {
                    Splits1[i + 3] = "+";
                }
            }
            i++;
        }
      //  System.out.println("after change\n");
//        for (int j = 0; j < Splits1.length; j++) {
//            System.out.println(Splits1[j].trim() + "\n");
//        }

        for (int h = 0; h < Splits1.length; h++) {
            maliste.add(Splits1[h]);
        }

       // System.out.println("contenue liste\n");
        for (int q = 0; q < maliste.size(); q++) {
            if ((maliste.get(q).trim().equals("(")) || (maliste.get(q).trim().equals(")"))) {
                maliste.remove(q);
            }
        }

//        for (int n = 0; n < maliste.size(); n++) {
//            System.out.println(maliste.get(n).trim() + "\n");
//        }

        List<String> val = new ArrayList<String>();
        List<String> op = new ArrayList<String>();
        for (int g = 0; g < maliste.size(); g++) {
            if (this.ChiffreOk(maliste.get(g).trim())) {
                val.add(maliste.get(g));
            } else {
                op.add(maliste.get(g));
            }

        }
    
        System.out.print("\n");
//        for(int z=0;z<op.size();z++)
//        {System.out.println("op:"+op.get(z));}
        //System.out.print(op.size());
        //System.out.print(op.get(0).length());
            for(int d=0;d<op.size();d++)
            {if(op.get(d).trim().length()==0)
            {op.remove(d);}}
         // System.out.print(op.size());  
        int s=Integer.valueOf(val.get(0));
        val.remove(0);
       // System.out.print(op.size());
        for(int t=0;t<op.size();t++)
        {
         if(op.get(t).trim().equals("+"))
             {s=s+Integer.valueOf(val.get(t));}
         else
             {s=s-Integer.valueOf(val.get(t));}
        
        }
  
        rslt=s;
       // System.out.print(rslt);
        return rslt;
    }

    //calcuer la valeur s'il n'xiste pas de parenthese
    
    public int CalculSansParenthese(String conc)
    {int res=0;
    
    conc=conc.replaceAll("  ", " ");
   // System.out.println(conc);
    String[] Splits1 = conc.split(" ");
    List<String> val=new ArrayList<String>();
    List<String> op=new ArrayList<String>();
    for(int i=0;i<Splits1.length;i++)
       {if(this.ChiffreOk(Splits1[i].trim()))
       {val.add(Splits1[i].trim()); }
       else
       {op.add(Splits1[i].trim());}
       
               }
       //System.out.println("size\n");
       //System.out.print(op.size());
       for(int f=0;f<op.size();f++)
       {if(op.get(f).trim().length()==0)
           op.remove(f);}
      // System.out.print(op.size());
       
       
       int s=Integer.valueOf(val.get(0));
       val.remove(0);
       for(int j=0;j<op.size();j++)
          {if(op.get(j).trim().equals("+"))
             {s=s+Integer.valueOf(val.get(j));}
          else
          {s=s-Integer.valueOf(val.get(j));}
          
          }
       
       res=s;
       //System.out.print(res);
    return res;}
    
    
    
    //retourner la valeur de la conclusion apres calcul des paramÃ©tres
    public String ReturnConclusion(String s) {
        String res = "";
        String nom=s.substring(0, s.indexOf("("));
//        System.out.println(nom+"\n");
        String s1 = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
//        System.out.println(s1);
        List<String> liste = new ArrayList<String>();
        String[] Splits1 = s1.split(",");
        for (int i = 0; i < Splits1.length; i++) {
            liste.add(Splits1[i]);
        }
//        for (int k = 0; k < liste.size(); k++) {
//            System.out.println(liste.get(k) + "\n");
//            
//        }
             
        for(int j=0;j<liste.size();j++)
        {if(liste.get(j).trim().contains("("))
            {int at1=this.CalculOperationwithparent(liste.get(j).trim());
            String va=String.valueOf(at1);
            liste.set(j, va);}
        else if((liste.get(j).trim().contains("+"))||(liste.get(j).trim().contains("-")))
             {int at2=this.CalculSansParenthese(liste.get(j).trim());
             String va2=String.valueOf(at2);
             liste.set(j, va2);
             }
        
                }
        
        String listeparam="";
        for(int m=0;m<liste.size();m++)
        {listeparam=listeparam+liste.get(m)+",";}
//        System.out.println("liste:"+listeparam+"\n");
        listeparam=listeparam.substring(0, listeparam.length()-1);
//        System.out.println("liste:"+listeparam+"\n");
        res=nom+"("+listeparam+")";
        return res;
    }
    
    
    
    //enregistrer les regles declanchable et l'unification dans une liste
    public List<String> ListeModif(String s)
    { List<String> maListe=new ArrayList<String>();
       String s1=s.substring(1, s.length()-1);
//       System.out.println(s1+"\n");
       String[] Splits1 = s1.split(",");
       for(int i=0;i<Splits1.length;i++)
            {
//                System.out.println(Splits1[i]+"\n");
            String reg=Splits1[i].substring(0, Splits1[i].indexOf("("));
//                System.out.println("regle:"+reg+"\n");
                String unif=Splits1[i].substring(Splits1[i].indexOf("(")+1, Splits1[i].indexOf(")")-1);
//                System.out.println("unif:"+unif+"\n");
                String num=reg.substring(reg.indexOf("R")+1);
                
                //System.out.println("num:"+num+"\n");
                String colone=num+":"+unif;
                //System.out.println("colone:"+colone+"\n");
                maListe.add(colone);
                       
            }
    
    
    return maListe;
    
    }
    
    
    

}

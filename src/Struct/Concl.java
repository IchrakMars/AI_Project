package Struct;


import java.util.ArrayList;

/**
 * @author Ichrak
 *
 */
class Concl {
    String name;
    ArrayList<String> composant=new ArrayList<String>();

    public String getName() {
        return name;
    }

    public ArrayList<String> getComposant() {
        return composant;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public void setComposant(ArrayList<String> parametres) {
        this.composant = parametres;
    }
    
    
    /*public String ExtraireName(String conc)
    {
    String nom="";
    int pos=conc.indexOf("(");
    nom=conc.substring(0, pos);
    return nom;}*/
    
    /*public ArrayList<String> ExtraireAttributConcl(String conc)
    {ArrayList<String> liste=new ArrayList<String>();
            int pos=conc.indexOf("(");
            int pos1=conc.indexOf(")");
            String param=conc.substring(pos+1, pos1);
            
           String[] Splits1=param.split(",");
           for(int i=0;i<Splits1.length;i++)
           {liste.add(Splits1[i]);}
            return liste;
    }*/
    
    
   /* public void AfficheConclusion(Concl c)
    { 
       String s=c.getName()+"(";
       for(int i=0;i<c.getComposant().size();i++)
       {
         s=s+c.getComposant().get(i)+",";
       }
       s=s.substring(0, s.lastIndexOf(","));
       System.out.println(s+")");
       
    
    }*/
    
    
}

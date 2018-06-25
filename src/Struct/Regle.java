package Struct;


import java.util.List;


/**
 * @author Ichrak
 *
 */
public class Regle {
    
    
    Struct2 struct2;
    List<Struc1> struc1;
    Concl conc;

    
    public void setConc(Concl conc) {
        this.conc = conc;
    }

    public Concl getConc() {
        return conc;
    }
    

    public void setStruc2(Struct2 struct2) {
        this.struct2 = struct2;
    }

    public void setStruc1(List<Struc1> struc1) {
        this.struc1 = struc1;
    }

    public Struct2 getStruc2() {
        return struct2;
    }

    public List<Struc1> getStruc1() {
        return struc1;
    }
    
    
    
    
    
}

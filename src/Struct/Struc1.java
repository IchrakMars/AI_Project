package Struct;

import java.util.List;

/**
 * @author Ichrak
 *
 */
class Struc1 {

    List<String> attribut;
    String conector;
    int value;
    String compare;

    public List<String> getAttribut() {
        return attribut;
    }

    public String getConector() {
        return conector;
    }

    public String getCompare() {
        return compare;
    }

    public void setAttribut(List<String> attribut) {
        this.attribut = attribut;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void AfficheStruct1(Struc1 p) {
    	//System.out.println("STRUCT 1 BEG");
        List<String> attribut = p.getAttribut();
        int val = p.getValue();
        String comp = p.getCompare();
        String s = " ";

        for (int i = 0; i < attribut.size(); i++) {
            s = s + attribut.get(i) + " + ";
        }
        s = s.substring(0, s.length() - 2);
        s = s + " " + comp + " " + String.valueOf(val);
        //System.out.println("premisse =" + s);
        //System.out.println("STRUCT 1 END");
    }
}

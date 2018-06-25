package Struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ichrak
 *
 */
class Struct2 {
	String nom;
	ArrayList<String> parametres = new ArrayList<String>();

	public String getNom() {
		return nom;
	}

	public ArrayList<String> getParametres() {
		return parametres;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setParametres(ArrayList<String> parametres) {
		this.parametres = parametres;
	}

	public String ExtractName(String cond) {
		String nom = "";
		int pos = cond.indexOf("(");
		nom = cond.substring(0, pos);
		return nom;
	}

	/*public ArrayList<String> ExtractAttribut(String cond) {
		ArrayList<String> liste = new ArrayList<String>();
		int pos = cond.indexOf("(");
		int pos1 = cond.indexOf(")");
		String param = cond.substring(pos + 1, pos1);

		String[] Splits1 = param.split(",");
		for (int i = 0; i < Splits1.length; i++) {
			liste.add(Splits1[i]);
		}
		return liste;
	}*/

	public void AfficheStruct2(Struct2 p) {
		System.out.println("BEG STRUCT2");
		String nom = p.getNom();
		List<String> param = p.getParametres();
		String s = "";
		for (int i = 0; i < param.size(); i++) {
			s = s + param.get(i) + ",";
		}
		s = s.substring(0, s.length() - 1);
		s = nom + "(" + s + ")";
		System.out.println(s);
		System.out.println("END STRUCT2");
	}

}

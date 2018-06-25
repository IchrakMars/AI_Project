package Unification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ichrak
 *
 */
public class Unification {

	public boolean estVariable(String V) {
		if (V.length() == 1) {
			return false;
		}
		if (V.charAt(0) == '?') {
			return true;
		}
		return false;
	}

	public boolean estAtome(List<String> expr1) {
		boolean res = false;

		if (expr1.size() == 1) {
			String A = expr1.get(0);
			if (estVariable(A) || A.length() == 1 || !A.contains(",")) {
				res = true;
			}
		}
		return res;
	}


	public String unifier(List<String> expr1, List<String> expr2) {
		if ((estAtome(expr1)) || (estAtome(expr2))) {
			return unifierAtomes(expr1, expr2);
		}

		String f1 = expr1.get(0);
		expr1.remove(0);
		List<String> t1 = expr1;
		String f2 = expr2.get(0);
		expr2.remove(0);
		List<String> t2 = expr2;

		List<String> e1 = new ArrayList<String>();
		e1.add(f1);
		List<String> e2 = new ArrayList<String>();
		e2.add(f2);
		String z1 = unifier(e1, e2);

		if (z1.equalsIgnoreCase("echec")) {
			return "echec";
		}

		return z1;
	}

	public List<String> changer(List<String> t1, String z1) {
		String[] chg = z1.trim().split("\\s+");
		List<String> b = new ArrayList<String>();
		for (int i = 0; i < chg.length; i++) {
			b.addAll(Arrays.asList(chg[i].split("/")));
		}
		if (!z1.equalsIgnoreCase("")) {
			for (int i = 0; i < t1.size(); i++) {
				// if(t1.get(i).equalsIgnoreCase(b[0]))
				for (int j = 0; j < b.size(); j += 2) {
					t1.set(i, t1.get(i).replaceAll("\\" + b.get(j), b.get(j + 1)));
				}
			}
		}
		return t1;
	}

	public String unifierAtomes(List<String> expr1, List<String> expr2) {
		String e1 = expr1.get(0);
		String e2 = expr2.get(0);
		// e1 et e2 sont identiques
		if (e1.equalsIgnoreCase(e2)) {
			return "NIL";
		}
		// e1 est une variable
		if (e1.charAt(0) == '?') {
			if (e2.contains(e1)) {
				return "echec";
			} else {
				return e1 + "/" + e2;
			}
		}
		// e2 est un variable
		if (e2.charAt(0) == '?') {
			return e2 + "/" + e1;
		}
		// e1 et e2 sont des fonctions
		if (e1.contains("(") && e2.contains("(")) {
			List<String> l1 = extractExpression(e1);
			List<String> l2 = extractExpression(e2);
			return unifier(l1, l2);
		}

		return "echec";

	}

	public List<String> extractExpression(String e) {
		List<String> liste = new ArrayList<String>();
		int ind1 = e.indexOf("(");
		int ind2 = e.indexOf(")");
		String e1 = e.substring(ind1 + 1, ind2);
		// System.out.println(e1);
		String[] Splits1 = e1.split(",");
		for (int i = 0; i < Splits1.length; i++) {
			liste.add(Splits1[i]);
		}
		return liste;
	}

	public String unifier1(List<String> expr1, List<String> expr2) {
		System.out.println("---- Unifier "+expr1+"___"+expr2+" ----");
		String res = " ";
		// Tester si l'une des expressions est un atome
		if ((estAtome(expr1)) || (estAtome(expr2))) {
			return unifierAtomes(expr1, expr2);
		}

		while (!expr1.isEmpty()) {
			// recuperer le premier element de la premiere expression
			String f1 = expr1.get(0);
			System.out.println("f1= " + f1 + "\n");
			// sauvegarder les termes non traites de la premiere expression
			expr1.remove(0);
			List<String> t1 = expr1;
			String t = "";
			for (int i = 0; i < t1.size(); i++) {
				t = t + t1.get(i) + " ";
			}
			// t=t.substring(0, t.lastIndexOf(","));
			System.out.println("T1= (" + t + ")\n");
            
			// recuperer le premier element de la deuxieme expression
			String f2 = expr2.get(0);
			System.out.println("f2= " + f2 + "\n");

			// sauvegarder les termes non traites de la deuxieme expression
			expr2.remove(0);
			List<String> t2 = expr2;
			String tt = "";
			for (int i = 0; i < t2.size(); i++) {
				tt = tt + t2.get(i) + " ";
			}

			// tt=tt.substring(0, tt.lastIndexOf(","));
			System.out.println("T2= (" + tt + ")\n");

			List<String> e1 = new ArrayList<String>();
			e1.add(f1);
			List<String> e2 = new ArrayList<String>();
			e2.add(f2);
            
			// unifier les elems des deux expressions
			String z1 = unifier(e1, e2);
			res = res + z1 + " * ";
			List<String> G1 = new ArrayList<String>();
			List<String> G2 = new ArrayList<String>();
			if (z1.contains("/")) {
				G1 = changer(t1, z1);
				G2 = changer(t2, z1);
			} else {
				G1 = t1;
				G2 = t2;
			}
			System.out.println("Z1= " + z1 + "\n");
			String g1 = "";
			String g2 = "";
			for (int i = 0; i < G1.size(); i++) {
				g1 = g1 + G1.get(i) + " ";
			}
			System.out.println("G1= (" + g1 + ")\n");
			for (int i = 0; i < G2.size(); i++) {
				g2 = g2 + G2.get(i) + " ";
			}
			System.out.println("G2= (" + g2 + ")\n");

			System.out.println("--------");

			// echec d'unification
			if (z1.equalsIgnoreCase("echec")) {
				res = "echec";
				return "echec";
			}

		}
		// return res;
		return res.substring(0, res.length() - 2);
	}

}

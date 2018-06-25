package Algorithmes;

import Unification.Unification;
import Frames.Accueil;
import Struct.BR;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ichrak
 *
 */
public class Moteur {

	RechercheHeureustique rh;
	RechercheIterative ri;
	Accueil a;

	public Moteur(Accueil a) {
		this.a = a;
	}

	/*
	 * public void ExeRechercheIterative(String but, String pathR, String
	 * etaCourant) { this.ri = new RechercheIterative(but, pathR, etaCourant); }
	 */

	// methode qui teste si un noeud existe deja dans une liste
	public boolean NoeudExist(String noeud, List<String> liste) {
		rh = new RechercheHeureustique();
		return rh.NoeudExist(noeud, liste);
	}

	// methode pour associer h à chaque noeud
	public String AssociateH(String noeud) {
		rh = new RechercheHeureustique();
		return rh.AssociateH(noeud);
	}

	// methode pour enlever la valeur de h
	public int ExtaractH(String noeud) {
		rh = new RechercheHeureustique();
		return rh.ExtaractH(noeud);
	}

	// retourne le noeud qui a h min
	public String ReturnNoeudaTester(List<String> listen) {
		rh = new RechercheHeureustique();
		return rh.ReturnNoeudaTester(listen);
	}

	// methode de recherche par algo A*
	public Boolean RechercheHeureustique(String but, String b, String etaCourant) {
		boolean trouve = false;
		boolean rslt = false;
		BR br = new BR();
		String but1 = this.AssociateH(but);
		List<String> regles = new ArrayList<String>();

		regles = br.GetRegles(b);
		// associer h au premier noeud
		String noeud1 = this.AssociateH(etaCourant);
		// liste contenant les noeuds a examiner
		List<String> listeaExaminer = new ArrayList<String>();
		// liste contenant les noeuds deja examines
		List<String> listedejaexaminer = new ArrayList<String>();
		// ajouter le premier neud Ã  la liste
		listeaExaminer.add(noeud1);
		// List<String> ListeNoeud = new ArrayList<String>();
		System.out.println("Noeud initial = " + noeud1);
		System.out.println("Liste a examiner = " + listeaExaminer);

		while (!listeaExaminer.isEmpty() && !trouve) {
			System.out.println("LISTNOEUD" + listeaExaminer);

			int size = listeaExaminer.size();
			for (int i = 0; i < size; i++) {
				// extraire etat a tester
				String etat = this.ReturnNoeudaTester(listeaExaminer);
				System.out.println("Noeud a examiner = " + etat);
				String noeudAenlever = this.AssociateH(etat);
				// System.out.println("Noeud a  enlever: " + noeudAenlever +
				// "\n");
				listeaExaminer.remove(noeudAenlever);
				System.out.println("Liste a examiner " + listeaExaminer);
				listedejaexaminer.add(noeudAenlever);
				System.out.println("Liste deja examinee " + listedejaexaminer);
				String s = br.RegleDeclanchable(b, etat);
				List<String> liste = br.ListeModif(s);
				System.out.println("Liste des unif à faire" + liste + "\n");
				for (int j = 0; j < liste.size(); j++) {
					// System.out.println(liste.get(j) + "\n");
					String unif = liste.get(j).substring(liste.get(j).indexOf(":") + 1);
					// System.out.println(unif + "\n");
					String indregle = liste.get(j).substring(0, liste.get(j).trim().indexOf(":"));
					// System.out.println(indregle);
					int ind = Integer.parseInt(indregle.trim());
					String regle = regles.get(ind - 1);
					System.out.println(regle);
					String resConc = br.ReturnResDeclancheRegle(unif, regle);
					// System.out.println(resConc);
					String con = br.ReturnConclusion(resConc);

					con = con.replaceAll(" ", "");
					System.out.println("Conclusion de R" + ind + "--Nouveau fait: " + con);
					String nn = this.AssociateH(con);
					System.out.println("Calcul de h = " + nn + "\n");

					if ((!this.NoeudExist(nn, listeaExaminer)) && (!this.NoeudExist(nn, listedejaexaminer))) {
						listeaExaminer.add(nn);
					}
					System.out.println("Liste a examiner" + listeaExaminer);

				}
				if (NoeudExist(but, listeaExaminer)) {
					trouve = true;
					break;
				}
			}
			//a.Ecrire("==> Noeud choisi =" + this.ReturnNoeudaTester(listeaExaminer));
			a.Ecrire("\n*******Noeuds testes*******");

			for (int i = 0; i < listedejaexaminer.size(); i++) {
				a.Ecrire(listedejaexaminer.get(i));
			}
			a.Ecrire("\n*******Noeuds sauvegardes*******");

			for (int i = 0; i < listeaExaminer.size(); i++) {
				a.Ecrire(listeaExaminer.get(i));
			}
			
			if ((this.NoeudExist(but, listeaExaminer)) || (this.NoeudExist(but, listedejaexaminer))) {
				// System.out.println("But atteint\n");
				a.Ecrire("\nBut atteint");
				rslt = true;
				break;
			}

		}
		if (listeaExaminer.isEmpty()) {
			System.out.println("But non atteint\n");
			a.Ecrire("\nBut non atteint");
		}
		return rslt;
	}

	/// algorithme de recherche profondeur iterative OK
	public boolean RechercheIterative(String but, String pathR, String etaCourant) {
		boolean res = false;
		boolean trouve = false;
		BR br = new BR();
		List<String> regles = new ArrayList<String>();
		regles = br.GetRegles(pathR);
		int niveau = 0;
		// liste contenant les noeuds a examiner
		List<String> listenoeud = new ArrayList<String>();
		listenoeud.add(etaCourant);
		List<String> liste = new ArrayList<String>();
		int i = 0;
		List<String> noefExamines = new ArrayList<String>();
		while ((!listenoeud.isEmpty()) && !trouve) {

			System.out.println("LISTNOEUD" + listenoeud);

			int size = listenoeud.size();
			for (int m = 0; m < size; m++) {
				String etat = listenoeud.get(i);
				noefExamines.add(etat);
				System.out.println("Noeud a examiner: " + etat);
				listenoeud.remove(i);
				String s = br.RegleDeclanchable(pathR, etat);
				liste = br.ListeModif(s);
				System.out.println("LIST" + liste);
				for (int k = 0; k < liste.size(); k++) {

					String indregle = liste.get(k).substring(0, liste.get(k).trim().indexOf(":"));
					// System.out.println(indregle);
					int ind = Integer.parseInt(indregle.trim()) - 1;
					// System.out.print(ind);
					String unif = liste.get(k).trim().substring(liste.get(k).indexOf(":") + 1);
					// System.out.println("unif:" + unif + "\n");
					String regle = regles.get(ind);
					System.out.println(regle);
					String resConc = br.ReturnResDeclancheRegle(unif, regle);
					String con = br.ReturnConclusion(resConc);
					System.out.println("Conclusion de R" + ind + "--Nouveau fait: " + con);
					con = con.replaceAll(" ", "");
					// if (con.equalsIgnoreCase(but))
					// trouve = true;
					if ((!this.NoeudExist(con, listenoeud)) && (!this.NoeudExist(con, noefExamines))) {
						listenoeud.add(con);
					}

				}
				if (NoeudExist(but, listenoeud)) {
					trouve = true;
					break;
				}
			}
			a.Ecrire("\nNiveau" + niveau + "*******Noeuds testes*******");
			a.Ecrire();
			niveau++;
			for (int g = 0; g < noefExamines.size(); g++) {
				// System.out.println(noefExamines.get(g));
				a.Ecrire(noefExamines.get(g));

			}

			a.Ecrire("\n*******Noeuds sauvegardes*******");
			for (int h = 0; h < listenoeud.size(); h++) {
				// System.out.println(listenoeud.get(h));
				a.Ecrire(listenoeud.get(h));
			}

			if (!this.ExisteBut(but, listenoeud)) {

			} else if (this.ExisteBut(but, listenoeud)) {
				res = true;
				System.out.println("But atteint !");
				a.Ecrire("\nBut atteint dans le niveau " + niveau);
				break;
			}
			/*
			 * if (trouve=true) { System.out.println("but atteint !"); a.Ecrire(
			 * "\nBut atteint !"); break; }
			 */
		}

		if (listenoeud.isEmpty()) {
			System.out.println("\n But non atteint \n");
			a.Ecrire("\n But non atteint");
		}
		return res;

	}

	// tester si le but existe dans la liste
	public boolean ExisteBut(String but, List<String> liste) {
		ri = new RechercheIterative();
		return ri.ExisteBut(but, liste);
	}
}

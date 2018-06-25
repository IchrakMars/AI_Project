package Struct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Ichrak
 *
 */
public class BF {

    ArrayList<Fait> ListeFait = new ArrayList<Fait>();

    public String GetNomFait(String fait) {
        int pos = fait.indexOf("(");
        String nom = fait.substring(0, pos);
        return nom;
    }

    public ArrayList<String> GetListParametres(String fait) {
        ArrayList<String> liste = new ArrayList<String>();
        int pos = fait.indexOf("(");
        int pos1 = fait.indexOf(")");
        String param = fait.substring(pos + 1, pos1);

        String[] Splits1 = param.split(",");
        for (int i = 0; i < Splits1.length; i++) {
            liste.add(Splits1[i]);
        }
        return liste;
    }

    public ArrayList<Fait> GetListFait(String path) {
        ArrayList<Fait> maliste = new ArrayList<Fait>();
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                //  System.out.println(ligne);
                String nom = this.GetNomFait(ligne);
                ArrayList<String> liste = this.GetListParametres(ligne);
                Fait f = new Fait(nom, liste);
                maliste.add(f);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return maliste;
    }

  /*  public void addFait(String path, String f) throws IOException {

        FileWriter writer = null;
        String texte = f;
        try {
            writer = new FileWriter("D:/Fait1.txt", true);

            writer.write(texte, 0, texte.length());
            writer.write("\r\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }*/

   /* public List< String> GetFait(String path) {

        ArrayList<String> maliste = new ArrayList<String>();
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                //System.out.println(ligne);
                maliste.add(ligne);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return maliste;

    }

    public void afficheFait(Fait f) {
        String parm = "" ;
        for (int i = 0; i < f.composant.size(); i++) {
            parm = parm + f.composant.get(i) + ",";
        }
        parm = parm.substring(0, parm.length() - 1);
        System.out.println("Fait:" + f.getName() + "(" + parm + ")\n");

    }*/
}

package Frames;

import Algorithmes.Map;
import javax.swing.JOptionPane;


/**
 * @author Ichrak
 *
 */
public class Accueil extends javax.swing.JFrame {

    /**
     * Creates new form Accueil
     */
    Map m;

    public Accueil(Map m) {
        this.m = m;
        m.ImporterBR();
//        this.br = new BR(this, m);
        // br.setVisible(true);;
        //a.setVisible(true);
        initComponents();
    }

    public void Ecrire(String s) {
        this.rsltText.append(s + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        HeuristiqueB = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        RIP = new javax.swing.JPanel();
        RIP1 = new javax.swing.JPanel();
        IterB = new javax.swing.JButton();
        etatinit = new javax.swing.JTextField();
        but = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rsltText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Fermer l'application");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        /*HeuristiqueB.setText("Appliquer l'algorithme");
        HeuristiqueB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeuristiqueBActionPerformed(evt);
            }
        });

        jLabel5.setText("l'algorithme A* avec pour heuristique spécifique au problème:");

        jLabel1.setText("if ?x=2 alors h=0");

        jLabel4.setText("si ?x+2 < 2 alors h=7");

        jLabel6.setText("si ?y >2 alors h=3");

        jLabel7.setText("sinon h=1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        	.addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(HeuristiqueB)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addComponent(HeuristiqueB)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("A star", jPanel3);*/

        HeuristiqueB.setText("Appliquer l'algorithme A Star");
        HeuristiqueB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	HeuristiqueBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RIPLayout1 = new javax.swing.GroupLayout(RIP1);
        RIP1.setLayout(RIPLayout1);
        RIPLayout1.setHorizontalGroup(
            RIPLayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RIPLayout1.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(HeuristiqueB)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        RIPLayout1.setVerticalGroup(
            RIPLayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RIPLayout1.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(HeuristiqueB)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("A star ", RIP1);
        
        IterB.setText("Appliquer l'algorithme Iter");
        IterB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IterBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RIPLayout = new javax.swing.GroupLayout(RIP);
        RIP.setLayout(RIPLayout);
        RIPLayout.setHorizontalGroup(
            RIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RIPLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(IterB)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        RIPLayout.setVerticalGroup(
            RIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RIPLayout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(IterB)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Recherche itérative", RIP);

        etatinit.setBackground(new java.awt.Color(204, 255, 255));
        etatinit.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        etatinit.setText("cruchesAetB(2,?y)");
        etatinit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etatinitActionPerformed(evt);
            }
        });

        but.setBackground(new java.awt.Color(204, 255, 255));
        but.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        but.setText("cruchesAetB(0,0)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Entrer l'état initiale");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Entrer le but");

        jButton1.setText("Afficher la base des règles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etatinit, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(but, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etatinit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rsltText.setBackground(new java.awt.Color(204, 255, 255));
        rsltText.setColumns(20);
        rsltText.setRows(5);
        rsltText.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(rsltText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(150, 150, 150))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void etatinitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etatinitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etatinitActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "Salem", "information", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void IterBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IterBActionPerformed
        rsltText.setText("************************* "
                + "Recherche Itératvie *************************\n");

        m.RechercheIterative(this.etatinit.getText(), this.but.getText());
        // m.rslt();
    }//GEN-LAST:event_IterBActionPerformed

    private void HeuristiqueBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeuristiqueBActionPerformed
        rsltText.setText("******************************* "
                + "A étoile *******************************\n");
        m.RechercheHeureustique(this.etatinit.getText(), this.but.getText());
        // m.rslt();
    }//GEN-LAST:event_HeuristiqueBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rsltText.setText("******************************* "
                + "La base des règles  *******************************\n");
        m.RsltAffichageBR();
// rsltText.setText(m.RsltAffichageBR());
    }//GEN-LAST:event_jButton1ActionPerformed

    public void Ecrire() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HeuristiqueB;
    private javax.swing.JButton IterB;
    private javax.swing.JPanel RIP;
    private javax.swing.JPanel RIP1;
    private javax.swing.JTextField but;
    private javax.swing.JTextField etatinit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea rsltText;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author kelompok 1
 */
public class About extends javax.swing.JInternalFrame {

    /**
     * Creates new form About
     */
    public About() {
        initComponents();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(122, 72, 221));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kelompok 1 @2021");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 733, 1230, 33));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Aplikasi ini dibuat untuk toko minuman herbal \"Monascho\" yang masih menyimpan datanya\n\ndi dalam pembukuan, jadi menurut kami itu tidak efektif dan bisa membuat kehilangan data\n\n\"Semoga dengan dibuatnya aplikasi ini toko herbal \"Monascho\" bisa aman dalam menyimpan data ");
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 1020, 150));

        jPanel3.setBackground(new java.awt.Color(122, 72, 221));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 1130, 220));

        jPanel4.setBackground(new java.awt.Color(122, 72, 221));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Monospaced", 0, 20)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setText("Aplikasi ini dibuat oleh:\n\n1)\tBAYU FIRDAUS (E41202548) \n2)\tARIEF ANASTIAR SUHARSONO (E41202383)\n3)\tINDRA WIRAWAN (E41202308)\n4)\tFIRDAUS FIRMANSYAH (E41202298)\n5)\tMOCHAMAD SYAFEI BAYAN RIZQI (E41202409)\n");
        jScrollPane3.setViewportView(jTextArea3);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 1030, 240));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 1130, 310));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1230, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}

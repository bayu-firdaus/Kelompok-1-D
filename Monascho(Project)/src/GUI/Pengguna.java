/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MethodLogin.DataPengguna;
import MethodLogin.PenggunaControl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Kelompok 1
 */
public class Pengguna extends javax.swing.JInternalFrame {
    int x = 0;
    private DefaultTableModel model;
    PenggunaControl pc = new PenggunaControl();
    DataPengguna dp = new DataPengguna();
    List<DataPengguna> ListPengguna = new ArrayList<DataPengguna>();
    /**
     * Creates new form Pengguna
     */
    public Pengguna() {
        initComponents();
        buatTable();
        showTable();
        tombolNormal();
        siapIsi(false);
        bersih();
    }
    
    private void buatTable() {
        model = new DefaultTableModel();
        model.addColumn("Kode Pengguna");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Telepon");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Hak Akses");
        tblPengguna.setModel(model);
    }
    
    private void showTable() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListPengguna.clear();
        ListPengguna=pc.Tampil();
        for(x=0; x < ListPengguna.size(); x++) {
            Object[] data = new Object[8];
            data[0]=ListPengguna.get(x).getIdpengguna();
            data[1]=ListPengguna.get(x).getNama();
            data[2]=ListPengguna.get(x).getAlamat();
            data[3]=ListPengguna.get(x).getKelamin();
            data[4]=ListPengguna.get(x).getTelepon();
            data[5]=ListPengguna.get(x).getUsername();
            data[6]=ListPengguna.get(x).getPassword();
            data[7]=ListPengguna.get(x).getHakakses();
            model.addRow(data);
        }
    }
    
      private void buatPengguna() {
        ListPengguna = pc.Tampil();
        int a = ListPengguna.size()-1;
        int no = Integer.parseInt(ListPengguna.get(a).getIdpengguna().replace("P-", ""))+1;
        txtID.setText("P-"+no);
        txtID.setEnabled(false);
    }
    
    private void bersih() {
        txtID.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        cmbKelamin.setSelectedItem("");
        txtTelepon.setText("");
        txtUser.setText("");
        txtPass.setText("");
        txtUlang.setText("");
        cmbAkses.setSelectedItem("");
    }
    
    private void tombolNormal() {
        btnSimpan.setEnabled(false);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    private void tombolKembali() {
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    
    private void siapIsi(boolean a) {
        txtID.setEnabled(a);
        txtNama.setEnabled(a);
        txtAlamat.setEnabled(a);
        cmbKelamin.setEnabled(a);
        txtTelepon.setEnabled(a);
        txtUser.setEnabled(a);
        cmbAkses.setEnabled(a);
        txtPass.setEnabled(a);
        txtUlang.setEnabled(a);
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
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        cmbKelamin = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtUlang = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        cmbAkses = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPengguna = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setTitle("USERS");
        setToolTipText("");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSimpan.setBackground(new java.awt.Color(122, 72, 221));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Save");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 660, 110, 50));

        btnTambah.setBackground(new java.awt.Color(122, 72, 221));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Add");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 660, 110, 50));

        btnEdit.setBackground(new java.awt.Color(122, 72, 221));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 110, 50));

        btnHapus.setBackground(new java.awt.Color(122, 72, 221));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Delete");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 660, 110, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setText("Nama");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("Kode Pengguna");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 150, 35));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 310, 35));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setText("Alamat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 90, 35));

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(txtAlamat);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 310, 70));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setText("Jenis Kelamin");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 150, 35));

        cmbKelamin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Laki-Laki", "Perempuan" }));
        jPanel1.add(cmbKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 170, 35));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setText("Telepon");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 120, 35));

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 170, 35));

        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 170, 35));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Username");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 140, 35));

        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 310, 35));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Password");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 140, 35));

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 310, 35));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setText("Ulangi Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 160, 35));

        txtUlang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtUlang, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 190, 310, 35));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("Hak Akses");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 150, 35));

        cmbAkses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbAkses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Admin", "Kasir" }));
        jPanel1.add(cmbAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 170, 35));

        tblPengguna.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPengguna.setGridColor(new java.awt.Color(255, 255, 255));
        tblPengguna.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblPengguna.setRowHeight(30);
        tblPengguna.setSelectionBackground(new java.awt.Color(122, 72, 221));
        tblPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenggunaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPengguna);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 1160, 200));

        jPanel2.setBackground(new java.awt.Color(122, 72, 221));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Kelompok 1 @2021");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 733, 1230, 33));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1230, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (txtUser.getText().isEmpty() || txtNama.getText().isEmpty() || txtAlamat.getText().isEmpty() || cmbKelamin.toString().equals(null) 
                || txtTelepon.getText().isEmpty() || txtPass.getText().isEmpty() || cmbAkses.toString().equals(null)) {
            JOptionPane.showMessageDialog(null, "Data Cannot Be Empty", "Monascho Store", JOptionPane.WARNING_MESSAGE);
        }else{
            //Validasi ID Pengguna
            if (txtPass.getText() == null ? txtUlang.getText() != null : !txtPass.getText().equals(txtUlang.getText())) {
                JOptionPane.showMessageDialog(null, "Password Does Not Match, Please Try Again", "Monascho Store", JOptionPane.WARNING_MESSAGE);
                txtUlang.setText("");
                txtPass.requestFocus();
                }else{
                        dp.setIdpengguna(txtID.getText());
                        dp.setNama(txtNama.getText());
                        dp.setAlamat(txtAlamat.getText());
                        dp.setKelamin(cmbKelamin.getSelectedItem().toString());
                        dp.setTelepon(txtTelepon.getText());
                        dp.setUsername(txtUser.getText());
                        dp.setPassword(txtPass.getText());
                        dp.setHakakses(cmbAkses.getSelectedItem().toString());
                        if(btnTambah.getText().equalsIgnoreCase("Cancel")) {
                         if (pc.Tambah(dp)==1) {
                            JOptionPane.showMessageDialog(null, "Data Has Been Saved","Monascho Store", JOptionPane.INFORMATION_MESSAGE);
                            buatTable();
                            showTable();                          
                        }else{
                            JOptionPane.showMessageDialog(null, "Data Failed To Save", "Monascho Store",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                if (btnEdit.getText().equalsIgnoreCase("Cancel")) {
                if (pc.edit(dp)==1) {
                    JOptionPane.showMessageDialog(null, "Data Has Been Changed", "Monascho Store", JOptionPane.INFORMATION_MESSAGE);
                    buatTable();
                    showTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Data Failed To Change", "Monascho Store", JOptionPane.WARNING_MESSAGE);
                }
                }
                        bersih();
                        siapIsi(false);
                        btnTambah.setText("Add");
                        btnEdit.setText("Edit");
                        tombolNormal();
                    }
            }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        dp.setIdpengguna(txtID.getText());
        int pesan = JOptionPane.showConfirmDialog(null, "Are You Sure Want to Delete This ?", "Monascho Store", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pesan == JOptionPane.YES_NO_OPTION) {
                if (pc.Hapus(dp)==1) {
                    JOptionPane.showMessageDialog(null, "Data Has Been Deleted", "Monascho Store", JOptionPane.INFORMATION_MESSAGE);
                    bersih();
                    tombolNormal();
                    siapIsi(false);
                    buatTable();
                    showTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Data Failed To Delete", "Monascho Store", JOptionPane.WARNING_MESSAGE);
                }
            }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
            if (btnEdit.getText().equalsIgnoreCase("Edit")) {
            btnEdit.setText("Cancel");
            siapIsi(true);
            txtID.setEnabled(false);
            txtNama.requestFocus();
            btnTambah.setEnabled(true);
            btnSimpan.setEnabled(true);
            btnHapus.setEnabled(false);
            btnEdit.setEnabled(true);
        }else{
            btnEdit.setText("Edit");
            bersih();
            siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
            if(btnTambah.getText().equalsIgnoreCase("Add")) {
            btnTambah.setText("Cancel");
            bersih();
            siapIsi(true);
            buatPengguna();
            txtNama.requestFocus();
            tombolKembali();
        }else{
            btnTambah.setText("Add");
            bersih();
            siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tblPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenggunaMouseClicked
        // TODO add your handling code here:
        int baris = tblPengguna.getSelectedRow();
        txtID.setText(tblPengguna.getModel().getValueAt(baris, 0).toString());
        txtNama.setText(tblPengguna.getModel().getValueAt(baris, 1).toString());
        txtAlamat.setText(tblPengguna.getModel().getValueAt(baris, 2).toString());
        cmbKelamin.setSelectedItem(tblPengguna.getModel().getValueAt(baris, 3).toString());
        txtTelepon.setText(tblPengguna.getModel().getValueAt(baris, 4).toString());
        txtUser.setText(tblPengguna.getModel().getValueAt(baris, 5).toString());
        txtPass.setText(tblPengguna.getModel().getValueAt(baris, 6).toString());
        cmbAkses.setSelectedItem(tblPengguna.getModel().getValueAt(baris, 7).toString());
        siapIsi(false);
        btnHapus.setEnabled(true);
        btnEdit.setEnabled(true);
    }//GEN-LAST:event_tblPenggunaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbAkses;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblPengguna;
    private javax.swing.JTextPane txtAlamat;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNama;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JPasswordField txtUlang;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

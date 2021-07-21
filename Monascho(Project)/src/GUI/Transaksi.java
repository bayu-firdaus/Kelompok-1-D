/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.database;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Kelompok 1
 */
public class Transaksi extends javax.swing.JInternalFrame {
Connection koneksi;
PreparedStatement pst, pst2;
ResultSet rst;
int istok, istok2, iharga, ijumlah, kstok, tstok;
String harga, barang,nama, dbarang, KD, jam, tanggal,ssub;
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        koneksi=database.koneksiDB();
        delay();
        detail();    
        autonumber();
        sum();
    }
    
    private void simpan(){
        String tgl=jTextField3.getText();
        String jam=jTextField9.getText();
      try {
            String sql="insert into transaksi (Kode_Transaksi,Kode_Detail,Nama_Barang,Tanggal,Total) value (?,?,?,?,?)";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, jkod.getText());
            pst.setString(2, KD);
            pst.setString(3, nama);
            pst.setString(4, tgl);
            pst.setString(5, txtTotal.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Has Been Saved", "Monascho Store", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
    }
    
    
    private void total(){
    int total, bayar, kembali;
        total= Integer.parseInt(txtBayar.getText());
        bayar= Integer.parseInt(txtTotal.getText());
        kembali=total-bayar;
        String ssub=String.valueOf(kembali);
        txtKembali.setText(ssub);
    }
    
    public void clsr(){
    jjum.setText("");
    jdis.setText("");
    }
    
    public void cari(){
    try {
        String sql="select * from barang where Nama_Barang LIKE '%"+txtCari.getText()+"%'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        tblBarang.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    lebarKolom();
    }
    
    public void kurangi_stok(){
    int qty;
    qty=Integer.parseInt(jjum.getText());
    kstok=istok-qty;
    }
    
    private void subtotal(){
    int diskon, jumlah, sub;
            if (jdis.getText().equals("")) {diskon=0;}
            else {diskon= Integer.parseInt(jdis.getText());}
         jumlah= Integer.parseInt(jjum.getText());
         sub=(jumlah*iharga)-diskon;
         ssub=String.valueOf(sub);     
    }
    
    public void tambah_stok(){
    tstok=ijumlah+istok2;
        try {
        String update="update barang set Stok='"+tstok+"' where Kode_Barang='"+barang+"'";
        pst2=koneksi.prepareStatement(update);
        pst2.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void ambil_stock(){
    try {
    String sql="select * from barang where Kode_Barang='"+barang+"'";
    pst=koneksi.prepareStatement(sql);
    rst=pst.executeQuery();
    if (rst.next()) {    
    String stok=rst.getString(("Stok"));
    istok2= Integer.parseInt(stok);
    }
}catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }
    
    public void sum(){
        int totalBiaya = 0;
        int subtotal;
        DefaultTableModel dataModel = (DefaultTableModel) tblJual.getModel();
        int jumlah = tblJual.getRowCount();
        for (int i=0; i<jumlah; i++){
        subtotal = Integer.parseInt(dataModel.getValueAt(i, 5).toString());
        totalBiaya += subtotal;
        }
        txtTotal.setText(String.valueOf(totalBiaya));
    }
    
    public void autonumber(){
    try{
        String sql = "SELECT MAX(RIGHT(Kode_Transaksi,3)) AS NO FROM transaksi";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        while (rst.next()) {
                if (rst.first() == false) {
                    jkod.setText("TRX001");
                } else {
                    rst.last();
                    int auto_id = rst.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    for (int j = 0; j < 3 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    jkod.setText("TRX" + no);
                }
            }
        rst.close();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void detail(){
    try {
        String Kode_detail=jkod.getText();
        String KD="D"+Kode_detail;
        String sql="select * from detail_transaksi where Kode_Detail='"+KD+"'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        tblJual.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }
    
    public void delay(){
    Thread clock=new Thread(){
        public void run(){
            for(;;){
                Calendar cal=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat format2=new SimpleDateFormat("dd-MM-yyyy");
                jTextField9.setText(format.format(cal.getTime()));
                 jTextField3.setText(format2.format(cal.getTime()));
                
            try { sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
    clock.start();
    }

    public void lebarKolom(){
        TableColumn column;
        tblBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tblBarang.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);
        column = tblBarang.getColumnModel().getColumn(1);
        column.setPreferredWidth(60);
        column = tblBarang.getColumnModel().getColumn(2);
        column.setPreferredWidth(150);
        column = tblBarang.getColumnModel().getColumn(3);
        column.setPreferredWidth(70);
        column = tblBarang.getColumnModel().getColumn(4);
        column.setPreferredWidth(80);
        column = tblBarang.getColumnModel().getColumn(5);
        column.setPreferredWidth(70); 
        column = tblBarang.getColumnModel().getColumn(6);
        column.setPreferredWidth(70);
        tblBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        kdBarang = new javax.swing.JTextField();
        kdTransaksi = new javax.swing.JTextField();
        Harga = new javax.swing.JTextField();
        nmBarang = new javax.swing.JTextField();
        Tanggal = new javax.swing.JTextField();
        Subtotal = new javax.swing.JTextField();
        Discount = new javax.swing.JTextField();
        Jam = new javax.swing.JTextField();
        Jumlah = new javax.swing.JTextField();
        Total = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jdis = new javax.swing.JTextField();
        jjum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jkod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJual = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog1.getContentPane().add(kdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, 30));
        jDialog1.getContentPane().add(kdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 30));
        jDialog1.getContentPane().add(Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 30));
        jDialog1.getContentPane().add(nmBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 30));
        jDialog1.getContentPane().add(Tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 110, 30));
        jDialog1.getContentPane().add(Subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 110, 30));
        jDialog1.getContentPane().add(Discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 110, 30));
        jDialog1.getContentPane().add(Jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 110, 30));
        jDialog1.getContentPane().add(Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 30));
        jDialog1.getContentPane().add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 110, 30));

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setTitle("SALES");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jTextField3.setEnabled(false);
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 140, 25));

        jTextField9.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jTextField9.setEnabled(false);
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, -30, 160, 25));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 310, 35));

        tblBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBarang.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblBarang.setRowHeight(30);
        tblBarang.setSelectionBackground(new java.awt.Color(122, 72, 221));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 590, 260));

        jdis.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jdis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jdis, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 640, 200, 35));

        jjum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jjum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jjum, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 150, 35));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Jumlah");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 460, 150, 35));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Discount");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, 200, 35));

        jToggleButton1.setBackground(new java.awt.Color(122, 72, 221));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Search");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 80, 35));

        jkod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jkod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jkod.setEnabled(false);
        jPanel1.add(jkod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 210, 35));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Kode Transaksi");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 250, 35));

        jPanel3.setBackground(new java.awt.Color(122, 72, 221));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_add_30px.png"))); // NOI18N
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel3.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 45));

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_minus_30px.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel3.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 50, 45));

        tblJual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblJual.setModel(new javax.swing.table.DefaultTableModel(
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
        tblJual.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblJual.setRowHeight(30);
        tblJual.setSelectionBackground(new java.awt.Color(122, 72, 221));
        tblJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblJualMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblJual);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 590, 300));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total ");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 100, 35));

        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setEnabled(false);
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 200, 35));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bayar");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 100, 35));

        txtBayar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 200, 35));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kembalian");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, -1, 35));

        txtKembali.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtKembali.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKembali.setEnabled(false);
        txtKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembaliActionPerformed(evt);
            }
        });
        jPanel3.add(txtKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 200, 35));

        btnSimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(122, 72, 221));
        btnSimpan.setText("Save");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel3.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 110, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 610, 670));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nama Barang");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 300, 35));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        txtNama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNamaPropertyChange(evt);
            }
        });
        jPanel1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 300, 35));

        jPanel2.setBackground(new java.awt.Color(122, 72, 221));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kelompok 1 @2021");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 733, 1230, 33));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1230, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        cari();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        // TODO add your handling code here:
        try {
            int row=tblBarang.getSelectedRow();
            String tabel_klik=(tblBarang.getModel().getValueAt(row, 0).toString());
            txtNama.setText(tblBarang.getModel().getValueAt(row, 2).toString());
            String sql="select * from barang where id_barang='"+tabel_klik+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            if (rst.next()) {
                barang=rst.getString(("Kode_Barang"));
                nama=rst.getString(("Nama_Barang"));
                String stok=rst.getString(("Stok"));
                istok= Integer.parseInt(stok);
                harga=rst.getString(("Harga"));
                iharga= Integer.parseInt(harga);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_tblBarangMouseClicked

    private void tblJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJualMouseClicked
        try {
            int row=tblJual.getSelectedRow();
            dbarang=(tblJual.getModel().getValueAt(row, 1).toString());
            String sql="select * from detail_transaksi where Kode_Barang='"+dbarang+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            if (rst.next()) {
                String jumlah=rst.getString(("Jumlah"));
                ijumlah= Integer.parseInt(jumlah);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
        ambil_stock();
        lebarKolom();
    }//GEN-LAST:event_tblJualMouseClicked

    private void txtKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembaliActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        total();
        simpan();
        autonumber();
        detail();
        txtTotal.setText("");
        txtBayar.setText("");
        txtKembali.setText("");
        txtCari.setText("");
        cari();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try {
            String sql="delete from detail_transaksi where Kode_Barang=?";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, dbarang);
            pst.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        detail();
        sum();
        tambah_stok();
        cari();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        subtotal();
        kurangi_stok();
        try {
            String diskon;
            if (jdis.getText().equals("")) {diskon="0";}
            else {diskon=jdis.getText();}
            if(jjum.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Data Cannot Be Empty", "Monascho Store", JOptionPane.WARNING_MESSAGE);
            }
            String Kode_detail=jkod.getText();
            KD="D"+Kode_detail;
            String sql="insert into detail_transaksi (Kode_Detail,Kode_Barang,Harga,Jumlah,Discount,Subtotal) values (?,?,?,?,?,?)";
            String update="update barang set Stok='"+kstok+"' where Kode_Barang='"+barang+"'";
            pst=koneksi.prepareStatement(sql);
            pst2=koneksi.prepareStatement(update);
            pst.setString(1, KD);
            pst.setString(2, barang);
            pst.setString(3, harga);
            pst.setString(4, jjum.getText());
            pst.setString(5, diskon);
            pst.setString(6, ssub);
            pst.execute();
            pst2.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        detail();
        sum();
        cari();
        clsr();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtCariKeyReleased

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtNamaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNamaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Discount;
    private javax.swing.JTextField Harga;
    private javax.swing.JTextField Jam;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JTextField Subtotal;
    private javax.swing.JTextField Tanggal;
    private javax.swing.JTextField Total;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jdis;
    private javax.swing.JTextField jjum;
    private javax.swing.JTextField jkod;
    private javax.swing.JTextField kdBarang;
    private javax.swing.JTextField kdTransaksi;
    private javax.swing.JTextField nmBarang;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblJual;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}

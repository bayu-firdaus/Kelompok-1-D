/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Koneksi.database;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Kelompok 1
 */
public class Laporan extends javax.swing.JInternalFrame {
Connection koneksi;
PreparedStatement pst;
ResultSet rst;
String tanggal,tanggal2, sql;

JasperReport JasRep;
    JasperPrint JasPri;
    Map param = new HashMap(2);
    JasperDesign JasDes;
    
    private DefaultTableModel tabmode;
    
    // frame maks dan geser panel
    static boolean maximixed = true;
    int xMouse;
    int yMouse;

public void noTable(){
        int Baris = tabmode.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor +".",a,0);
        }
    }
    
    public void tanggal(){
        Date tgl = new Date();
        tglAwal.setDate(tgl);
        tglAkhir.setDate(tgl);
    }
    
    public void dataTable(){
        Object[] Baris = {"No","Tanggal Masuk","Kode Barang","Nama Barang","Jenis","Stok","Harga","Kadaluwarsa"};
        tabmode = new DefaultTableModel(null, Baris);
        tblBarang2.setModel(tabmode);   
    }
    
    public void dataTable2(){
        Object[] Baris = {"Tanggal", "Kode Transaksi", "Kode Detail", "Nama Barang", "Total"};
        tabmode = new DefaultTableModel(null, Baris);
        tblJual.setModel(tabmode);
    }
    
    public void lebarKolom(){
        TableColumn column;
        tblBarang2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tblBarang2.getColumnModel().getColumn(0);
        column.setPreferredWidth(30);
        column = tblBarang2.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = tblBarang2.getColumnModel().getColumn(2);
        column.setPreferredWidth(70);
        column = tblBarang2.getColumnModel().getColumn(3);
        column.setPreferredWidth(170);
        column = tblBarang2.getColumnModel().getColumn(4);
        column.setPreferredWidth(70);
        column = tblBarang2.getColumnModel().getColumn(5);
        column.setPreferredWidth(40); 
        column = tblBarang2.getColumnModel().getColumn(6);
        column.setPreferredWidth(65);
        column = tblBarang2.getColumnModel().getColumn(7);
        column.setPreferredWidth(80);
        tblBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
    }
    
        public void kolomTransaksi(){
        TableColumn column;
        tblJual.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tblJual.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column = tblJual.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = tblJual.getColumnModel().getColumn(2);
        column.setPreferredWidth(80);
        column = tblJual.getColumnModel().getColumn(3);
        column.setPreferredWidth(200);
        column = tblJual.getColumnModel().getColumn(4);
        column.setPreferredWidth(80);
        tblBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
    }
        

    public void pencarian(String sql){
        Object[] Baris = {"No","Tanggal Masuk","Kode Barang","Nama Barang","Jenis","Stok","Harga","Kadaluwarsa"};
        tabmode = new DefaultTableModel(null, Baris);
        tblBarang2.setModel(tabmode);
        int brs = tblBarang2.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode.removeRow(1);
        }
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("Tanggal_Masuk");
                String kdBarang = hasil.getString("Kode_Barang");
                String namaBarang = hasil.getString("Nama_Barang");
                String jenis = hasil.getString("Jenis");
                String stok = hasil.getString("Stok");
                String harga = hasil.getString("Harga");
                String kadaluwarsa = hasil.getString("kadaluwarsa");
                String[] data = {"",tanggal,kdBarang,namaBarang,jenis,stok,harga,kadaluwarsa};
                tabmode.addRow(data);
                noTable();
            }
        } catch(Exception e){
            
        }
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
                Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
    clock.start();
    }
    
    /**
     * Creates new form Laporan
     */
    public Laporan(){
        initComponents();
        koneksi=database.koneksiDB();
        delay();
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
        jTglAwal = new com.toedter.calendar.JDateChooser();
        jTglAkhir = new com.toedter.calendar.JDateChooser();
        btnTampil2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tglAwal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tglAkhir = new com.toedter.calendar.JDateChooser();
        btnTampil = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        labelNama = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBarang2 = new javax.swing.JTable();
        btnCetak2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJual = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setTitle("REPORT");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTglAwal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jTglAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 240, 30));

        jTglAkhir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jTglAkhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 240, 30));

        btnTampil2.setBackground(new java.awt.Color(122, 72, 221));
        btnTampil2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTampil2.setForeground(new java.awt.Color(255, 255, 255));
        btnTampil2.setText("Show");
        btnTampil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampil2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnTampil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 80, 35));

        jPanel4.setBackground(new java.awt.Color(122, 72, 221));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sales Report");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 580, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 580, 60));

        jPanel2.setBackground(new java.awt.Color(122, 72, 221));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tglAwal.setBackground(new java.awt.Color(255, 255, 255));
        tglAwal.setDateFormatString("dd-MM-yyyy");
        tglAwal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel2.add(tglAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 200, 30));

        jLabel1.setBackground(new java.awt.Color(122, 72, 221));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("From :");
        jLabel1.setOpaque(true);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 30));

        jLabel2.setBackground(new java.awt.Color(122, 72, 221));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Until :");
        jLabel2.setOpaque(true);
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, 30));

        tglAkhir.setBackground(new java.awt.Color(255, 255, 255));
        tglAkhir.setDateFormatString("dd-MM-yyyy");
        tglAkhir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel2.add(tglAkhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 200, 30));

        btnTampil.setBackground(new java.awt.Color(255, 255, 255));
        btnTampil.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTampil.setForeground(new java.awt.Color(122, 72, 221));
        btnTampil.setText("Show");
        btnTampil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });
        jPanel2.add(btnTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 620, 80, 35));

        btnCetak.setBackground(new java.awt.Color(255, 255, 255));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(122, 72, 221));
        btnCetak.setText("Print");
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel2.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 80, 35));

        labelNama.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelNama.setForeground(new java.awt.Color(255, 255, 255));
        labelNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNama.setText("Products Report");
        jPanel2.add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 610, 40));

        tblBarang2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblBarang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblBarang2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblBarang2.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblBarang2.setRowHeight(30);
        tblBarang2.setSelectionBackground(new java.awt.Color(122, 72, 221));
        tblBarang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarang2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBarang2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 161, 600, 440));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 620, 670));

        btnCetak2.setBackground(new java.awt.Color(122, 72, 221));
        btnCetak2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCetak2.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak2.setText("Print");
        btnCetak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetak2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCetak2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 80, 35));

        jPanel3.setBackground(new java.awt.Color(122, 72, 221));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Kelompok 1 @2021");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 733, 1230, 33));

        tblJual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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
        jScrollPane1.setViewportView(tblJual);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 540, 150));

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
        jScrollPane2.setViewportView(tblBarang);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 540, 150));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Transactions Detail");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 540, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Transactions Data");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 540, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Until :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("From :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1230, 790));

        jTextField9.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jTextField9.setEnabled(false);
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, -20, 150, 20));

        jTextField3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jTextField3.setEnabled(false);
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, -20, 160, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTampil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil2ActionPerformed
        dataTable2();
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggalAwal = String.valueOf(fm.format(jTglAwal.getDate()));
        String tanggalAkhir = String.valueOf(fm.format(jTglAkhir.getDate()));
        String sql = "select * from transaksi where Tanggal between '"+tanggalAwal+"' and '"+tanggalAkhir+"'";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("Tanggal");
                String kdTransaksi = hasil.getString("Kode_Transaksi");
                String kdDetail = hasil.getString("Kode_Detail");
                String namaBarang = hasil.getString("Nama_Barang");
                String total = hasil.getString("Total");
                String[] data = {tanggal,kdTransaksi,kdDetail,namaBarang,total};
                tabmode.addRow(data);
                kolomTransaksi();
            }
        } catch (Exception e){
        }
        kolomTransaksi();
    }//GEN-LAST:event_btnTampil2ActionPerformed

    private void tblBarang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarang2MouseClicked
        int bar = tblBarang2.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        try{
            dateValue = date.parse((String)tblBarang2.getValueAt(bar, 1));
        } catch (ParseException ex){
            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblBarang2MouseClicked

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        dataTable();
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggalAwal = String.valueOf(fm.format(tglAwal.getDate()));
        String tanggalAkhir = String.valueOf(fm.format(tglAkhir.getDate()));
        String sql = "select * from barang where Tanggal_Masuk between '"+tanggalAwal+"' and '"+tanggalAkhir+"'";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("Tanggal_Masuk");
                String kdBarang = hasil.getString("Kode_Barang");
                String namaBarang = hasil.getString("Nama_Barang");
                String jenis = hasil.getString("Jenis");
                String stok = hasil.getString("Stok");
                String harga = hasil.getString("Harga");
                String kadaluwarsa = hasil.getString("kadaluwarsa");
                String[] data = {"",tanggal,kdBarang,namaBarang,jenis,stok,harga,kadaluwarsa};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (Exception e){
        }
        lebarKolom();
    }//GEN-LAST:event_btnTampilActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            Connection konn = new database().koneksiDB();
            File file = new File("D:\\IDM\\Download\\Compressed\\Aplikasi-Kasir-Netbeans-master\\Monascho(Project)\\src\\GUI\\LaporanBarang.jrxml");
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggalAwal = String.valueOf(fm.format(tglAwal.getDate()));
            String tanggalAkhir = String.valueOf(fm.format(tglAkhir.getDate()));
            JasDes = JRXmlLoader.load(file);
            param.put("tglAwal",tanggalAwal);
            param.put("tglAkhir",tanggalAkhir);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, konn);
            //JasperViewer.viewReport(JasPri, false);
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            jasperViewer.setExtendedState(jasperViewer.getExtendedState()|javax.swing.JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
            //jasperViewer.setAlwaysOnTop(maximixed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to Open a Report", "Monascho Store", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnCetak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetak2ActionPerformed
        // TODO add your handling code here:
                try {
            Connection konn = new database().koneksiDB();
            File file = new File("D:\\IDM\\Download\\Compressed\\Aplikasi-Kasir-Netbeans-master\\Monascho(Project)\\src\\GUI\\LaporanJual.jrxml");
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggalAwal = String.valueOf(fm.format(jTglAwal.getDate()));
            String tanggalAkhir = String.valueOf(fm.format(jTglAkhir.getDate()));
            JasDes = JRXmlLoader.load(file);
            param.put("tglAwal2",tanggalAwal);
            param.put("tglAkhir2",tanggalAkhir);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, konn);
            //JasperViewer.viewReport(JasPri, false);
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            jasperViewer.setExtendedState(jasperViewer.getExtendedState()|javax.swing.JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
            //jasperViewer.setAlwaysOnTop(maximixed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to Open a Report", "Monascho Store", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCetak2ActionPerformed

    private void tblJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJualMouseClicked

        try {
            int row=tblJual.getSelectedRow();
            String tabel_klik=(tblJual.getModel().getValueAt(row, 2).toString());
            String sql="select * from detail_transaksi where Kode_Detail=?";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, tabel_klik);
            rst=pst.executeQuery();
            tblBarang.setModel(DbUtils.resultSetToTableModel(rst));
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_tblJualMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnCetak2;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private com.toedter.calendar.JDateChooser jTglAkhir;
    private com.toedter.calendar.JDateChooser jTglAwal;
    private javax.swing.JLabel labelNama;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblBarang2;
    private javax.swing.JTable tblJual;
    private com.toedter.calendar.JDateChooser tglAkhir;
    private com.toedter.calendar.JDateChooser tglAwal;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MethodBarang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kelompok 1
 */
public class BarangControl {
     Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public BarangControl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/monascho","root","");
            st = con.createStatement();
            System.out.println("Berhasil Terhubung");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    public List tampil() {
        List logBeli = new ArrayList() ;
        sql = "select Kode_Barang,Nama_Barang,Jenis,Stok,Harga,Tanggal_Masuk,kadaluwarsa from barang order by id_barang asc";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DataBarang db = new DataBarang();
                db.setKdBarang(rs.getString("Kode_Barang"));
                db.setNmBarang(rs.getString("Nama_Barang"));
                db.setJenis(rs.getString("Jenis"));
                db.setStok(rs.getInt("Stok"));
                db.setHarga(rs.getInt("Harga"));
                db.setTanggal(rs.getString("Tanggal_Masuk"));
                db.setKadaluwarsa(rs.getString("kadaluwarsa"));
                logBeli.add(db);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return logBeli;
    }
    
    public  int tambahBarang (DataBarang e) {
        sql = "insert into barang (Kode_Barang,Nama_Barang,Jenis,Stok,Harga,Tanggal_Masuk,kadaluwarsa) values ('"+e.getKdBarang()+"','"+e.getNmBarang()
                +"','"+e.getJenis()+"','"+e.getStok()+"','"+e.getHarga()+"','"+e.getTanggal()+"','"+e.getKadaluwarsa()+"')";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public  int editBarang (DataBarang e) {
        sql = "update barang set Nama_Barang ='"+e.getNmBarang()+"',Jenis='"+e.getJenis()
              +"',Stok='"+e.getStok()+"',Harga='"+e.getHarga()+"',Tanggal_Masuk='"+e.getTanggal()+"',kadaluwarsa='"+e.getKadaluwarsa()
              +"' where Kode_Barang='"+e.getKdBarang()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public List cariBarang(String cari) {
        List logObat = new ArrayList();
        sql = "select Kode_Barang,Nama_Barang,Jenis,Stok,Harga,Tanggal_Masuk,kadaluwarsa from barang where Jenis "
                + "like '%"+cari+"%' or Nama_Barang like '%"+cari+"%'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DataBarang Do = new DataBarang();
                Do.setKdBarang(rs.getString("Kode_Barang"));
                Do.setNmBarang(rs.getString("Nama_Barang"));
                Do.setJenis(rs.getString("Jenis"));
                Do.setStok(rs.getInt("Stok"));
                Do.setHarga(rs.getInt("Harga"));
                Do.setTanggal(rs.getString("Tanggal_Masuk"));
                Do.setKadaluwarsa(rs.getString("kadaluwarsa"));
                logObat.add(Do);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return logObat;
    }
     
    public int deleteBarang(DataBarang e) {
        sql = "delete from barang where Kode_Barang='"+e.getKdBarang()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public int getJumlahBarang() throws SQLException {
        sql = "select count(*) as Jumlah_Barang " + "from barang";
        int jumlah;
        try {
        rs = st.executeQuery(sql);
        while(rs.next()) {
            jumlah = rs.getInt("Jumlah_Barang");
            return jumlah;
        }
        } catch (Exception e) {
        Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public List tampilJenis() {
        List logObat = new ArrayList();
        sql = "select distinct(Jenis) from barang order by id_barang asc";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()) {
                DataBarang eb = new DataBarang();
                eb.setJenis(rs.getString("Jenis"));
                logObat.add(eb);
            }
        } catch (Exception e) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return logObat;
    }
    
    public int updateStok(int stok, String KdBarang) {
        sql = "update barang set Stok='"+stok+"' where Kode_Barang='"+KdBarang+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.getLogger(BarangControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
}

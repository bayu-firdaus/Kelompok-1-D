/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MethodLogin;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kelompok 1
 */
public class PenggunaControl {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public PenggunaControl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/monascho", "root", "");
            st = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, Terjadi Kesalahan Pada : \n" + e);
        }
    }
    
    public List CariLogin(String username, String password) {
     List logLogin = new ArrayList();
     int result;
     sql = "select username, password, hak_akses from login where username='"+username+"' and password='"+password+"'";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()) {
                DataPengguna ep = new DataPengguna();
                ep.setUsername(rs.getString("username"));
                ep.setPassword(rs.getString("password"));
                ep.setHakakses(rs.getString("hak_akses"));
                logLogin.add(ep);
            } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Login, Pada:n" + e);
        }
        return logLogin;
    }
    
    public int Tambah(DataPengguna e) {
        sql = "insert into login (kd_pengguna,nama,alamat,jenis_kelamin,telepon,username,password,hak_akses) values ('"+e.getIdpengguna()
                +"','"+e.getNama()+"','"+e.getAlamat()+"','"+e.getKelamin()+"','"+e.getTelepon()
                +"','"+e.getUsername()+"','"+e.getPassword()+"','"+e.getHakakses()+"')";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(PenggunaControl.class.getName()).log(Level.SEVERE, null, a);
        }
        return hasil;
    }
    
        public int Hapus(DataPengguna e) {
        sql = "delete from login where kd_pengguna='"+e.getIdpengguna()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(PenggunaControl.class.getName()).log(Level.SEVERE, null, a);
        }
        return hasil;
    }
        
        public  int edit (DataPengguna e) {
        sql = "update login set nama='"+e.getNama()+"',alamat='"+e.getAlamat()+"',jenis_kelamin='"+e.getKelamin()+"',telepon='"+e.getTelepon()
                +"',username ='"+e.getUsername()+"',password='"+e.getPassword()
                +"',hak_akses='"+e.getHakakses()+"' where kd_pengguna='"+e.getIdpengguna()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception a) {
            Logger.getLogger(PenggunaControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public List Tampil() {
        List logPengguna = new ArrayList();
        sql = "select kd_pengguna,nama,alamat,jenis_kelamin,telepon,username,password,hak_akses from login order by id_pengguna asc";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()) {
                DataPengguna dp = new DataPengguna();
                dp.setIdpengguna(rs.getString("kd_pengguna"));
                dp.setNama(rs.getString("nama"));
                dp.setAlamat(rs.getString("alamat"));
                dp.setKelamin(rs.getString("jenis_kelamin"));
                dp.setTelepon(rs.getString("telepon"));
                dp.setUsername(rs.getString("username"));
                dp.setPassword(rs.getString("password"));
                dp.setHakakses(rs.getString("hak_akses"));
                logPengguna.add(dp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Tampil, Pada:" + e);
        }
        return logPengguna;
    }
}

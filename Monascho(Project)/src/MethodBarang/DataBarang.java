/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MethodBarang;

/**
 *
 * @author Kelompok 1
 */
public class DataBarang {
    public String kode_barang, nama_barang,tgl_masuk, jenis, kadaluwarsa;
    public int stok, harga;
    
    public String getKdBarang() {
        return kode_barang;
    }
    public void setKdBarang(String kode_barang) {
        this.kode_barang = kode_barang;
    }
    public String getNmBarang() {
        return nama_barang;
    }
    public void setNmBarang(String nama_barang) {
        this.nama_barang = nama_barang;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getKadaluwarsa() {
        return kadaluwarsa;
    }
    public void setKadaluwarsa(String kadaluwarsa) {
        this.kadaluwarsa = kadaluwarsa;
    }
    public String getTanggal() {
        return tgl_masuk;
    }
    public void setTanggal(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }
    public int getStok() {
        return stok;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
}

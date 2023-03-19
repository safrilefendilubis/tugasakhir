package com.juaracoding.DBLaundry.dto;

import com.juaracoding.DBLaundry.model.PaketLayanan;
import com.juaracoding.DBLaundry.model.Pelanggan;
import com.juaracoding.DBLaundry.model.Pembayaran;

public class PesananDTO {

    //variabel untuk menampung ID pesanan
    private Long idPesanan;

    //object  relation ke model paket layanan
    private PaketLayanan paketLayanan;

    //object  relation ke model pelanggan
    private Pelanggan pelanggan;

    //object  relation ke model pembayaran
    private Pembayaran pembayaran;

    //variabel untuk menampung berat
    private Double berat;

    //variabel untuk menampung total harga
    private Long totalHarga;

    public Long getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public PaketLayanan getPaketLayanan() {
        return paketLayanan;
    }

    public void setPaketLayanan(PaketLayanan paketLayanan) {
        this.paketLayanan = paketLayanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public Double getBerat() {
        return berat;
    }

    public void setBerat(Double berat) {
        this.berat = berat;
    }

    public Long getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Long totalHarga) {
        this.totalHarga = totalHarga;
    }
}

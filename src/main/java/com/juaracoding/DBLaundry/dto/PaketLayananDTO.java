package com.juaracoding.DBLaundry.dto;

public class PaketLayananDTO {

    //variabel untuk menampung ID List Harga
    private Long idListHarga;

    //variabel untuk menampung nama paket
    private String namaPaket;

    //variabel untuk menampung harga perkilo
    private Long hargaPerKilo;

    //variabel untuk menampung tipe layanan
    private String tipeLayanan;

    public Long getIdListHarga() {
        return idListHarga;
    }

    public void setIdListHarga(Long idListHarga) {
        this.idListHarga = idListHarga;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public Long getHargaPerKilo() {
        return hargaPerKilo;
    }

    public void setHargaPerKilo(Long hargaPerKilo) {
        this.hargaPerKilo = hargaPerKilo;
    }

    public String getTipeLayanan() {
        return tipeLayanan;
    }

    public void setTipeLayanan(String tipeLayanan) {
        this.tipeLayanan = tipeLayanan;
    }
}

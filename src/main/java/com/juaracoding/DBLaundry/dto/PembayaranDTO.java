package com.juaracoding.DBLaundry.dto;

public class PembayaranDTO {

    //variabel untuk menampung ID pembayaran
    private Long idPembayaran;

    //variabel untuk menampung nama pembayaran
    private String namaPembayaran;

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getNamaPembayaran() {
        return namaPembayaran;
    }

    public void setNamaPembayaran(String namaPembayaran) {
        this.namaPembayaran = namaPembayaran;
    }
}

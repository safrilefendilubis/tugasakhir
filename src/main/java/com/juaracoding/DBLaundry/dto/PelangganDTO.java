package com.juaracoding.DBLaundry.dto;

public class PelangganDTO {

    //variabel untuk menampung ID pelanggan
    private Long idPelanggan;

    //variabel untuk menampung nama lengkap
    private String namaLengkap;

    //variabel untuk menampung alamat lengkap
    private String alamatLengkap;

    //variabel untuk menampung no handphone
    private String noHandphone;

    public Long getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getNoHandphone() {
        return noHandphone;
    }

    public void setNoHandphone(String noHandphone) {
        this.noHandphone = noHandphone;
    }
}

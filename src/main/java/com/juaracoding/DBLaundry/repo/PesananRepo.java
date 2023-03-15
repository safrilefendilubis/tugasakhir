package com.juaracoding.DBLaundry.repo;

import com.juaracoding.DBLaundry.model.Pesanan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesananRepo extends JpaRepository<Pesanan,Long> {
    List<Pesanan> findByIsDelete(byte byteIsDelete);
    Page<Pesanan> findByIsDelete(Pageable page,byte byteIsDelete);
    Page<Pesanan> findByIsDeleteAndPelangganNamaLengkapContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Pesanan> findByIsDeleteAndPaketLayananHargaPerKilo(Pageable page , byte byteIsDelete, Double values);
    Page<Pesanan> findByIsDeleteAndIdPesanan(Pageable page , byte byteIsDelete, Long values);
    Page<Pesanan> findByIsDeleteAndBerat(Pageable page , byte byteIsDelete, Double values);
    Page<Pesanan> findByIsDeleteAndTotalHarga(Pageable page , byte byteIsDelete, Double values);
    Page<Pesanan> findByIsDeleteAndPaketLayananNamaPaketContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Pesanan> findByIsDeleteAndPaketLayananTipeLayananContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Pesanan> findByIsDeleteAndPembayaranNamaPembayaranContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);

}


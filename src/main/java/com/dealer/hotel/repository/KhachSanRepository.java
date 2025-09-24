package com.dealer.hotel.repository;

import com.dealer.hotel.entity.KhachSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachSanRepository extends JpaRepository<KhachSan, Integer> {
    
    @Query("SELECT ks FROM KhachSan ks JOIN FETCH ks.thanhPho JOIN FETCH ks.loaiKhachSan ORDER BY ks.ten")
    List<KhachSan> findAllWithDetails();
    
    @Query("SELECT ks FROM KhachSan ks JOIN FETCH ks.thanhPho JOIN FETCH ks.loaiKhachSan WHERE ks.thanhPho.id = :thanhPhoId")
    List<KhachSan> findByThanhPhoId(@Param("thanhPhoId") Integer thanhPhoId);
    
    @Query("SELECT ks FROM KhachSan ks JOIN FETCH ks.thanhPho JOIN FETCH ks.loaiKhachSan WHERE ks.loaiKhachSan.id = :loaiKhachSanId")
    List<KhachSan> findByLoaiKhachSanId(@Param("loaiKhachSanId") Integer loaiKhachSanId);
    
    @Query("SELECT ks FROM KhachSan ks JOIN FETCH ks.thanhPho JOIN FETCH ks.loaiKhachSan " +
           "WHERE LOWER(ks.thanhPho.ten) LIKE LOWER(CONCAT('%', :tenThanhPho, '%'))")
    List<KhachSan> findByThanhPhoTenContainingIgnoreCase(@Param("tenThanhPho") String tenThanhPho);
}
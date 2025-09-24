package com.dealer.hotel.repository;

import com.dealer.hotel.entity.DatPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatPhongRepository extends JpaRepository<DatPhong, Integer> {
    
    @Query("SELECT dp FROM DatPhong dp JOIN FETCH dp.taiKhoan JOIN FETCH dp.phong " +
           "WHERE dp.taiKhoan.tenTaiKhoan = :tenTaiKhoan ORDER BY dp.ngayDat DESC")
    List<DatPhong> findByTaiKhoanTenTaiKhoan(@Param("tenTaiKhoan") String tenTaiKhoan);
    
    @Query("SELECT dp FROM DatPhong dp JOIN FETCH dp.taiKhoan JOIN FETCH dp.phong ORDER BY dp.ngayDat DESC")
    List<DatPhong> findAllWithDetails();
}
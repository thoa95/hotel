package com.dealer.hotel.repository;

import com.dealer.hotel.entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer> {
    
    @Query("SELECT p FROM Phong p JOIN FETCH p.khachSan ORDER BY p.ten")
    List<Phong> findAllWithKhachSan();
    
    @Query("SELECT p FROM Phong p WHERE p.khachSan.id = :khachSanId")
    List<Phong> findByKhachSanId(@Param("khachSanId") Integer khachSanId);
    
    @Query("SELECT p FROM Phong p WHERE p.khachSan.id = :khachSanId " +
           "AND p.id NOT IN (SELECT dp.phong.id FROM DatPhong dp " +
           "WHERE dp.daHuy = false " +
           "AND NOT (dp.ngayTra < :ngayDen OR dp.ngayDen > :ngayTra))")
    List<Phong> findAvailableRooms(@Param("khachSanId") Integer khachSanId, 
                                  @Param("ngayDen") LocalDate ngayDen, 
                                  @Param("ngayTra") LocalDate ngayTra);
}
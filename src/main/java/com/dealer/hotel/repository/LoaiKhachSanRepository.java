package com.dealer.hotel.repository;

import com.dealer.hotel.entity.LoaiKhachSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiKhachSanRepository extends JpaRepository<LoaiKhachSan, Integer> {
    
    @Query("SELECT lks FROM LoaiKhachSan lks ORDER BY lks.ten")
    List<LoaiKhachSan> findAllOrderByTen();
}
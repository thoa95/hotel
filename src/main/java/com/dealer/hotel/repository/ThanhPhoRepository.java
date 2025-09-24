package com.dealer.hotel.repository;

import com.dealer.hotel.entity.ThanhPho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanhPhoRepository extends JpaRepository<ThanhPho, Integer> {
    
    @Query("SELECT tp FROM ThanhPho tp ORDER BY tp.ten")
    List<ThanhPho> findAllOrderByTen();
    
    @Query("SELECT tp FROM ThanhPho tp WHERE LOWER(tp.ten) LIKE LOWER(CONCAT('%', :ten, '%'))")
    List<ThanhPho> findByTenContainingIgnoreCase(String ten);
}
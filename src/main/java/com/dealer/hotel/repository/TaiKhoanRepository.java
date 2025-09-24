package com.dealer.hotel.repository;

import com.dealer.hotel.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    
    Optional<TaiKhoan> findByTenTaiKhoanAndMatKhau(String tenTaiKhoan, String matKhau);
    
    boolean existsByTenTaiKhoan(String tenTaiKhoan);
}
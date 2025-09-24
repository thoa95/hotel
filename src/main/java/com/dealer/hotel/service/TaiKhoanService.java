package com.dealer.hotel.service;

import com.dealer.hotel.entity.TaiKhoan;
import com.dealer.hotel.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaiKhoanService {
    
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<TaiKhoan> findAll() {
        return taiKhoanRepository.findAll();
    }
    
    public Optional<TaiKhoan> findById(String tenTaiKhoan) {
        return taiKhoanRepository.findById(tenTaiKhoan);
    }
    
    public TaiKhoan save(TaiKhoan taiKhoan) {
        // Encode password before saving
        if (taiKhoan.getMatKhau() != null && !taiKhoan.getMatKhau().startsWith("$2a$")) {
            taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        }
        return taiKhoanRepository.save(taiKhoan);
    }
    
    public void deleteById(String tenTaiKhoan) {
        taiKhoanRepository.deleteById(tenTaiKhoan);
    }
    
    public Optional<TaiKhoan> authenticate(String tenTaiKhoan, String matKhau) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(tenTaiKhoan);
        if (taiKhoan.isPresent() && passwordEncoder.matches(matKhau, taiKhoan.get().getMatKhau())) {
            return taiKhoan;
        }
        return Optional.empty();
    }
    
    public boolean existsByTenTaiKhoan(String tenTaiKhoan) {
        return taiKhoanRepository.existsByTenTaiKhoan(tenTaiKhoan);
    }
}
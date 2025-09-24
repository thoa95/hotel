package com.dealer.hotel.service;

import com.dealer.hotel.entity.Phong;
import com.dealer.hotel.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhongService {
    
    @Autowired
    private PhongRepository phongRepository;
    
    public List<Phong> findAll() {
        return phongRepository.findAllWithKhachSan();
    }
    
    public Optional<Phong> findById(Integer id) {
        return phongRepository.findById(id);
    }
    
    public Phong save(Phong phong) {
        return phongRepository.save(phong);
    }
    
    public void deleteById(Integer id) {
        phongRepository.deleteById(id);
    }
    
    public List<Phong> findByKhachSanId(Integer khachSanId) {
        return phongRepository.findByKhachSanId(khachSanId);
    }
    
    public List<Phong> findAvailableRooms(Integer khachSanId, LocalDate ngayDen, LocalDate ngayTra) {
        return phongRepository.findAvailableRooms(khachSanId, ngayDen, ngayTra);
    }
}
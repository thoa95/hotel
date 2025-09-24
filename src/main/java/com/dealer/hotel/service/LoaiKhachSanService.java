package com.dealer.hotel.service;

import com.dealer.hotel.entity.LoaiKhachSan;
import com.dealer.hotel.repository.LoaiKhachSanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoaiKhachSanService {
    
    @Autowired
    private LoaiKhachSanRepository loaiKhachSanRepository;
    
    public List<LoaiKhachSan> findAll() {
        return loaiKhachSanRepository.findAllOrderByTen();
    }
    
    public Optional<LoaiKhachSan> findById(Integer id) {
        return loaiKhachSanRepository.findById(id);
    }
    
    public LoaiKhachSan save(LoaiKhachSan loaiKhachSan) {
        return loaiKhachSanRepository.save(loaiKhachSan);
    }
    
    public void deleteById(Integer id) {
        loaiKhachSanRepository.deleteById(id);
    }
}
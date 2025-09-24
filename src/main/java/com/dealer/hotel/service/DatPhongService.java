package com.dealer.hotel.service;

import com.dealer.hotel.entity.DatPhong;
import com.dealer.hotel.repository.DatPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DatPhongService {
    
    @Autowired
    private DatPhongRepository datPhongRepository;
    
    public List<DatPhong> findAll() {
        return datPhongRepository.findAllWithDetails();
    }
    
    public Optional<DatPhong> findById(Integer id) {
        return datPhongRepository.findById(id);
    }
    
    public DatPhong save(DatPhong datPhong) {
        return datPhongRepository.save(datPhong);
    }
    
    public void deleteById(Integer id) {
        datPhongRepository.deleteById(id);
    }
    
    public List<DatPhong> findByTaiKhoan(String tenTaiKhoan) {
        return datPhongRepository.findByTaiKhoanTenTaiKhoan(tenTaiKhoan);
    }
    
    public boolean huyDatPhong(Integer id) {
        Optional<DatPhong> datPhong = datPhongRepository.findById(id);
        if (datPhong.isPresent()) {
            datPhong.get().setDaHuy(true);
            datPhongRepository.save(datPhong.get());
            return true;
        }
        return false;
    }
}
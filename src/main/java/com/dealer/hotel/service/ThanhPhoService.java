package com.dealer.hotel.service;

import com.dealer.hotel.entity.ThanhPho;
import com.dealer.hotel.repository.ThanhPhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ThanhPhoService {
    
    @Autowired
    private ThanhPhoRepository thanhPhoRepository;
    
    public List<ThanhPho> findAll() {
        return thanhPhoRepository.findAllOrderByTen();
    }
    
    public Optional<ThanhPho> findById(Integer id) {
        return thanhPhoRepository.findById(id);
    }
    
    public ThanhPho save(ThanhPho thanhPho) {
        return thanhPhoRepository.save(thanhPho);
    }
    
    public void deleteById(Integer id) {
        thanhPhoRepository.deleteById(id);
    }
    
    public List<ThanhPho> searchByTen(String ten) {
        return thanhPhoRepository.findByTenContainingIgnoreCase(ten);
    }
}
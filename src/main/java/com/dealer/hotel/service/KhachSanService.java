package com.dealer.hotel.service;

import com.dealer.hotel.entity.KhachSan;
import com.dealer.hotel.repository.KhachSanRepository;
import com.dealer.hotel.util.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class KhachSanService {
    
    @Autowired
    private KhachSanRepository khachSanRepository;
    
    public List<KhachSan> findAll() {
        return khachSanRepository.findAllWithDetails();
    }
    
    public Optional<KhachSan> findById(Integer id) {
        return khachSanRepository.findById(id);
    }
    
    public KhachSan save(KhachSan khachSan) {
        return khachSanRepository.save(khachSan);
    }
    
    public void deleteById(Integer id) {
        khachSanRepository.deleteById(id);
    }
    
    public List<KhachSan> findByThanhPhoId(Integer thanhPhoId) {
        return khachSanRepository.findByThanhPhoId(thanhPhoId);
    }
    
    public List<KhachSan> findByLoaiKhachSanId(Integer loaiKhachSanId) {
        return khachSanRepository.findByLoaiKhachSanId(loaiKhachSanId);
    }
    
    public List<KhachSan> searchByThanhPho(String tenThanhPho) {
        String tenKhongDau = VNCharacterUtils.removeAccent(tenThanhPho.toLowerCase());
        return khachSanRepository.findAllWithDetails().stream()
                .filter(ks -> VNCharacterUtils.removeAccent(ks.getTenThanhPho().toLowerCase())
                        .contains(tenKhongDau))
                .collect(Collectors.toList());
    }
    
    public List<KhachSan> filterKhachSan(List<KhachSan> khachSanList, 
                                        List<Integer> danhGiaFilter,
                                        List<String> loaiKhachSanFilter,
                                        List<Integer> buaAnFilter,
                                        List<Integer> cachTrungTamFilter,
                                        List<Boolean> giapBienFilter) {
        return khachSanList.stream()
                .filter(ks -> filterByDanhGia(ks, danhGiaFilter))
                .filter(ks -> filterByLoaiKhachSan(ks, loaiKhachSanFilter))
                .filter(ks -> filterByBuaAn(ks, buaAnFilter))
                .filter(ks -> filterByCachTrungTam(ks, cachTrungTamFilter))
                .filter(ks -> filterByGiapBien(ks, giapBienFilter))
                .collect(Collectors.toList());
    }
    
    private boolean filterByDanhGia(KhachSan ks, List<Integer> filter) {
        return filter == null || filter.isEmpty() || filter.contains(ks.getDanhGia());
    }
    
    private boolean filterByLoaiKhachSan(KhachSan ks, List<String> filter) {
        return filter == null || filter.isEmpty() || filter.contains(ks.getTenLoaiKhachSan());
    }
    
    private boolean filterByBuaAn(KhachSan ks, List<Integer> filter) {
        return filter == null || filter.isEmpty() || filter.contains(ks.getBuaAn());
    }
    
    private boolean filterByCachTrungTam(KhachSan ks, List<Integer> filter) {
        if (filter == null || filter.isEmpty()) return true;
        
        int khoangCach = ks.getCachTrungTam();
        return filter.stream().anyMatch(f -> {
            switch (f) {
                case 0: return khoangCach < 1000;
                case 1: return khoangCach < 3000;
                case 2: return khoangCach < 5000;
                default: return false;
            }
        });
    }
    
    private boolean filterByGiapBien(KhachSan ks, List<Boolean> filter) {
        return filter == null || filter.isEmpty() || filter.contains(ks.getGiapBien());
    }
}
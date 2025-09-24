package com.dealer.hotel.controller;

import com.dealer.hotel.entity.KhachSan;
import com.dealer.hotel.entity.LoaiKhachSan;
import com.dealer.hotel.entity.ThanhPho;
import com.dealer.hotel.service.KhachSanService;
import com.dealer.hotel.service.LoaiKhachSanService;
import com.dealer.hotel.service.ThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private ThanhPhoService thanhPhoService;
    
    @Autowired
    private LoaiKhachSanService loaiKhachSanService;
    
    @Autowired
    private KhachSanService khachSanService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<ThanhPho> danhSachThanhPho = thanhPhoService.findAll();
        List<LoaiKhachSan> danhSachLoaiKhachSan = loaiKhachSanService.findAll();
        List<KhachSan> danhSachKhachSan = khachSanService.findAll();
        
        model.addAttribute("danhSachThanhPho", danhSachThanhPho);
        model.addAttribute("danhSachLoaiKhachSan", danhSachLoaiKhachSan);
        model.addAttribute("danhSachKhachSan", danhSachKhachSan.subList(0, Math.min(4, danhSachKhachSan.size())));
        
        return "index";
    }
    
    @PostMapping("/tim-kiem")
    public String timKiem(@RequestParam("tenThanhPho") String tenThanhPho, Model model) {
        List<KhachSan> ketQuaTimKiem = khachSanService.searchByThanhPho(tenThanhPho);
        
        model.addAttribute("danhSachKhachSan", ketQuaTimKiem);
        model.addAttribute("tuKhoaTimKiem", tenThanhPho);
        
        return "danh-sach-khach-san";
    }
    
    @GetMapping("/lien-he")
    public String lienHe() {
        return "lien-he";
    }
    
    @GetMapping("/tin-tuc")
    public String tinTuc() {
        return "tin-tuc";
    }
}
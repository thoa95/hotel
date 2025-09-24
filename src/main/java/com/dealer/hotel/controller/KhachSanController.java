package com.dealer.hotel.controller;

import com.dealer.hotel.entity.KhachSan;
import com.dealer.hotel.entity.LoaiKhachSan;
import com.dealer.hotel.entity.Phong;
import com.dealer.hotel.entity.ThanhPho;
import com.dealer.hotel.service.KhachSanService;
import com.dealer.hotel.service.LoaiKhachSanService;
import com.dealer.hotel.service.PhongService;
import com.dealer.hotel.service.ThanhPhoService;
import com.dealer.hotel.util.BuaAnEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/khach-san")
public class KhachSanController {
    
    @Autowired
    private KhachSanService khachSanService;
    
    @Autowired
    private ThanhPhoService thanhPhoService;
    
    @Autowired
    private LoaiKhachSanService loaiKhachSanService;
    
    @Autowired
    private PhongService phongService;
    
    @GetMapping
    public String danhSachKhachSan(Model model) {
        List<KhachSan> danhSachKhachSan = khachSanService.findAll();
        List<ThanhPho> danhSachThanhPho = thanhPhoService.findAll();
        List<LoaiKhachSan> danhSachLoaiKhachSan = loaiKhachSanService.findAll();
        
        model.addAttribute("danhSachKhachSan", danhSachKhachSan);
        model.addAttribute("danhSachThanhPho", danhSachThanhPho);
        model.addAttribute("danhSachLoaiKhachSan", danhSachLoaiKhachSan);
        model.addAttribute("danhSachBuaAn", BuaAnEnum.getAllBuaAn());
        
        return "danh-sach-khach-san";
    }
    
    @GetMapping("/thanh-pho/{id}")
    public String khachSanTheoThanhPho(@PathVariable Integer id, Model model) {
        List<KhachSan> danhSachKhachSan = khachSanService.findByThanhPhoId(id);
        Optional<ThanhPho> thanhPho = thanhPhoService.findById(id);
        
        model.addAttribute("danhSachKhachSan", danhSachKhachSan);
        model.addAttribute("thanhPho", thanhPho.orElse(null));
        
        return "danh-sach-khach-san";
    }
    
    @GetMapping("/loai/{id}")
    public String khachSanTheoLoai(@PathVariable Integer id, Model model) {
        List<KhachSan> danhSachKhachSan = khachSanService.findByLoaiKhachSanId(id);
        Optional<LoaiKhachSan> loaiKhachSan = loaiKhachSanService.findById(id);
        
        model.addAttribute("danhSachKhachSan", danhSachKhachSan);
        model.addAttribute("loaiKhachSan", loaiKhachSan.orElse(null));
        
        return "danh-sach-khach-san";
    }
    
    @GetMapping("/{id}")
    public String chiTietKhachSan(@PathVariable Integer id, Model model) {
        Optional<KhachSan> khachSan = khachSanService.findById(id);
        if (khachSan.isPresent()) {
            List<Phong> danhSachPhong = phongService.findByKhachSanId(id);
            
            model.addAttribute("khachSan", khachSan.get());
            model.addAttribute("danhSachPhong", danhSachPhong);
            model.addAttribute("danhSachBuaAn", BuaAnEnum.getAllBuaAn());
            
            return "chi-tiet-khach-san";
        }
        return "redirect:/khach-san";
    }
    
    @GetMapping("/{id}/phong-trong")
    public String kiemTraPhongTrong(@PathVariable Integer id,
                                   @RequestParam("ngayDen") String ngayDenStr,
                                   @RequestParam("ngayTra") String ngayTraStr,
                                   Model model) {
        try {
            LocalDate ngayDen = LocalDate.parse(ngayDenStr);
            LocalDate ngayTra = LocalDate.parse(ngayTraStr);
            
            Optional<KhachSan> khachSan = khachSanService.findById(id);
            if (khachSan.isPresent()) {
                List<Phong> phongTrong = phongService.findAvailableRooms(id, ngayDen, ngayTra);
                
                model.addAttribute("khachSan", khachSan.get());
                model.addAttribute("danhSachPhong", phongTrong);
                model.addAttribute("ngayDen", ngayDen);
                model.addAttribute("ngayTra", ngayTra);
                model.addAttribute("daKiemTraPhongTrong", true);
                
                return "chi-tiet-khach-san";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ngày nhập vào không hợp lệ!");
        }
        
        return "redirect:/khach-san/" + id;
    }
}
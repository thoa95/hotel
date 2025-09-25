package com.dealer.hotel.controller;

import com.dealer.hotel.entity.DatPhong;
import com.dealer.hotel.entity.Phong;
import com.dealer.hotel.entity.TaiKhoan;
import com.dealer.hotel.service.DatPhongService;
import com.dealer.hotel.service.PhongService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@RequestMapping("/dat-phong")
public class BookingController {
    
    @Autowired
    private PhongService phongService;
    
    @Autowired
    private DatPhongService datPhongService;
    
    @GetMapping("/{phongId}")
    public String datPhong(@PathVariable Integer phongId,
                          @RequestParam("ngayDen") String ngayDenStr,
                          @RequestParam("ngayTra") String ngayTraStr,
                          HttpSession session,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
        if (taiKhoan == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập để đặt phòng!");
            return "redirect:/dang-nhap";
        }
        
        Optional<Phong> phong = phongService.findById(phongId);
        if (phong.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Phòng không tồn tại!");
            return "redirect:/khach-san";
        }
        
        try {
            LocalDate ngayDen = LocalDate.parse(ngayDenStr);
            LocalDate ngayTra = LocalDate.parse(ngayTraStr);
            
            if (ngayDen.isBefore(LocalDate.now()) || ngayTra.isBefore(ngayDen)) {
                redirectAttributes.addFlashAttribute("error", "Ngày không hợp lệ!");
                return "redirect:/khach-san/" + phong.get().getKhachSan().getId();
            }
            
            long soNgay = ChronoUnit.DAYS.between(ngayDen, ngayTra);
            int thanhTien = (int) (soNgay * phong.get().getGiaThue());
            
            DatPhong datPhong = new DatPhong();
            datPhong.setTaiKhoan(taiKhoan);
            datPhong.setPhong(phong.get());
            datPhong.setNgayDat(LocalDate.now());
            datPhong.setNgayDen(ngayDen);
            datPhong.setNgayTra(ngayTra);
            datPhong.setThanhTien(thanhTien);
            datPhong.setDaHuy(false);
            
            datPhongService.save(datPhong);
            
            redirectAttributes.addFlashAttribute("success", "Đặt phòng thành công!");
            return "redirect:/ca-nhan/lich-su";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi đặt phòng!");
            return "redirect:/khach-san/" + phong.get().getKhachSan().getId();
        }
    }
}
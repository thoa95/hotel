package com.dealer.hotel.controller;

import com.dealer.hotel.entity.DatPhong;
import com.dealer.hotel.entity.KhachSan;
import com.dealer.hotel.entity.TaiKhoan;
import com.dealer.hotel.service.DatPhongService;
import com.dealer.hotel.service.KhachSanService;
import com.dealer.hotel.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/ca-nhan")
public class UserController {
    
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @Autowired
    private DatPhongService datPhongService;
    
    @Autowired
    private KhachSanService khachSanService;
    
    @GetMapping
    public String caNhan(HttpSession session, Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
        if (taiKhoan == null) {
            return "redirect:/dang-nhap";
        }
        
        // Khách sạn gợi ý ngẫu nhiên
        List<KhachSan> danhSachKhachSan = khachSanService.findAll();
        if (!danhSachKhachSan.isEmpty()) {
            Random random = new Random();
            KhachSan khachSanGoiY = danhSachKhachSan.get(random.nextInt(danhSachKhachSan.size()));
            model.addAttribute("khachSanGoiY", khachSanGoiY);
        }
        
        model.addAttribute("taiKhoan", taiKhoan);
        return "ca-nhan";
    }
    
    @PostMapping("/cap-nhat")
    public String capNhatThongTin(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
                                 BindingResult bindingResult,
                                 @RequestParam("nhapLaiMatKhau") String nhapLaiMatKhau,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        
        if (bindingResult.hasErrors()) {
            return "ca-nhan";
        }
        
        if (!taiKhoan.getMatKhau().equals(nhapLaiMatKhau)) {
            model.addAttribute("error", "Mật khẩu không khớp!");
            return "ca-nhan";
        }
        
        TaiKhoan taiKhoanCapNhat = taiKhoanService.save(taiKhoan);
        session.setAttribute("taiKhoan", taiKhoanCapNhat);
        
        redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công!");
        return "redirect:/ca-nhan";
    }
    
    @GetMapping("/lich-su")
    public String lichSu(HttpSession session, Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
        if (taiKhoan == null) {
            return "redirect:/dang-nhap";
        }
        
        List<DatPhong> lichSuDatPhong = datPhongService.findByTaiKhoan(taiKhoan.getTenTaiKhoan());
        
        model.addAttribute("lichSuDatPhong", lichSuDatPhong);
        return "lich-su";
    }
    
    @PostMapping("/huy-dat-phong/{id}")
    public String huyDatPhong(@PathVariable Integer id, 
                             RedirectAttributes redirectAttributes) {
        
        if (datPhongService.huyDatPhong(id)) {
            redirectAttributes.addFlashAttribute("success", "Hủy đặt phòng thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Hủy đặt phòng thất bại!");
        }
        
        return "redirect:/ca-nhan/lich-su";
    }
}
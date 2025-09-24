package com.dealer.hotel.controller;

import com.dealer.hotel.entity.TaiKhoan;
import com.dealer.hotel.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthController {
    
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @GetMapping("/dang-nhap")
    public String dangNhap(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "dang-nhap";
    }
    
    @PostMapping("/dang-nhap")
    public String xuLyDangNhap(@RequestParam("tenTaiKhoan") String tenTaiKhoan,
                              @RequestParam("matKhau") String matKhau,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        
        Optional<TaiKhoan> taiKhoan = taiKhoanService.authenticate(tenTaiKhoan, matKhau);
        
        if (taiKhoan.isPresent()) {
            session.setAttribute("taiKhoan", taiKhoan.get());
            redirectAttributes.addFlashAttribute("success", "Đăng nhập thành công!");
            
            if (taiKhoan.get().getIsAdmin()) {
                return "redirect:/admin";
            } else {
                return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Sai tên tài khoản hoặc mật khẩu!");
            return "redirect:/dang-nhap";
        }
    }
    
    @GetMapping("/dang-ky")
    public String dangKy(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "dang-ky";
    }
    
    @PostMapping("/dang-ky")
    public String xuLyDangKy(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
                            BindingResult bindingResult,
                            @RequestParam("nhapLaiMatKhau") String nhapLaiMatKhau,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        
        if (bindingResult.hasErrors()) {
            return "dang-ky";
        }
        
        if (!taiKhoan.getMatKhau().equals(nhapLaiMatKhau)) {
            model.addAttribute("error", "Mật khẩu không khớp!");
            return "dang-ky";
        }
        
        if (taiKhoanService.existsByTenTaiKhoan(taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("error", "Tên tài khoản đã được sử dụng!");
            return "dang-ky";
        }
        
        taiKhoan.setIsAdmin(false);
        taiKhoanService.save(taiKhoan);
        
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công!");
        return "redirect:/dang-nhap";
    }
    
    @GetMapping("/dang-xuat")
    public String dangXuat(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Đăng xuất thành công!");
        return "redirect:/";
    }
}
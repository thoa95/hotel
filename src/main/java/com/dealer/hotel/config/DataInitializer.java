package com.dealer.hotel.config;

import com.dealer.hotel.entity.*;
import com.dealer.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ThanhPhoService thanhPhoService;
    
    @Autowired
    private LoaiKhachSanService loaiKhachSanService;
    
    @Autowired
    private KhachSanService khachSanService;
    
    @Autowired
    private PhongService phongService;
    
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
    
    private void initializeData() {
        // Tạo thành phố
        ThanhPho haNoi = new ThanhPho("Hà Nội", "Thủ đô của Việt Nam", 
            "https://images.pexels.com/photos/2161467/pexels-photo-2161467.jpeg");
        ThanhPho hoChiMinh = new ThanhPho("Hồ Chí Minh", "Thành phố lớn nhất Việt Nam", 
            "https://images.pexels.com/photos/2161467/pexels-photo-2161467.jpeg");
        ThanhPho daNang = new ThanhPho("Đà Nẵng", "Thành phố biển đẹp", 
            "https://images.pexels.com/photos/1450360/pexels-photo-1450360.jpeg");
        
        haNoi = thanhPhoService.save(haNoi);
        hoChiMinh = thanhPhoService.save(hoChiMinh);
        daNang = thanhPhoService.save(daNang);
        
        // Tạo loại khách sạn
        LoaiKhachSan resort = new LoaiKhachSan("Resort", "Khu nghỉ dưỡng cao cấp", 
            "https://images.pexels.com/photos/261102/pexels-photo-261102.jpeg");
        LoaiKhachSan hotel = new LoaiKhachSan("Khách sạn", "Khách sạn tiêu chuẩn", 
            "https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg");
        LoaiKhachSan villa = new LoaiKhachSan("Villa", "Biệt thự nghỉ dưỡng", 
            "https://images.pexels.com/photos/1396122/pexels-photo-1396122.jpeg");
        
        resort = loaiKhachSanService.save(resort);
        hotel = loaiKhachSanService.save(hotel);
        villa = loaiKhachSanService.save(villa);
        
        // Tạo khách sạn
        KhachSan ks1 = new KhachSan();
        ks1.setTen("Khách sạn Hà Nội Luxury");
        ks1.setDiaChi("123 Hoàn Kiếm, Hà Nội");
        ks1.setSoDienThoai("0243456789");
        ks1.setCachTrungTam(500);
        ks1.setMoTa("Khách sạn sang trọng tại trung tâm Hà Nội");
        ks1.setGiapBien(false);
        ks1.setDanhGia(5);
        ks1.setBuaAn(3);
        ks1.setThanhPho(haNoi);
        ks1.setLoaiKhachSan(hotel);
        ks1 = khachSanService.save(ks1);
        
        KhachSan ks2 = new KhachSan();
        ks2.setTen("Resort Đà Nẵng Beach");
        ks2.setDiaChi("456 Bãi biển Mỹ Khê, Đà Nẵng");
        ks2.setSoDienThoai("0236789123");
        ks2.setCachTrungTam(2000);
        ks2.setMoTa("Resort nghỉ dưỡng bên bờ biển");
        ks2.setGiapBien(true);
        ks2.setDanhGia(4);
        ks2.setBuaAn(4);
        ks2.setThanhPho(daNang);
        ks2.setLoaiKhachSan(resort);
        ks2 = khachSanService.save(ks2);
        
        // Tạo phòng
        Phong phong1 = new Phong();
        phong1.setTen("Phòng Deluxe");
        phong1.setDienTich(30);
        phong1.setGiaThue(1500);
        phong1.setTienNghi("TV, Điều hòa, Minibar");
        phong1.setMoTa("Phòng cao cấp với view thành phố");
        phong1.setLoaiGiuong(1);
        phong1.setKhachSan(ks1);
        phongService.save(phong1);
        
        Phong phong2 = new Phong();
        phong2.setTen("Phòng Standard");
        phong2.setDienTich(25);
        phong2.setGiaThue(1000);
        phong2.setTienNghi("TV, Điều hòa");
        phong2.setMoTa("Phòng tiêu chuẩn thoải mái");
        phong2.setLoaiGiuong(0);
        phong2.setKhachSan(ks1);
        phongService.save(phong2);
        
        Phong phong3 = new Phong();
        phong3.setTen("Suite Ocean View");
        phong3.setDienTich(50);
        phong3.setGiaThue(3000);
        phong3.setTienNghi("TV, Điều hòa, Jacuzzi, Ban công");
        phong3.setMoTa("Suite cao cấp với view biển tuyệt đẹp");
        phong3.setLoaiGiuong(1);
        phong3.setKhachSan(ks2);
        phongService.save(phong3);
        
        // Tạo tài khoản admin
        TaiKhoan admin = new TaiKhoan();
        admin.setTenTaiKhoan("admin");
        admin.setMatKhau("admin123");
        admin.setHoTen("Quản trị viên");
        admin.setGioiTinh(true);
        admin.setSoDienThoai("0123456789");
        admin.setEmail("admin@qlks.com");
        admin.setIsAdmin(true);
        taiKhoanService.save(admin);
        
        // Tạo tài khoản user
        TaiKhoan user = new TaiKhoan();
        user.setTenTaiKhoan("user");
        user.setMatKhau("user123");
        user.setHoTen("Người dùng");
        user.setGioiTinh(false);
        user.setSoDienThoai("0987654321");
        user.setEmail("user@qlks.com");
        user.setIsAdmin(false);
        taiKhoanService.save(user);
    }
}
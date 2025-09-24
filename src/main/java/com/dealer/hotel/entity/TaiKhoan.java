package com.dealer.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
    
    @Id
    @NotBlank(message = "Tên tài khoản không được để trống")
    @Size(max = 50, message = "Tên tài khoản không được quá 50 ký tự")
    @Column(name = "TenTaiKhoan")
    private String tenTaiKhoan;
    
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    @Column(name = "MatKhau")
    private String matKhau;
    
    @Size(max = 50, message = "Họ tên không được quá 50 ký tự")
    @Column(name = "HoTen")
    private String hoTen;
    
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;
    
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Số điện thoại không hợp lệ")
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    
    @Email(message = "Email không hợp lệ")
    @Size(max = 50, message = "Email không được quá 50 ký tự")
    @Column(name = "Email")
    private String email;
    
    @Column(name = "IsAdmin")
    private Boolean isAdmin = false;
    
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DatPhong> danhSachDatPhong;
    
    // Constructors
    public TaiKhoan() {}
    
    public TaiKhoan(String tenTaiKhoan, String matKhau, String hoTen, Boolean gioiTinh, 
                   String soDienThoai, String email, Boolean isAdmin) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    
    // Getters and Setters
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }
    
    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public Boolean getGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public List<DatPhong> getDanhSachDatPhong() {
        return danhSachDatPhong;
    }
    
    public void setDanhSachDatPhong(List<DatPhong> danhSachDatPhong) {
        this.danhSachDatPhong = danhSachDatPhong;
    }
    
    // Helper methods
    public String getGioiTinhText() {
        return gioiTinh != null && gioiTinh ? "Nam" : "Nữ";
    }
}
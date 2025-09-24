package com.dealer.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "LoaiKhachSan")
public class LoaiKhachSan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Tên loại khách sạn không được để trống")
    @Size(max = 100, message = "Tên loại khách sạn không được quá 100 ký tự")
    @Column(name = "Ten")
    private String ten;
    
    @Size(max = 200, message = "Mô tả không được quá 200 ký tự")
    @Column(name = "MoTa")
    private String moTa;
    
    @Size(max = 200, message = "URL hình ảnh không được quá 200 ký tự")
    @Column(name = "UrlHinhAnh")
    private String urlHinhAnh;
    
    @OneToMany(mappedBy = "loaiKhachSan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KhachSan> danhSachKhachSan;
    
    // Constructors
    public LoaiKhachSan() {}
    
    public LoaiKhachSan(String ten, String moTa, String urlHinhAnh) {
        this.ten = ten;
        this.moTa = moTa;
        this.urlHinhAnh = urlHinhAnh;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTen() {
        return ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public String getUrlHinhAnh() {
        return urlHinhAnh;
    }
    
    public void setUrlHinhAnh(String urlHinhAnh) {
        this.urlHinhAnh = urlHinhAnh;
    }
    
    public List<KhachSan> getDanhSachKhachSan() {
        return danhSachKhachSan;
    }
    
    public void setDanhSachKhachSan(List<KhachSan> danhSachKhachSan) {
        this.danhSachKhachSan = danhSachKhachSan;
    }
    
    public int getSoKhachSan() {
        return danhSachKhachSan != null ? danhSachKhachSan.size() : 0;
    }
}
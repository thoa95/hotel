package com.dealer.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Phong")
public class Phong {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Tên phòng không được để trống")
    @Size(max = 100, message = "Tên phòng không được quá 100 ký tự")
    @Column(name = "Ten")
    private String ten;
    
    @Min(value = 1, message = "Diện tích phải lớn hơn 0")
    @Column(name = "DienTich")
    private Integer dienTich;
    
    @Min(value = 0, message = "Giá thuê phải lớn hơn hoặc bằng 0")
    @Column(name = "GiaThue")
    private Integer giaThue;
    
    @Size(max = 200, message = "Tiện nghi không được quá 200 ký tự")
    @Column(name = "TienNghi")
    private String tienNghi;
    
    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự")
    @Column(name = "MoTa")
    private String moTa;
    
    @Min(value = 0, message = "Loại giường phải là 0 hoặc 1")
    @Max(value = 1, message = "Loại giường phải là 0 hoặc 1")
    @Column(name = "LoaiGiuong")
    private Integer loaiGiuong;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKhachSan", referencedColumnName = "id")
    private KhachSan khachSan;
    
    @OneToMany(mappedBy = "phong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DatPhong> danhSachDatPhong;
    
    // Constructors
    public Phong() {}
    
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
    
    public Integer getDienTich() {
        return dienTich;
    }
    
    public void setDienTich(Integer dienTich) {
        this.dienTich = dienTich;
    }
    
    public Integer getGiaThue() {
        return giaThue;
    }
    
    public void setGiaThue(Integer giaThue) {
        this.giaThue = giaThue;
    }
    
    public String getTienNghi() {
        return tienNghi;
    }
    
    public void setTienNghi(String tienNghi) {
        this.tienNghi = tienNghi;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public Integer getLoaiGiuong() {
        return loaiGiuong;
    }
    
    public void setLoaiGiuong(Integer loaiGiuong) {
        this.loaiGiuong = loaiGiuong;
    }
    
    public KhachSan getKhachSan() {
        return khachSan;
    }
    
    public void setKhachSan(KhachSan khachSan) {
        this.khachSan = khachSan;
    }
    
    public List<DatPhong> getDanhSachDatPhong() {
        return danhSachDatPhong;
    }
    
    public void setDanhSachDatPhong(List<DatPhong> danhSachDatPhong) {
        this.danhSachDatPhong = danhSachDatPhong;
    }
    
    // Helper methods
    public String getTenKhachSan() {
        return khachSan != null ? khachSan.getTen() : "";
    }
    
    public String getLoaiGiuongText() {
        return loaiGiuong != null && loaiGiuong == 1 ? "Đôi" : "Đơn";
    }
}
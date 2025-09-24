package com.dealer.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "KhachSan")
public class KhachSan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Tên khách sạn không được để trống")
    @Size(max = 100, message = "Tên khách sạn không được quá 100 ký tự")
    @Column(name = "Ten")
    private String ten;
    
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 100, message = "Địa chỉ không được quá 100 ký tự")
    @Column(name = "DiaChi")
    private String diaChi;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải có 10 chữ số")
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    
    @Min(value = 0, message = "Khoảng cách phải lớn hơn hoặc bằng 0")
    @Column(name = "CachTrungTam")
    private Integer cachTrungTam;
    
    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự")
    @Column(name = "MoTa")
    private String moTa;
    
    @Column(name = "GiapBien")
    private Boolean giapBien = false;
    
    @Min(value = 0, message = "Đánh giá phải từ 0 đến 5")
    @Max(value = 5, message = "Đánh giá phải từ 0 đến 5")
    @Column(name = "DanhGia")
    private Integer danhGia;
    
    @Min(value = 0, message = "Bữa ăn phải từ 0 đến 4")
    @Max(value = 4, message = "Bữa ăn phải từ 0 đến 4")
    @Column(name = "BuaAn")
    private Integer buaAn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdThanhPho", referencedColumnName = "id")
    private ThanhPho thanhPho;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdLoaiKhachSan", referencedColumnName = "id")
    private LoaiKhachSan loaiKhachSan;
    
    @OneToMany(mappedBy = "khachSan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phong> danhSachPhong;
    
    // Constructors
    public KhachSan() {}
    
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
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public Integer getCachTrungTam() {
        return cachTrungTam;
    }
    
    public void setCachTrungTam(Integer cachTrungTam) {
        this.cachTrungTam = cachTrungTam;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public Boolean getGiapBien() {
        return giapBien;
    }
    
    public void setGiapBien(Boolean giapBien) {
        this.giapBien = giapBien;
    }
    
    public Integer getDanhGia() {
        return danhGia;
    }
    
    public void setDanhGia(Integer danhGia) {
        this.danhGia = danhGia;
    }
    
    public Integer getBuaAn() {
        return buaAn;
    }
    
    public void setBuaAn(Integer buaAn) {
        this.buaAn = buaAn;
    }
    
    public ThanhPho getThanhPho() {
        return thanhPho;
    }
    
    public void setThanhPho(ThanhPho thanhPho) {
        this.thanhPho = thanhPho;
    }
    
    public LoaiKhachSan getLoaiKhachSan() {
        return loaiKhachSan;
    }
    
    public void setLoaiKhachSan(LoaiKhachSan loaiKhachSan) {
        this.loaiKhachSan = loaiKhachSan;
    }
    
    public List<Phong> getDanhSachPhong() {
        return danhSachPhong;
    }
    
    public void setDanhSachPhong(List<Phong> danhSachPhong) {
        this.danhSachPhong = danhSachPhong;
    }
    
    // Helper methods
    public String getTenThanhPho() {
        return thanhPho != null ? thanhPho.getTen() : "";
    }
    
    public String getTenLoaiKhachSan() {
        return loaiKhachSan != null ? loaiKhachSan.getTen() : "";
    }
    
    public String getUrlHinhAnhThanhPho() {
        return thanhPho != null ? thanhPho.getUrlHinhAnh() : "";
    }
}
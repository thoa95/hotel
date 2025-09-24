package com.dealer.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "DatPhong")
public class DatPhong {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TaiKhoan", referencedColumnName = "tenTaiKhoan")
    private TaiKhoan taiKhoan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPhong", referencedColumnName = "id")
    private Phong phong;
    
    @NotNull(message = "Ngày đặt không được để trống")
    @Column(name = "NgayDat")
    private LocalDate ngayDat;
    
    @NotNull(message = "Ngày đến không được để trống")
    @Column(name = "NgayDen")
    private LocalDate ngayDen;
    
    @NotNull(message = "Ngày trả không được để trống")
    @Column(name = "NgayTra")
    private LocalDate ngayTra;
    
    @Size(max = 200, message = "Dịch vụ không được quá 200 ký tự")
    @Column(name = "DichVu")
    private String dichVu;
    
    @Size(max = 200, message = "Ghi chú không được quá 200 ký tự")
    @Column(name = "GhiChu")
    private String ghiChu;
    
    @Min(value = 0, message = "Thành tiền phải lớn hơn hoặc bằng 0")
    @Column(name = "ThanhTien")
    private Integer thanhTien;
    
    @Column(name = "DaHuy")
    private Boolean daHuy = false;
    
    // Constructors
    public DatPhong() {}
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }
    
    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    
    public Phong getPhong() {
        return phong;
    }
    
    public void setPhong(Phong phong) {
        this.phong = phong;
    }
    
    public LocalDate getNgayDat() {
        return ngayDat;
    }
    
    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }
    
    public LocalDate getNgayDen() {
        return ngayDen;
    }
    
    public void setNgayDen(LocalDate ngayDen) {
        this.ngayDen = ngayDen;
    }
    
    public LocalDate getNgayTra() {
        return ngayTra;
    }
    
    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }
    
    public String getDichVu() {
        return dichVu;
    }
    
    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }
    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public Integer getThanhTien() {
        return thanhTien;
    }
    
    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    public Boolean getDaHuy() {
        return daHuy;
    }
    
    public void setDaHuy(Boolean daHuy) {
        this.daHuy = daHuy;
    }
    
    // Helper methods
    public String getTenTaiKhoan() {
        return taiKhoan != null ? taiKhoan.getTenTaiKhoan() : "";
    }
    
    public String getTenPhong() {
        return phong != null ? phong.getTen() : "";
    }
}
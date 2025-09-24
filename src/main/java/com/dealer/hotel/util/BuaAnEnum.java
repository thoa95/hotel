package com.dealer.hotel.util;

public enum BuaAnEnum {
    KHONG_CO(0, "Không có"),
    BUA_SANG(1, "Bữa Sáng"),
    BUA_SANG_VA_TRUA(2, "Bữa Sáng Và Trưa"),
    BUA_SANG_VA_TOI(3, "Bữa Sáng Và Tối"),
    CA_BA_BUA(4, "Cả Ba Bữa");
    
    private final int id;
    private final String ten;
    
    BuaAnEnum(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTen() {
        return ten;
    }
    
    public static String getTenById(int id) {
        for (BuaAnEnum buaAn : values()) {
            if (buaAn.getId() == id) {
                return buaAn.getTen();
            }
        }
        return "Không xác định";
    }
    
    public static BuaAnEnum[] getAllBuaAn() {
        return values();
    }
}
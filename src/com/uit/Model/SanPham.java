package com.uit.Model;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String dvt;
    private String nuocSX;
    private int gia;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String dvt, String nuocSX, int gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dvt = dvt;
        this.nuocSX = nuocSX;
        this.gia = gia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getNuocSX() {
        return nuocSX;
    }

    public void setNuocSX(String nuocSX) {
        this.nuocSX = nuocSX;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}

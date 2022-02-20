package com.uit.Model;

public class ChiTietHoaDonFull {
    private String maSP;
    private String tenSP;
    private String dvt;
    private String nuocSX;
    private int SL;
    private int gia;
    private int triGia;

    public ChiTietHoaDonFull() {
    }

    public ChiTietHoaDonFull(String maSP, String tenSP, String dvt, String nuocSX, int SL, int gia, int triGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dvt = dvt;
        this.nuocSX = nuocSX;
        this.SL = SL;
        this.gia = gia;
        this.triGia = triGia;
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

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getTriGia() {
        return triGia;
    }

    public void setTriGia(int triGia) {
        this.triGia = triGia;
    }
}

package com.uit.Model;

public class ChiTietHoaDon {
    private int soHD;
    private String maSP;
    private int soLuong;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int soHD, String maSP, int soLuong) {
        this.soHD = soHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

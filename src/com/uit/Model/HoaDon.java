package com.uit.Model;

public class HoaDon {
    private int soHD;
    private String ngayHD;
    private String maKH;
    private String maNV;
    private int triGia;

    public HoaDon() {
    }

    public HoaDon(int soHD, String ngayHD, String maKH, String maNV) {
        this.soHD = soHD;
        this.ngayHD = ngayHD;
        this.maKH = maKH;
        this.maNV = maNV;
    }

    public HoaDon(int soHD, String ngayHD, String maKH, String maNV, int triGia) {
        this.soHD = soHD;
        this.ngayHD = ngayHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.triGia = triGia;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getTriGia() {
        return triGia;
    }

    public void setTriGia(int triGia) {
        this.triGia = triGia;
    }
}

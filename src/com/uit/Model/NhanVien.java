package com.uit.Model;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String soDT;
    private String ngayVaoLam;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String soDT, String ngayVaoLam) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }
}

package com.uit.Model;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String soDT;
    private String ngaySinh;
    private String ngayDangKy;
    private int doanhSo;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String diaChi, String soDT, String ngaySinh, String ngayDangKy, int doanhSo) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.ngayDangKy = ngayDangKy;
        this.doanhSo = doanhSo;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public int getDoanhSo() {
        return doanhSo;
    }

    public void setDoanhSo(int doanhSo) {
        this.doanhSo = doanhSo;
    }
}

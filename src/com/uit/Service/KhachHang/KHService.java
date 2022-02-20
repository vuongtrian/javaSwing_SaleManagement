package com.uit.Service.KhachHang;

import com.uit.Model.KhachHang;

import java.util.List;

public interface KHService {
    public void insertKH(KhachHang newKH);

    public void updateKH(KhachHang updatedKH);

    public void updateDoanhSo (String maKH, int doanhSo);

    public void deleteKH(String maKH);

    public List<KhachHang> getAllKH();

    public boolean checkMaKH(String maKH);
}

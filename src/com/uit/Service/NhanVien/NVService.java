package com.uit.Service.NhanVien;

import com.uit.Model.NhanVien;

import java.util.List;

public interface NVService {
    public void insertNV(NhanVien newNV);
    public void updateNV(NhanVien updatedNV);
    public void deleteNV(String maNV);
    public List<NhanVien> getAllNV();
    public boolean checkMaNV (String maNV);
}

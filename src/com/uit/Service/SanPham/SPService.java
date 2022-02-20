package com.uit.Service.SanPham;

import com.uit.Model.SanPham;

import java.util.List;

public interface SPService {
    public void insertSP(SanPham newSP);

    public void updateSP(SanPham updatedSP);

    public void deleteSP(String maSP);

    public List<SanPham> getAllSP();

    public List<String> getListDVT();

    public List<String> getListNuocSX();

    public boolean checkMaSP (String maSP);

    public int getGiaSP (String maSP);
}

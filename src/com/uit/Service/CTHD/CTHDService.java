package com.uit.Service.CTHD;

import com.uit.Model.ChiTietHoaDon;
import com.uit.Model.ChiTietHoaDonFull;

import java.util.List;

public interface CTHDService {
    public void insertCTHD(ChiTietHoaDon newCTHD);

    public void updateCTHD(ChiTietHoaDon updatedCTHD);

    public void deleteCTHD(int soHD, String maSP);

    public void deleteAllCTHD(int soHD);

    public List<ChiTietHoaDon> getAllCTHD();

    public List<ChiTietHoaDonFull> getCTHD(int soHD);

    public int tongTriGia (int soHD);
}

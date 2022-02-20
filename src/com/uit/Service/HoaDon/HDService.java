package com.uit.Service.HoaDon;

import com.uit.Model.HoaDon;

import java.util.List;

public interface HDService {
    public void insertHD(HoaDon newHD);

    public void updateHD(HoaDon updatedHD);

    public void deleteHD(int soHD);

    public List<HoaDon> getAllHD();

    public boolean checkSoHD (int soHD);

    public boolean checkMaKH (String maKH);

    public void updateTriGia (int soHD, int triGia);

    public int tongTriGiaKhachHang (String maKH);
}

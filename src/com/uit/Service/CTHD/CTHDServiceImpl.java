package com.uit.Service.CTHD;

import com.uit.Dao.MyConnection;
import com.uit.Model.ChiTietHoaDon;
import com.uit.Model.ChiTietHoaDonFull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CTHDServiceImpl implements CTHDService {
    @Override
    public void insertCTHD(ChiTietHoaDon newCTHD) {
        try {
            String sql = "insert into CTHD (SOHD, MASP, SL) values (?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, newCTHD.getSoHD());
            psta.setString(2, newCTHD.getMaSP());
            psta.setInt(3, newCTHD.getSoLuong());

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCTHD(ChiTietHoaDon updatedCTHD) {
        try {
            String sql = "update CTHD set SOHD = ?, MASP = ?, SL = ? where SOHD = ? and MASP = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, updatedCTHD.getSoHD());
            psta.setString(2, updatedCTHD.getMaSP());
            psta.setInt(3, updatedCTHD.getSoLuong());
            psta.setInt(4, updatedCTHD.getSoHD());
            psta.setString(5, updatedCTHD.getMaSP());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCTHD(int soHD, String maSP) {
        try {
            String sql = "delete from CTHD where SOHD = " + soHD + " and MASP = '" + maSP + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllCTHD(int soHD) {
        try {
            String sql = "delete from CTHD where SOHD = " + soHD;

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChiTietHoaDon> getAllCTHD() {
        List<ChiTietHoaDon> allHD = new ArrayList<>();

        try {
            String sql = "select SOHD, MASP, SL from CTHD";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setSoHD(resultSet.getInt("SOHD"));
                cthd.setMaSP(resultSet.getString("MASP"));
                cthd.setSoLuong(resultSet.getInt("SL"));

                allHD.add(cthd);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allHD;
    }

    @Override
    public List<ChiTietHoaDonFull> getCTHD(int soHD) {
        List<ChiTietHoaDonFull> chiTiet = new ArrayList<>();

        try {
//            String sql = "select SOHD, MASP, SL from CTHD where SOHD = " + soHD;
            String sql = "select c.MASP, c.SL, s.TENSP, s.DVT, s.NUOCSX, s.GIA from CTHD c INNER JOIN SANPHAM s ON c.MASP = s.MASP where c.SOHD = " + soHD;

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
//                ChiTietHoaDon cthd = new ChiTietHoaDon();
//                cthd.setSoHD(resultSet.getInt("SOHD"));
//                cthd.setMaSP(resultSet.getString("MASP"));
//                cthd.setSoLuong(resultSet.getInt("SL"));
                ChiTietHoaDonFull ctFull = new ChiTietHoaDonFull();
                ctFull.setMaSP(resultSet.getString("MASP"));
                ctFull.setTenSP(resultSet.getString("TENSP"));
                ctFull.setDvt(resultSet.getString("DVT"));
                ctFull.setNuocSX(resultSet.getString("NUOCSX"));
                ctFull.setGia(resultSet.getInt("GIA"));
                ctFull.setSL(resultSet.getInt("SL"));
                ctFull.setTriGia(resultSet.getInt("GIA") * resultSet.getInt("SL"));

                chiTiet.add(ctFull);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chiTiet;
    }

    @Override
    public int tongTriGia(int soHD) {
        int tong = 0;

        try {
            String sql = "select c.MASP, c.SL, s.TENSP, s.DVT, s.NUOCSX, s.GIA from CTHD c INNER JOIN SANPHAM s ON c.MASP = s.MASP where c.SOHD = " + soHD;

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                tong += resultSet.getInt("GIA") * resultSet.getInt("SL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tong;
    }
}

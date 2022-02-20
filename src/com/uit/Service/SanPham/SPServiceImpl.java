package com.uit.Service.SanPham;

import com.uit.Dao.MyConnection;
import com.uit.Model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SPServiceImpl implements SPService{
    @Override
    public void insertSP(SanPham newSP) {
        try {
            String sql = "insert into SANPHAM (MASP, TENSP, DVT, NUOCSX, GIA) values (?,?,?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, newSP.getMaSP());
            psta.setString(2, newSP.getTenSP());
            psta.setString(3, newSP.getDvt());
            psta.setString(4, newSP.getNuocSX());
            psta.setInt(5, newSP.getGia());

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSP(SanPham updatedSP) {
        try {
            String sql = "update SANPHAM set MASP = ?, TENSP = ?, DVT = ?, NUOCSX = ?, GIA = ? where MASP = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, updatedSP.getMaSP());
            psta.setString(2, updatedSP.getTenSP());
            psta.setString(3, updatedSP.getDvt());
            psta.setString(4, updatedSP.getNuocSX());
            psta.setInt(5, updatedSP.getGia());
            psta.setString(6, updatedSP.getMaSP());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSP(String maSP) {
        try {
            String sql = "delete from SANPHAM where MASP = '" + maSP + "'";

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
    public List<SanPham> getAllSP() {
        List<SanPham> allSP = new ArrayList<>();

        try {
            String sql = "select MASP, TENSP, DVT, NUOCSX, GIA from SANPHAM";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(resultSet.getString("MASP"));
                sanPham.setTenSP(resultSet.getString("TENSP"));
                sanPham.setDvt(resultSet.getString("DVT"));
                sanPham.setNuocSX(resultSet.getString("NUOCSX"));
                sanPham.setGia(resultSet.getInt("GIA"));

                allSP.add(sanPham);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allSP;
    }

    @Override
    public List<String> getListDVT() {
        List<String> allDVT = new ArrayList<>();

        try {
            String sql = "select distinct DVT from SANPHAM";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                allDVT.add(resultSet.getString("DVT"));
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allDVT;
    }

    @Override
    public List<String> getListNuocSX() {
        List<String> allNuocSX = new ArrayList<>();

        try {
            String sql = "select distinct NUOCSX from SANPHAM";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                allNuocSX.add(resultSet.getString("NUOCSX"));
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allNuocSX;
    }

    @Override
    public boolean checkMaSP(String maSP) {
        try {
            String sql = "select MASP from SANPHAM where MASP = '" + maSP + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getGiaSP(String maSP) {
        int giaSP = 0;
        try {
            String sql = "select GIA from SANPHAM where MASP = '" + maSP + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            giaSP = resultSet.getInt("GIA");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return giaSP;
    }
}

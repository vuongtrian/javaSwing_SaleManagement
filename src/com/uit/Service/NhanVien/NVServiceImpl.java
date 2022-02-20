package com.uit.Service.NhanVien;

import com.uit.Dao.MyConnection;
import com.uit.Model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NVServiceImpl implements NVService {
    @Override
    public void insertNV(NhanVien newNV) {
        try {
            String sql = "insert into NHANVIEN (MANV, HOTEN, SODT, NGAYVAOLAM) values(?,?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, newNV.getMaNV());
            psta.setString(2, newNV.getTenNV());
            psta.setString(3, newNV.getSoDT());
            psta.setString(4, newNV.getNgayVaoLam());

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNV(NhanVien updatedNV) {
        try {
            String sql = "update NHANVIEN set MANV = ?, HOTEN = ?, SODT = ?, NGAYVAOLAM = ? where MANV = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, updatedNV.getMaNV());
            psta.setString(2, updatedNV.getTenNV());
            psta.setString(3, updatedNV.getSoDT());
            psta.setString(4, updatedNV.getNgayVaoLam());
            psta.setString(5, updatedNV.getMaNV());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNV(String maNV) {
        try {
            String sql = "delete from NHANVIEN where MANV = '" + maNV + "'";

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
    public List<NhanVien> getAllNV() {
        List<NhanVien> allNV = new ArrayList<>();

        try {
            String sql = "select MANV, HOTEN, SODT, NGAYVAOLAM from NHANVIEN";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(resultSet.getString("MANV"));
                nhanVien.setTenNV(resultSet.getString("HOTEN"));
                nhanVien.setSoDT(resultSet.getString("SODT"));
                nhanVien.setNgayVaoLam(resultSet.getString("NGAYVAOLAM"));

                allNV.add(nhanVien);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allNV;
    }

    @Override
    public boolean checkMaNV(String maNV) {
        try {
            String sql = "select MANV from NHANVIEN where MANV = '" + maNV + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            if (!resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

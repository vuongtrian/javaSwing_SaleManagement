package com.uit.Service.KhachHang;

import com.uit.Dao.MyConnection;
import com.uit.Model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KHServiceImpl implements KHService {
    @Override
    public void insertKH(KhachHang newKH) {
        try {
            String sql = "insert into KHACHHANG (MAKH, HOTEN, DIACHI, SODT, NGAYSINH, NGAYDANGKY, DOANHSO) values(?,?,?,?,?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, newKH.getMaKH());
            psta.setString(2, newKH.getTenKH());
            psta.setString(3, newKH.getDiaChi());
            psta.setString(4, newKH.getSoDT());
            psta.setString(5, newKH.getNgaySinh());
            psta.setString(6, newKH.getNgayDangKy());
            psta.setInt(7, 0);

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateKH(KhachHang updatedKH) {
        try {
            String sql = "update KHACHHANG set HOTEN = ?, DIACHI = ?, SODT = ?, NGAYSINH = ?, NGAYDANGKY = ? where MAKH = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, updatedKH.getTenKH());
            psta.setString(2, updatedKH.getDiaChi());
            psta.setString(3, updatedKH.getSoDT());
            psta.setString(4, updatedKH.getNgaySinh());
            psta.setString(5, updatedKH.getNgayDangKy());
            psta.setString(6, updatedKH.getMaKH());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDoanhSo(String maKH, int doanhSo) {
        try {
            String sql = "update KHACHHANG set DOANHSO = ? where MAKH = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, doanhSo);
            psta.setString(2, maKH);

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteKH(String maKH) {
        try {
            String sql = "delete from KHACHHANG where MAKH = '" + maKH + "'";

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
    public List<KhachHang> getAllKH() {
        List<KhachHang> allKH = new ArrayList<>();

        try {
            String sql = "select MAKH, HOTEN, DIACHI, SODT, NGAYSINH, NGAYDANGKY, DOANHSO from KHACHHANG";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(resultSet.getString("MAKH"));
                khachHang.setTenKH(resultSet.getString("HOTEN"));
                khachHang.setDiaChi(resultSet.getString("DIACHI"));
                khachHang.setSoDT(resultSet.getString("SODT"));
                khachHang.setNgaySinh(resultSet.getString("NGAYSINH"));
                khachHang.setNgayDangKy(resultSet.getString("NGAYDANGKY"));
                khachHang.setDoanhSo(resultSet.getInt("DOANHSO"));

                allKH.add(khachHang);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allKH;
    }

    @Override
    public boolean checkMaKH(String maKH) {
        try {
            String sql = "select MAKH from KHACHHANG where MAKH = '" + maKH + "'";

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

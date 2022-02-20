package com.uit.Service.HoaDon;

import com.uit.Dao.MyConnection;
import com.uit.Model.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HDServiceImpl implements HDService {
    @Override
    public void insertHD(HoaDon newHD) {
        try {
            String sql = "insert into HOADON (SOHD, NGAYHD, MAKH, MANV, TRIGIA) values (?,?,?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, newHD.getSoHD());
            psta.setString(2, newHD.getNgayHD());
            psta.setString(3, newHD.getMaKH());
            psta.setString(4, newHD.getMaNV());
            psta.setInt(5, newHD.getTriGia());

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHD(HoaDon updatedHD) {
        try {
            String sql = "update HOADON set SOHD = ?, NGAYHD = ?, MAKH = ?, MANV = ? where SOHD = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, updatedHD.getSoHD());
            psta.setString(2, updatedHD.getNgayHD());
            psta.setString(3, updatedHD.getMaKH());
            psta.setString(4, updatedHD.getMaNV());
            psta.setInt(5, updatedHD.getSoHD());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHD(int soHD) {
        try {
            String sql = "delete from HOADON where SOHD = " + soHD;

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
    public List<HoaDon> getAllHD() {
        List<HoaDon> allHD = new ArrayList<>();

        try {
            String sql = "select SOHD, NGAYHD, MAKH, MANV, TRIGIA from HOADON";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setSoHD(resultSet.getInt("SOHD"));
                hoaDon.setNgayHD(resultSet.getString("NGAYHD"));
                hoaDon.setMaKH(resultSet.getString("MAKH"));
                hoaDon.setMaNV(resultSet.getString("MANV"));
                hoaDon.setTriGia(resultSet.getInt("TRIGIA"));

                allHD.add(hoaDon);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allHD;
    }

    @Override
    public boolean checkSoHD(int soHD) {
        try {
            String sql = "select SOHD from HOADON where SOHD = " + soHD;

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

    @Override
    public boolean checkMaKH(String maKH) {
        try {
            String sql = "select MAKH from HOADON where MAKH = '" + maKH + "'";

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
    public void updateTriGia(int soHD, int triGia) {
        try {
            String sql = "update HOADON set TRIGIA = ? where SOHD = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setInt(1, triGia);
            psta.setInt(2, soHD);

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int tongTriGiaKhachHang(String maKH) {
        int tongTriGia = 0;

        try {
            String sql = "select sum(TRIGIA) from HOADON where MAKH = '" + maKH + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                tongTriGia = resultSet.getInt("sum(TRIGIA)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongTriGia;
    }
}

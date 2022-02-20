package com.uit.Gui;

import com.uit.Gui.HoaDon.HoaDonGUI;
import com.uit.Gui.KhachHang.KhachHangGUI;
import com.uit.Gui.NhanVien.NhanVienGUI;
import com.uit.Gui.SanPham.SanPhamGUI;
import com.uit.Gui.Users.UsersGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdminGUI extends JFrame {
    private JButton btnKhachHang;
    private JButton btnSanPham;
    private JButton btnNhanVien;
    private JButton btnUsers;
    private JButton btnHoaDon;
    private JPanel adminHomeFrame;
    private JLabel lbHeader;

    public HomeAdminGUI() {
        setTitle("Admin - Trang Chủ");
        setContentPane(adminHomeFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);

        btnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHangGUI khachHangFrame = new KhachHangGUI();
                khachHangFrame.setVisible(true);
            }
        });
        btnSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanPhamGUI sanPhamFrame = new SanPhamGUI();
                sanPhamFrame.setVisible(true);
            }
        });
        btnNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienGUI nhanVienFrame = new NhanVienGUI();
                nhanVienFrame.setVisible(true);
            }
        });
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersGUI usersFrame = new UsersGUI();
                usersFrame.setVisible(true);
            }
        });
        btnHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonGUI hoaDonFrame = new HoaDonGUI();
                hoaDonFrame.setVisible(true);
            }
        });
    }

    public void setLabelHeader (String name) {
        lbHeader.setText("Welcome back " + name);
    }
}

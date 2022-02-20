package com.uit.Gui;

import com.uit.Gui.HoaDon.HoaDonGUI;
import com.uit.Gui.KhachHang.KhachHangGUI;
import com.uit.Gui.SanPham.SanPhamGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUserGUI extends JFrame {
    private JPanel userHomeFrame;
    private JLabel lbHeader;
    private JButton btnKhachHang;
    private JButton btnSanPham;
    private JButton btnHoaDon;

    public HomeUserGUI() {
        setTitle("User - Trang Chủ");
        setContentPane(userHomeFrame);
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
        btnHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonGUI hoaDonFrame = new HoaDonGUI();
                hoaDonFrame.setVisible(true);
            }
        });
    }

    public void setLabelHeader(String name) {
        lbHeader.setText("Welcome back " + name);
    }
}

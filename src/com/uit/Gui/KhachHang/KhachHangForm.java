package com.uit.Gui.KhachHang;

import com.uit.Model.KhachHang;
import com.uit.Service.Helper.DateLabelFormatter;
import com.uit.Service.HoaDon.HDService;
import com.uit.Service.HoaDon.HDServiceImpl;
import com.uit.Service.KhachHang.KHService;
import com.uit.Service.KhachHang.KHServiceImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.Properties;

public class KhachHangForm extends JFrame {
    private JPanel KHForm;
    private JLabel lbKHFormHeader;
    private JTextField tfMaKH;
    private JTextField tfTenKH;
    private JTextField tfDiaChi;
    private JTextField tfSoDT;
    private JPanel pNgaySinh;
    private JPanel pNgayDangKy;
    private JButton btnAction;
    private JButton btnCancel;

    private int doanhSo;

    KHService khService = new KHServiceImpl();

    public KhachHangForm () {
        setContentPane(KHForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedNgaySinh = (Date) ngaySinhPicker.getModel().getValue();
                java.sql.Date sqlNgaySinhPicker = new java.sql.Date(selectedNgaySinh.getTime());

                Date selectedNgayDangKy = (Date) ngayDangKyPicker.getModel().getValue();
                java.sql.Date sqlNgayDangKyPicker = new java.sql.Date(selectedNgayDangKy.getTime());

                String maKH = tfMaKH.getText();
                String hoTen = tfTenKH.getText();
                String diaChi = tfDiaChi.getText();
                String soDT = tfSoDT.getText();
                String ngaySinh = sqlNgaySinhPicker.toString();
                String ngayDangKy = sqlNgayDangKyPicker.toString();
                if (btnAction.getText().equals("Thêm")) {
                    int doanhSo = 0;

                    if (!khService.checkMaKH(maKH)) {
                        JOptionPane.showMessageDialog(KhachHangForm.this, "Mã khách hàng " + maKH + " đã tồn tại. Vui lòng nhập lại mã khách hàng!", "Thêm khách hàng mới", JOptionPane.ERROR_MESSAGE );
                    } else if (maKH.isEmpty() || hoTen.isEmpty() || diaChi.isEmpty() || soDT.isEmpty() || ngaySinh.isEmpty() || ngayDangKy.isEmpty()) {
                        JOptionPane.showMessageDialog(KhachHangForm.this, "Vui lòng nhập đầy đủ thông tin khách hàng", "Thêm khách hàng mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        KhachHang newKH = new KhachHang(maKH,hoTen,diaChi,soDT,ngaySinh,ngayDangKy,doanhSo);
                        khService.insertKH(newKH);
                        JOptionPane.showMessageDialog(KhachHangForm.this, "Khách hàng " + hoTen + " đã được thêm thành công!", "Thêm khách hàng mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")) {
//                    tfMaKH.setEnabled(false);
                    if (hoTen.isEmpty() || diaChi.isEmpty() || soDT.isEmpty() || ngaySinh.isEmpty() || ngayDangKy.isEmpty()) {
                        JOptionPane.showMessageDialog(KhachHangForm.this, "Vui lòng nhập đầy đủ thông tin khách hàng", "Thêm khách hàng mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        int curDoanhSo = doanhSo;
                        KhachHang updatedKH = new KhachHang(maKH,hoTen,diaChi,soDT,ngaySinh,ngayDangKy,doanhSo);
                        khService.updateKH(updatedKH);
                        JOptionPane.showMessageDialog(KhachHangForm.this, "Khách hàng " + hoTen + " đã được cập nhật thành công!", "Cập nhật khách hàng", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setLabelHeader (String label) {
        lbKHFormHeader.setText(label);
    }

    public void setBtnAction (String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField (String maKH, String tenKH, String diaChi, String soDT) {
        tfMaKH.setText(maKH);
        tfTenKH.setText(tenKH);
        tfDiaChi.setText(diaChi);
        tfSoDT.setText(soDT);
    }

    public void getDoanhSo (int doanhSo) {
        doanhSo = doanhSo;
    }

    public void setEnabledMaKH () {
        tfMaKH.setEnabled(false);
    }

    JDatePickerImpl ngaySinhPicker;
    JDatePickerImpl ngayDangKyPicker;

    public void addDatePicker () {
        UtilDateModel modelNgaySinh = new UtilDateModel();
        Properties propNgaySinh = new Properties();
        propNgaySinh.put("text.today", "Today");
        propNgaySinh.put("text.month", "Month");
        propNgaySinh.put("text.year", "Year");
        JDatePanelImpl datePanelNgaySinh = new JDatePanelImpl(modelNgaySinh, propNgaySinh);
        ngaySinhPicker = new JDatePickerImpl(datePanelNgaySinh, new DateLabelFormatter());
        pNgaySinh.setLayout(new GridBagLayout());
        pNgaySinh.add(ngaySinhPicker);

        UtilDateModel modelNgayDangKy = new UtilDateModel();
        modelNgayDangKy.setSelected(true);
        Properties propNgayDangKy = new Properties();
        propNgayDangKy.put("text.today", "Today");
        propNgayDangKy.put("text.month", "Month");
        propNgayDangKy.put("text.year", "Year");
        JDatePanelImpl datePanelNgayDangKy = new JDatePanelImpl(modelNgayDangKy, propNgayDangKy);
        ngayDangKyPicker = new JDatePickerImpl(datePanelNgayDangKy, new DateLabelFormatter());
        pNgayDangKy.setLayout(new GridBagLayout());
        pNgayDangKy.add(ngayDangKyPicker);
    }

    public void updateDatePicker (String ngaySinh, String ngayDangKy) {
        int yearNgaySinh = Integer.parseInt(ngaySinh.substring(0,4));
        int mothNgaySinh = Integer.parseInt(ngaySinh.substring(5,7));
        int dayNgaySinh = Integer.parseInt(ngaySinh.substring(8,ngaySinh.length()));
        UtilDateModel modelNgaySinh = new UtilDateModel();
        modelNgaySinh.setDate(yearNgaySinh,mothNgaySinh-1,dayNgaySinh);
        modelNgaySinh.setSelected(true);
        Properties propNgaySinh = new Properties();
        propNgaySinh.put("text.today", "Today");
        propNgaySinh.put("text.month", "Month");
        propNgaySinh.put("text.year", "Year");
        JDatePanelImpl datePanelNgaySinh = new JDatePanelImpl(modelNgaySinh, propNgaySinh);
        ngaySinhPicker = new JDatePickerImpl(datePanelNgaySinh, new DateLabelFormatter());
        pNgaySinh.setLayout(new GridBagLayout());
        pNgaySinh.add(ngaySinhPicker);

        int yearNgayDangKy = Integer.parseInt(ngayDangKy.substring(0,4));
        int mothNgayDangKy = Integer.parseInt(ngayDangKy.substring(5,7));
        int dayNgayDangKy = Integer.parseInt(ngayDangKy.substring(8,ngayDangKy.length()));
        UtilDateModel modelNgayDangKy = new UtilDateModel();
        modelNgayDangKy.setDate(yearNgayDangKy,mothNgayDangKy-1,dayNgayDangKy);
        modelNgayDangKy.setSelected(true);
        Properties propNgayDangKy = new Properties();
        propNgayDangKy.put("text.today", "Today");
        propNgayDangKy.put("text.month", "Month");
        propNgayDangKy.put("text.year", "Year");
        JDatePanelImpl datePanelNgayDangKy = new JDatePanelImpl(modelNgayDangKy, propNgayDangKy);
        ngayDangKyPicker = new JDatePickerImpl(datePanelNgayDangKy, new DateLabelFormatter());
        pNgayDangKy.setLayout(new GridBagLayout());
        pNgayDangKy.add(ngayDangKyPicker);
    }

}

package com.uit.Gui.NhanVien;

import com.uit.Gui.KhachHang.KhachHangForm;
import com.uit.Model.NhanVien;
import com.uit.Service.Helper.DateLabelFormatter;
import com.uit.Service.NhanVien.NVService;
import com.uit.Service.NhanVien.NVServiceImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

public class NhanVienForm extends JFrame {
    private JPanel NVForm;
    private JTextField tfMaNV;
    private JTextField tfTenNV;
    private JTextField tfSoDT;
    private JButton btnAction;
    private JButton btnCancel;
    private JLabel lbNVFormHeader;
    private JPanel pNgayVaoLam;

    NVService nvService = new NVServiceImpl();

    public NhanVienForm () {
        setContentPane(NVForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedNgayVaoLam = (Date) ngayVaoLamPicker.getModel().getValue();
                java.sql.Date sqlNgayVaoLamPicker = new java.sql.Date(selectedNgayVaoLam.getTime());

                String maNV = tfMaNV.getText();
                String tenNV = tfTenNV.getText();
                String soDT = tfSoDT.getText();
                String ngayVaoLam = sqlNgayVaoLamPicker.toString();

                if (btnAction.getText().equals("Thêm")) {
                    if (!nvService.checkMaNV(maNV)) {
                        JOptionPane.showMessageDialog(NhanVienForm.this, "Mã nhân viên " + maNV + " đã tồn tại. Vui lòng nhập lại mã nhân viên!", "Thêm nhân viên mới", JOptionPane.ERROR_MESSAGE );
                    } else if (maNV.isEmpty() || tenNV.isEmpty() || soDT.isEmpty() || ngayVaoLam.isEmpty()) {
                        JOptionPane.showMessageDialog(NhanVienForm.this, "Vui lòng nhập đầy đủ thông tin nhân viên", "Thêm nhân viên mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        NhanVien newNV = new NhanVien(maNV, tenNV, soDT, ngayVaoLam);
                        nvService.insertNV(newNV);
                        JOptionPane.showMessageDialog(NhanVienForm.this, "Nhân viên " + tenNV + " đã được thêm thành công!", "Thêm nhân viên mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")) {
                    if (tenNV.isEmpty() || soDT.isEmpty() || ngayVaoLam.isEmpty()) {
                        JOptionPane.showMessageDialog(NhanVienForm.this, "Vui lòng nhập đầy đủ thông tin nhân viên", "Thêm nhân viên mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        NhanVien updatedNV = new NhanVien(maNV, tenNV, soDT, ngayVaoLam);
                        nvService.updateNV(updatedNV);
                        JOptionPane.showMessageDialog(NhanVienForm.this, "Nhân viên " + tenNV + " đã được cập nhật thành công!", "Cập nhật nhân viên", JOptionPane.INFORMATION_MESSAGE );
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

    public void setLableHeader (String lable) {
        lbNVFormHeader.setText(lable);
    }

    public void setBtnAction (String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField (String maNV, String tenNV, String soDT) {
        tfMaNV.setText(maNV);
        tfTenNV.setText(tenNV);
        tfSoDT.setText(soDT);
    }

    public void setEnableMaNV () {
        tfMaNV.setEnabled(false);
    }

    JDatePickerImpl ngayVaoLamPicker;
    public void addDatePicker () {
        UtilDateModel modelNgayVaoLam = new UtilDateModel();
        Properties propNgayVaoLam = new Properties();
        propNgayVaoLam.put("text.today", "Today");
        propNgayVaoLam.put("text.month", "Month");
        propNgayVaoLam.put("text.year", "Year");
        JDatePanelImpl datePanelNgayVaoLam = new JDatePanelImpl(modelNgayVaoLam, propNgayVaoLam);
        ngayVaoLamPicker = new JDatePickerImpl(datePanelNgayVaoLam, new DateLabelFormatter());
        pNgayVaoLam.setLayout(new GridBagLayout());
        pNgayVaoLam.add(ngayVaoLamPicker);
    }

    public void updateDatePicker (String ngayVaoLam) {
        int yearNgayVaoLam = Integer.parseInt(ngayVaoLam.substring(0,4));
        int mothNgayVaoLam = Integer.parseInt(ngayVaoLam.substring(5,7));
        int dayNgayVaoLam = Integer.parseInt(ngayVaoLam.substring(8,ngayVaoLam.length()));
        UtilDateModel modelNgayVaoLam = new UtilDateModel();
        modelNgayVaoLam.setDate(yearNgayVaoLam,mothNgayVaoLam-1,dayNgayVaoLam);
        modelNgayVaoLam.setSelected(true);
        Properties propNgayVaoLam = new Properties();
        propNgayVaoLam.put("text.today", "Today");
        propNgayVaoLam.put("text.month", "Month");
        propNgayVaoLam.put("text.year", "Year");
        JDatePanelImpl datePanelNgayVaoLam = new JDatePanelImpl(modelNgayVaoLam, propNgayVaoLam);
        ngayVaoLamPicker = new JDatePickerImpl(datePanelNgayVaoLam, new DateLabelFormatter());
        pNgayVaoLam.setLayout(new GridBagLayout());
        pNgayVaoLam.add(ngayVaoLamPicker);
    }
}

package com.uit.Gui.HoaDon;

import com.uit.Gui.KhachHang.KhachHangForm;
import com.uit.Gui.KhachHang.KhachHangGUI;
import com.uit.Gui.SanPham.SanPhamForm;
import com.uit.Model.ChiTietHoaDon;
import com.uit.Model.HoaDon;
import com.uit.Service.CTHD.CTHDService;
import com.uit.Service.CTHD.CTHDServiceImpl;
import com.uit.Service.Helper.DateLabelFormatter;
import com.uit.Service.HoaDon.HDService;
import com.uit.Service.HoaDon.HDServiceImpl;
import com.uit.Service.KhachHang.KHService;
import com.uit.Service.KhachHang.KHServiceImpl;
import com.uit.Service.NhanVien.NVService;
import com.uit.Service.NhanVien.NVServiceImpl;
import com.uit.Service.SanPham.SPService;
import com.uit.Service.SanPham.SPServiceImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

public class HoaDonForm extends JFrame {
    private JPanel HDForm;
    private JTextField tfSoHoaDon;
    private JTextField tfMaKhachHang;
    private JTextField tfMaNhanVien;
    private JButton btnAction;
    private JButton btnCancel;
    private JLabel lbHDFormHeader;
    private JPanel pNgayHoaDon;

    KHService khService = new KHServiceImpl();
    NVService nvService = new NVServiceImpl();
    SPService spService = new SPServiceImpl();
    HDService hdService = new HDServiceImpl();
    CTHDService cthdService = new CTHDServiceImpl();

    public HoaDonForm() {
        setContentPane(HDForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);



        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedNgayHD = (Date) ngayHDPicker.getModel().getValue();
                java.sql.Date sqlNgayHDPicker = new java.sql.Date(selectedNgayHD.getTime());

                int soHD = 0;
                if (!tfSoHoaDon.getText().isEmpty()) {
                    soHD = Integer.parseInt(tfSoHoaDon.getText());
                }

                String ngayHD = sqlNgayHDPicker.toString();
                String maKH = tfMaKhachHang.getText();
                String maNV = tfMaNhanVien.getText();


                if (btnAction.getText().equals("Thêm")) {

                    if (soHD == 0 || ngayHD.isEmpty() || maKH.isEmpty() || maNV.isEmpty()) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Vui lòng nhập đầy đủ thông tin hóa đơn", "Thêm hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else if (!hdService.checkSoHD(soHD)) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Số hóa đơn " + soHD + " đã tồn tại. Vui lòng nhập lại mã số hóa đơn!", "Thêm hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else if (khService.checkMaKH(maKH)) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Mã khách hàng " + maKH + " không tồn tại. Vui lòng nhập lại mã khách hàng!", "Mã khách hàng", JOptionPane.ERROR_MESSAGE );
                    } else if (nvService.checkMaNV(maNV)) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Mã nhân viên " + maNV + " không tồn tại. Vui lòng nhập lại mã nhân viên!", "Mã nhân viên", JOptionPane.ERROR_MESSAGE );
                    } else {
                        HoaDon newHoaDon = new HoaDon(soHD, ngayHD, maKH, maNV, 0);
                        hdService.insertHD(newHoaDon);
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Hóa đơn " + soHD + " đã được thêm thành công!", "Thêm hóa đơn mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")) {
                    if (khService.checkMaKH(maKH)) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Mã khách hàng " + maKH + " không tồn tại. Vui lòng nhập lại mã khách hàng!", "Mã khách hàng", JOptionPane.ERROR_MESSAGE );
                    } else if (soHD == 0 || ngayHD.isEmpty() || maKH.isEmpty() || maNV.isEmpty()) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Vui lòng nhập đầy đủ thông tin hóa đơn", "Thêm hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else if (nvService.checkMaNV(maNV)) {
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Mã nhân viên " + maNV + " không tồn tại. Vui lòng nhập lại mã nhân viên!", "Mã nhân viên", JOptionPane.ERROR_MESSAGE );
                    }  else {
                        HoaDon updatedHoaDon = new HoaDon(soHD, ngayHD, maKH, maNV);
                        hdService.updateHD(updatedHoaDon);
                        JOptionPane.showMessageDialog(HoaDonForm.this, "Hóa đơn " + soHD + " đã được cập nhật thành công!", "Cập nhật hóa đơn", JOptionPane.INFORMATION_MESSAGE );
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

    public void setLabelHeader(String label) {
        lbHDFormHeader.setText(label);
    }

    public void setBtnAction(String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField(int soHD, String maKH, String maNV) {
        tfSoHoaDon.setText(String.valueOf(soHD));
        tfMaKhachHang.setText(maKH);
        tfMaNhanVien.setText(maNV);
    }

    public void setEnableSoHD() {
        tfSoHoaDon.setEnabled(false);
    }

    JDatePickerImpl ngayHDPicker;

    public void addDatePicker() {
        UtilDateModel modelNgayHD = new UtilDateModel();
        modelNgayHD.setSelected(true);
        Properties propNgayHD = new Properties();
        propNgayHD.put("text.today", "Today");
        propNgayHD.put("text.month", "Month");
        propNgayHD.put("text.year", "Year");
        JDatePanelImpl datePanelNgayHD = new JDatePanelImpl(modelNgayHD, propNgayHD);
        ngayHDPicker = new JDatePickerImpl(datePanelNgayHD, new DateLabelFormatter());
        pNgayHoaDon.setLayout(new GridBagLayout());
        pNgayHoaDon.add(ngayHDPicker);
    }

    public void updateDatePicker(String ngayHD) {
        int yearNgayHD = Integer.parseInt(ngayHD.substring(0, 4));
        int mothNgayHD = Integer.parseInt(ngayHD.substring(5, 7));
        int dayNgayHD = Integer.parseInt(ngayHD.substring(8, ngayHD.length()));
        UtilDateModel modelNgayHD = new UtilDateModel();
        modelNgayHD.setDate(yearNgayHD, mothNgayHD - 1, dayNgayHD);
        modelNgayHD.setSelected(true);
        Properties propNgayHD = new Properties();
        propNgayHD.put("text.today", "Today");
        propNgayHD.put("text.month", "Month");
        propNgayHD.put("text.year", "Year");
        JDatePanelImpl datePanelNgayHD = new JDatePanelImpl(modelNgayHD, propNgayHD);
        ngayHDPicker = new JDatePickerImpl(datePanelNgayHD, new DateLabelFormatter());
        pNgayHoaDon.setLayout(new GridBagLayout());
        pNgayHoaDon.add(ngayHDPicker);
    }


}

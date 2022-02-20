package com.uit.Gui.HoaDon;

import com.uit.Model.ChiTietHoaDon;
import com.uit.Service.CTHD.CTHDService;
import com.uit.Service.CTHD.CTHDServiceImpl;
import com.uit.Service.HoaDon.HDService;
import com.uit.Service.HoaDon.HDServiceImpl;
import com.uit.Service.SanPham.SPService;
import com.uit.Service.SanPham.SPServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChiTietForm extends JFrame {
    private JButton btnAction;
    private JButton btnCancel;
    private JPanel CTForm;
    private JLabel lbFormHeader;
    private JTextField tfMaSP;
    private JTextField tfSoLuong;

    CTHDService cthdService = new CTHDServiceImpl();
    SPService spService = new SPServiceImpl();

    private int soHD;

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public ChiTietForm () {
        setContentPane(CTForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(550, 300));
        setLocationRelativeTo(null);

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = tfMaSP.getText();

                int soLuong = 0;
                if (!tfSoLuong.getText().isEmpty()) {
                    soLuong = Integer.parseInt(tfSoLuong.getText());
                }

                if (btnAction.getText().equals("Thêm")) {
                    if (maSP.isEmpty() || soLuong == 0) {
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Vui lòng nhập đầy đủ thông tin chi tiết hóa đơn", "Thêm chi tiết hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else if (!spService.checkMaSP(maSP)) {
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Mã sản phẩm " + maSP + " không tồn tại. Vui lòng nhập lại mã số sản phẩm!", "Thêm chi tiết hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        ChiTietHoaDon newChiTietHoaDon = new ChiTietHoaDon(soHD, maSP, soLuong);
                        cthdService.insertCTHD(newChiTietHoaDon);
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Chi tiết hóa đơn " + soHD + " đã được thêm thành công!", "Thêm chi tiết hóa đơn mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")) {
                    if (maSP.isEmpty() || soLuong == 0) {
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Vui lòng nhập đầy đủ thông tin chi tiết hóa đơn", "Thêm chi tiết hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else if (!spService.checkMaSP(maSP)) {
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Mã sản phẩm " + maSP + " không tồn tại. Vui lòng nhập lại mã số sản phẩm!", "Thêm chi tiết hóa đơn mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        ChiTietHoaDon updatedChiTietHoaDon = new ChiTietHoaDon(soHD, maSP, soLuong);
                        cthdService.updateCTHD(updatedChiTietHoaDon);
                        JOptionPane.showMessageDialog(ChiTietForm.this, "Chi tiết hóa đơn " + soHD + " đã được cập nhật thành công!", "Cập nhật chi tiết hóa đơn", JOptionPane.INFORMATION_MESSAGE );
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

    public void setBtnAction(String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField (String maSP, int soLuong) {
        tfMaSP.setText(maSP);
        tfSoLuong.setText(String.valueOf(soLuong));
    }

    public void setLabelHeader (String typeAction) {
        lbFormHeader.setText(typeAction);
    }


}

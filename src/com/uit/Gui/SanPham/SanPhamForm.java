package com.uit.Gui.SanPham;

import com.uit.Gui.NhanVien.NhanVienForm;
import com.uit.Model.SanPham;
import com.uit.Service.SanPham.SPService;
import com.uit.Service.SanPham.SPServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SanPhamForm extends JFrame {
    private JPanel SPForm;
    private JComboBox cbDVT;
    private JComboBox cbNuocSX;
    private JTextField tfGia;
    private JTextField tfMaSP;
    private JTextField tfTenSP;
    private JLabel lbSPFormHeader;
    private JButton btnAction;
    private JButton btnCancel;

    SPService spService = new SPServiceImpl();

    private List<String> allDVT = spService.getListDVT();
    private List<String> allNuocSX = spService.getListNuocSX();

    public SanPhamForm () {
        setContentPane(SPForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(450, 300));
        setLocationRelativeTo(null);
        setComboBoxDVT();
        setComboBoxNuocSX();

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = tfMaSP.getText();
                String tenSP = tfTenSP.getText();
                String dvt = cbDVT.getSelectedItem().toString();
                String nuocSX = cbNuocSX.getSelectedItem().toString();

                int gia = 0;
                if (!tfGia.getText().isEmpty()) {
                    gia = Integer.parseInt(tfGia.getText());
                }

                if (btnAction.getText().equals("Thêm")) {
                    if (!spService.checkMaSP(maSP)) {
                        JOptionPane.showMessageDialog(SanPhamForm.this, "Mã sản phẩm " + maSP + " đã tồn tại. Vui lòng nhập lại mã sản phẩm!", "Thêm sản phẩm mới", JOptionPane.ERROR_MESSAGE );
                    } else if (maSP.isEmpty() || tenSP.isEmpty() || dvt.isEmpty() || nuocSX.isEmpty() || gia == 0) {
                        JOptionPane.showMessageDialog(SanPhamForm.this, "Vui lòng nhập đầy đủ thông tin sản phẩm", "Thêm sản phẩm mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        SanPham newSP = new SanPham(maSP, tenSP, dvt, nuocSX, gia);
                        spService.insertSP(newSP);
                        JOptionPane.showMessageDialog(SanPhamForm.this, "Sản phẩm " + tenSP + " đã được thêm thành công!", "Thêm sản phẩm mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")) {
                    if (tenSP.isEmpty() || dvt.isEmpty() || nuocSX.isEmpty() || gia == 0) {
                        JOptionPane.showMessageDialog(SanPhamForm.this, "Vui lòng nhập đầy đủ thông tin sản phẩm", "Thêm sản phẩm mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        SanPham updatedSP = new SanPham(maSP, tenSP, dvt, nuocSX, gia);
                        spService.updateSP(updatedSP);
                        JOptionPane.showMessageDialog(SanPhamForm.this, "Sản phẩm " + tenSP + " đã được cập nhật thành công!", "Cập nhật sản phẩm", JOptionPane.INFORMATION_MESSAGE );
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
        lbSPFormHeader.setText(label);
    }

    public void setBtnAction (String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField (String maSP, String tenSP, String dvt, String nuocSX, int gia) {
        tfMaSP.setText(maSP);
        tfTenSP.setText(tenSP);

        for (int i = 0; i < allDVT.size(); i++) {
            if (allDVT.get(i).equals(dvt)) {
                cbDVT.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < allNuocSX.size(); i++) {
            if (allNuocSX.get(i).equals(nuocSX)) {
                cbNuocSX.setSelectedIndex(i);
            }
        }

        tfGia.setText(String.valueOf(gia));
    }

    public void setEnableMaSP () {
        tfMaSP.setEnabled(false);
    }

    public void setComboBoxDVT () {
        for (String dvt : allDVT) {
            cbDVT.addItem(dvt);
        }
    }

    public void setComboBoxNuocSX () {
        for (String nuocSX : allNuocSX) {
            cbNuocSX.addItem(nuocSX);
        }
    }
}

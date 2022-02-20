package com.uit.Gui.NhanVien;

import com.uit.Gui.KhachHang.KhachHangGUI;
import com.uit.Gui.SanPham.SanPhamGUI;
import com.uit.Model.NhanVien;
import com.uit.Service.NhanVien.NVService;
import com.uit.Service.NhanVien.NVServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NhanVienGUI extends JFrame {
    private JPanel nhanVienFrame;
    private JButton btnLoadNV;
    private JButton btnCapNhatNV;
    private JButton btnThemNV;
    private JButton btnXoaNV;
    private JTable tNhanVien;

    NVService nvService = new NVServiceImpl();

    public NhanVienGUI() {
        setTitle("Quản Lý Nhân Viên");
        setContentPane(nhanVienFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 800));
        setLocationRelativeTo(null);
        loadTableNV();

        ListSelectionModel listSelectionModel = tNhanVien.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnLoadNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableNV();
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienForm form = new NhanVienForm();
                form.setVisible(true);
                form.setTitle("Thêm Nhân Viên");
                form.setLableHeader("Thêm Nhân Viên");
                form.setBtnAction("Thêm");
                form.addDatePicker();
            }
        });

        btnCapNhatNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tNhanVien.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(NhanVienGUI.this, "Vui lòng chọn nhân viên cần cập nhật!", "Cập nhật nhân viên", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    String maNV = tNhanVien.getModel().getValueAt(row, 0).toString();
                    String tenNV = tNhanVien.getModel().getValueAt(row, 1).toString();
                    String soDT = tNhanVien.getModel().getValueAt(row, 2).toString();
                    String ngayVaoLam = tNhanVien.getModel().getValueAt(row, 3).toString();

                    NhanVienForm form = new NhanVienForm();
                    form.setVisible(true);
                    form.setTitle("Cập Nhật Nhân Viên");
                    form.setLableHeader("Cập Nhật Nhân Viên");
                    form.setBtnAction("Cập Nhật");
                    form.fillTextField(maNV, tenNV, soDT);
                    form.setEnableMaNV();
                    form.updateDatePicker(ngayVaoLam);
                }
            }
        });

        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tNhanVien.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(NhanVienGUI.this, "Vui lòng chọn nhân viên cần xóa!", "Xóa nhân viên", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    String maNV = tNhanVien.getModel().getValueAt(row, 0).toString();
                    String tenNV = tNhanVien.getModel().getValueAt(row, 1).toString();
                    int reply = JOptionPane.showConfirmDialog(NhanVienGUI.this, "Bạn có chắc muốn xóa " + tenNV + " khỏi danh sách?", "Xóa nhân viên", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        nvService.deleteNV(maNV);
                        JOptionPane.showMessageDialog(NhanVienGUI.this, "Nhân viên " + tenNV + " đã xóa thành công!", "Xóa nhân viên", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void loadTableNV() {
        String[] column = {"Mã NV", "Họ Tên", "Số ĐT", "Ngày Vào Làm"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<NhanVien> dbData = nvService.getAllNV();

        for (int i = 0; i < dbData.size(); i++) {
            String maNV = dbData.get(i).getMaNV();
            String tenNV = dbData.get(i).getTenNV();
            String soDT = dbData.get(i).getSoDT();
            String ngayVaoLam = dbData.get(i).getNgayVaoLam();

            Object[] tableData = {maNV, tenNV, soDT, ngayVaoLam};

            tableModel.addRow(tableData);
        }
        tNhanVien.setModel(tableModel);
        tNhanVien.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }
}

package com.uit.Gui.SanPham;

import com.uit.Gui.NhanVien.NhanVienForm;
import com.uit.Gui.NhanVien.NhanVienGUI;
import com.uit.Model.SanPham;
import com.uit.Service.SanPham.SPService;
import com.uit.Service.SanPham.SPServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SanPhamGUI extends JFrame {
    private JPanel sanPhamFrame;
    private JTable tSanPham;
    private JButton btnLoadSP;
    private JButton btnCapNhatSP;
    private JButton btnThemSP;
    private JButton btnXoaSP;

    SPService spService = new SPServiceImpl();


    public SanPhamGUI() {
        setTitle("Quản Lý Sản Phẩm");
        setContentPane(sanPhamFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 800));
        setLocationRelativeTo(null);
        loadTableSP();
        ListSelectionModel listSelectionModel = tSanPham.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnLoadSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableSP();
            }
        });

        btnThemSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanPhamForm form = new SanPhamForm();
                form.setVisible(true);
                form.setTitle("Thêm Sản Phẩm");
                form.setLabelHeader("Thêm Sản Phẩm");
                form.setBtnAction("Thêm");
            }
        });

        btnCapNhatSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tSanPham.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(SanPhamGUI.this, "Vui lòng chọn sản phẩm cần cập nhật!", "Cập nhật sản phẩm", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maSP = tSanPham.getModel().getValueAt(row, 0).toString();
                    String tenSP = tSanPham.getModel().getValueAt(row, 1).toString();
                    String dvt = tSanPham.getModel().getValueAt(row, 2).toString();
                    String nuocSX = tSanPham.getModel().getValueAt(row, 3).toString();
                    int gia = Integer.parseInt(tSanPham.getModel().getValueAt(row, 4).toString());

                    SanPhamForm form = new SanPhamForm();
                    form.setVisible(true);
                    form.setTitle("Cập Nhật Sản Phẩm");
                    form.setLabelHeader("Cập Nhật Sản Phẩm");
                    form.setBtnAction("Cập Nhật");
                    form.setEnableMaSP();
                    form.fillTextField(maSP, tenSP, dvt, nuocSX, gia);
                }
            }
        });

        btnXoaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tSanPham.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(SanPhamGUI.this, "Vui lòng chọn sản phẩm cần xóa!", "Xóa sản phẩm", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maSP = tSanPham.getModel().getValueAt(row, 0).toString();
                    String tenSP = tSanPham.getModel().getValueAt(row, 1).toString();
                    int reply = JOptionPane.showConfirmDialog(SanPhamGUI.this, "Bạn có chắc muốn xóa " + tenSP + " khỏi danh sách?", "Xóa sản phẩm", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        spService.deleteSP(maSP);
                        JOptionPane.showMessageDialog(SanPhamGUI.this, "Sản phẩm " + tenSP + " đã xóa thành công!", "Xóa sản phẩm", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void loadTableSP() {
        String[] column = {"Mã SP", "Tên SP", "Đơn Vị Tính", "Nước Sản Xuất", "Đơn Giá SP"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<SanPham> dbData = spService.getAllSP();

        for (int i = 0; i < dbData.size(); i++) {
            String maSP = dbData.get(i).getMaSP();
            String tenSP = dbData.get(i).getTenSP();
            String dvt = dbData.get(i).getDvt();
            String nuocSX = dbData.get(i).getNuocSX();
            int gia = dbData.get(i).getGia();

            Object[] tableData = {maSP, tenSP, dvt, nuocSX, gia};

            tableModel.addRow(tableData);
        }
        tSanPham.setModel(tableModel);
        tSanPham.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }
}

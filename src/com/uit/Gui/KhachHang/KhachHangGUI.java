package com.uit.Gui.KhachHang;

import com.uit.Gui.HoaDon.HoaDonGUI;
import com.uit.Gui.SanPham.SanPhamGUI;
import com.uit.Model.KhachHang;
import com.uit.Service.HoaDon.HDService;
import com.uit.Service.HoaDon.HDServiceImpl;
import com.uit.Service.KhachHang.KHService;
import com.uit.Service.KhachHang.KHServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KhachHangGUI extends JFrame {
    private JPanel khachHangFrame;
    private JButton btnLoadKH;
    private JButton btnCapNhatKH;
    private JButton btnThemKH;
    private JButton btnXoaKH;
    private JTable tKhachHang;

    KHService khService = new KHServiceImpl();
    HDService hdService = new HDServiceImpl();

    public KhachHangGUI() {
        setTitle("Quản Lý Khách Hàng");
        setContentPane(khachHangFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 800));
        setLocationRelativeTo(null);
        loadTableKH();
        ListSelectionModel listSelectionModel = tKhachHang.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnLoadKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableKH();
            }
        });

        btnThemKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHangForm form = new KhachHangForm();
                form.setVisible(true);
                form.setTitle("Thêm Khách Hàng");
                form.setLabelHeader("Thêm Khách Hàng");
                form.setBtnAction("Thêm");
                form.addDatePicker();
            }
        });

        btnCapNhatKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ListSelectionModel listSelectionModel = tKhachHang.getSelectionModel();
//                listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                int row = tKhachHang.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(KhachHangGUI.this, "Vui lòng chọn khách hàng cần cập nhật!", "Cập nhật khách hàng", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    String maKH = tKhachHang.getModel().getValueAt(row, 0).toString();
                    String hoTen = tKhachHang.getModel().getValueAt(row, 1).toString();
                    String diaChi = tKhachHang.getModel().getValueAt(row, 2).toString();
                    String soDT = tKhachHang.getModel().getValueAt(row, 3).toString();
                    String ngaySinh = tKhachHang.getModel().getValueAt(row, 4).toString();
                    String ngayDangKy = tKhachHang.getModel().getValueAt(row, 5).toString();
                    String doanhSo = tKhachHang.getModel().getValueAt(row, 6).toString();

                    KhachHangForm form = new KhachHangForm();
                    form.setVisible(true);
                    form.setTitle("Cập Nhật Khách Hàng");
                    form.setLabelHeader("Cập Nhật Khách Hàng");
                    form.setBtnAction("Cập Nhật");
                    form.fillTextField(maKH, hoTen, diaChi, soDT);
                    form.getDoanhSo(Integer.parseInt(doanhSo));
                    form.setEnabledMaKH();
                    form.updateDatePicker(ngaySinh, ngayDangKy);
                }

            }
        });

        btnXoaKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tKhachHang.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(KhachHangGUI.this, "Vui lòng chọn khách hàng cần xóa!", "Xóa khách hàng", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    String maKH = tKhachHang.getModel().getValueAt(row, 0).toString();
                    String hoTen = tKhachHang.getModel().getValueAt(row, 1).toString();
                    int reply = JOptionPane.showConfirmDialog(KhachHangGUI.this, "Bạn có chắc muốn xóa " + hoTen + " khỏi danh sách?", "Xóa khách hàng", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        khService.deleteKH(maKH);
                        JOptionPane.showMessageDialog(KhachHangGUI.this, "Khách hàng " + hoTen + " đã xóa thành công!", "Xóa khách hàng", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void loadTableKH() {
        String[] column = {"Mã KH", "Họ Tên", "Địa Chỉ", "Số ĐT", "Ngày Sinh", "Ngày Đăng Ký", "Doanh Số"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<KhachHang> dbData = khService.getAllKH();

        for (int i = 0; i < dbData.size(); i++) {
            String maKH = dbData.get(i).getMaKH();
            String tenKH = dbData.get(i).getTenKH();
            String diaChi = dbData.get(i).getDiaChi();
            String soDT = dbData.get(i).getSoDT();
            String ngaySinh = dbData.get(i).getNgaySinh();
            String ngayDangKy = dbData.get(i).getNgayDangKy();
            int doanhSo = dbData.get(i).getDoanhSo();

            Object[] tableData = {maKH, tenKH, diaChi, soDT, ngaySinh, ngayDangKy, doanhSo};

            tableModel.addRow(tableData);
        }
        tKhachHang.setModel(tableModel);
        tKhachHang.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }
}

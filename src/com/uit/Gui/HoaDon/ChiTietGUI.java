package com.uit.Gui.HoaDon;

import com.uit.Gui.SanPham.SanPhamGUI;
import com.uit.Model.ChiTietHoaDonFull;
import com.uit.Service.CTHD.CTHDService;
import com.uit.Service.CTHD.CTHDServiceImpl;
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

public class ChiTietGUI extends JFrame {
    private JTable tChiTiet;
    private JPanel chiTietFrame;
    private JTextField tfTongTriGia;
    private JButton btnThemCT;
    private JButton btnXoaCT;
    private JButton btnCapNhatCT;
    private JButton btnLoadCT;

    CTHDService cthdService = new CTHDServiceImpl();
    HDService hdService = new HDServiceImpl();
    KHService khService = new KHServiceImpl();

    private int soHD;

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public ChiTietGUI() {
        setContentPane(chiTietFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 500));
        setLocationRelativeTo(null);


        btnThemCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChiTietForm chiTietForm = new ChiTietForm();
                chiTietForm.setSoHD(getSoHD());
                chiTietForm.setVisible(true);
                chiTietForm.setLabelHeader("Thêm Chi Tiết Hóa Đơn " + soHD);
                chiTietForm.setBtnAction("Thêm");

            }
        });

        btnLoadCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableChiTiet(getSoHD());
                tinhTongTriGia(getSoHD());
            }
        });
        btnCapNhatCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tChiTiet.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(ChiTietGUI.this, "Vui lòng chọn chi tiết hóa đơn cần cập nhật!", "Cập nhật chi tiết hóa đơn", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maSP = tChiTiet.getModel().getValueAt(row, 0).toString();
                    int soLuong = Integer.parseInt(tChiTiet.getModel().getValueAt(row, 5).toString());

                    ChiTietForm chiTietForm = new ChiTietForm();
                    chiTietForm.setSoHD(getSoHD());
                    chiTietForm.setVisible(true);
                    chiTietForm.setLabelHeader("Cập Nhật Chi Tiết Hóa Đơn " + soHD);
                    chiTietForm.setBtnAction("Cập Nhật");
                    chiTietForm.fillTextField(maSP, soLuong);
                }


            }
        });
        btnXoaCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tChiTiet.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(ChiTietGUI.this, "Vui lòng chọn chi tiết hóa đơn cần xóa!", "Xóa chi tiết hóa đơn", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maSP = tChiTiet.getModel().getValueAt(row, 0).toString();
                    int reply = JOptionPane.showConfirmDialog(ChiTietGUI.this, "Bạn có chắc muốn xóa?", "Xóa chi tiết hóa đơn", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        cthdService.deleteCTHD(getSoHD(), maSP);
                        JOptionPane.showMessageDialog(ChiTietGUI.this, "Đã xóa thành công!", "Xóa chi tiết hóa đơn", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void setChiTietTitle(int soHD) {
        setTitle("Chi tiết hóa đơn " + soHD);
    }

    public void loadTableChiTiet(int soHD) {
        String[] column = {"Mã Sản Phẩm", "Tên Sản Phẩm", "DVT", "Nước Sản Xuất", "Giá", "Số lượng", "Trị Giá"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<ChiTietHoaDonFull> dbData = cthdService.getCTHD(soHD);

        for (int i = 0; i < dbData.size(); i++) {
            String maSP = dbData.get(i).getMaSP();
            String tenSP = dbData.get(i).getTenSP();
            String dvt = dbData.get(i).getDvt();
            String nuocSX = dbData.get(i).getNuocSX();
            int gia = dbData.get(i).getGia();
            int soLuong = dbData.get(i).getSL();
            int triGia = dbData.get(i).getTriGia();

            Object[] tableData = {maSP, tenSP, dvt, nuocSX, gia, soLuong, triGia};

            tableModel.addRow(tableData);
        }
        tChiTiet.setModel(tableModel);
        tChiTiet.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }

    public void tinhTongTriGia(int soHD) {
        int tong = cthdService.tongTriGia(soHD);
        hdService.updateTriGia(soHD, tong);
        tfTongTriGia.setText(String.valueOf(tong));
    }

    public void tinhTongTriGiaKH(String maKH) {
        int tongTriGia = hdService.tongTriGiaKhachHang(maKH);
        khService.updateDoanhSo(maKH, tongTriGia);
    }
}

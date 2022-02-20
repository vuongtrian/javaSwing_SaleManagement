package com.uit.Gui.HoaDon;

import com.uit.Gui.KhachHang.KhachHangGUI;
import com.uit.Model.HoaDon;
import com.uit.Service.CTHD.CTHDService;
import com.uit.Service.CTHD.CTHDServiceImpl;
import com.uit.Service.HoaDon.HDService;
import com.uit.Service.HoaDon.HDServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HoaDonGUI extends JFrame{
    private JPanel hoaDonFrame;
    private JTable tHoaDon;
    private JButton btnLoadHD;
    private JButton btnCapNhatHD;
    private JButton btnThemHD;
    private JButton btnXoaHD;
    private JButton btnChiTiet;

    HDService hdService = new HDServiceImpl();
    CTHDService cthdService = new CTHDServiceImpl();

    public HoaDonGUI() {
        setTitle("Quản Lý Hóa Đơn");
        setContentPane(hoaDonFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 800));
        setLocationRelativeTo(null);
        loadTableHD();
        ListSelectionModel listSelectionModel = tHoaDon.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnLoadHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tHoaDon.getSelectedRow();

                if (row == -1) {
                    loadTableHD();
                } else {
                    String maKH = tHoaDon.getModel().getValueAt(row, 2).toString();
                    ChiTietGUI chiTietGUI = new ChiTietGUI();
                    chiTietGUI.tinhTongTriGiaKH(maKH);
                    loadTableHD();
                }
            }
        });

        btnChiTiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tHoaDon.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(HoaDonGUI.this, "Vui lòng chọn hóa đơn cần xem chi tiết!", "Chi tiết hóa đơn", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    int soHD = Integer.parseInt(tHoaDon.getModel().getValueAt(row, 0).toString());
                    ChiTietGUI chiTietGUI = new ChiTietGUI();
                    chiTietGUI.setSoHD(soHD);
                    chiTietGUI.setVisible(true);
                    chiTietGUI.setChiTietTitle(soHD);
                    chiTietGUI.loadTableChiTiet(soHD);
                    chiTietGUI.tinhTongTriGia(soHD);
                }
            }
        });

        btnThemHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonForm form = new HoaDonForm();
                form.setVisible(true);
                form.setTitle("Thêm Hóa Đơn");
                form.setLabelHeader("Thêm Hóa Đơn");
                form.setBtnAction("Thêm");
                form.addDatePicker();
            }
        });

        btnCapNhatHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tHoaDon.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(HoaDonGUI.this, "Vui lòng chọn hóa đơn cần cập nhật!", "Cập nhật hóa đơn", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    int soHD = Integer.parseInt(tHoaDon.getModel().getValueAt(row, 0).toString());
                    String ngayHD = tHoaDon.getModel().getValueAt(row, 1).toString();
                    String maKH = tHoaDon.getModel().getValueAt(row, 2).toString();
                    String maNV = tHoaDon.getModel().getValueAt(row, 3).toString();

                    HoaDonForm form = new HoaDonForm();
                    form.setVisible(true);
                    form.setTitle("Cập Nhật Hóa Đơn");
                    form.setLabelHeader("Cập Nhật Hóa Đơn");
                    form.setBtnAction("Cập Nhật");
                    form.fillTextField(soHD, maKH, maNV);
                    form.setEnableSoHD();
                    form.updateDatePicker(ngayHD);
                }
            }
        });
        btnXoaHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tHoaDon.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(HoaDonGUI.this, "Vui lòng chọn hóa đơn cần xóa!", "Xóa hóa đơn", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    int soHD = Integer.parseInt(tHoaDon.getModel().getValueAt(row, 0).toString());
                    int triGia = Integer.parseInt(tHoaDon.getModel().getValueAt(row, 4).toString());
                    int reply = JOptionPane.showConfirmDialog(HoaDonGUI.this, "Bạn có chắc muốn xóa hóa đơn " + soHD + " khỏi danh sách?", "Xóa hóa đơn", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        if (triGia == 0) {
                            hdService.deleteHD(soHD);
                            JOptionPane.showMessageDialog(HoaDonGUI.this, "Hóa đơn " + soHD + " đã xóa thành công!", "Xóa hóa đơn", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int replyCTHD = JOptionPane.showConfirmDialog(HoaDonGUI.this, "Tất cả chi tiết hóa đơn cũng sẽ bị xóa. Bạn muốn tiếp tục?", "Xóa hóa đơn", JOptionPane.YES_NO_CANCEL_OPTION);
                            if (replyCTHD == JOptionPane.YES_OPTION) {
                                cthdService.deleteAllCTHD(soHD);
                                hdService.deleteHD(soHD);
                                JOptionPane.showMessageDialog(HoaDonGUI.this, "Hóa đơn " + soHD + " đã xóa thành công!", "Xóa hóa đơn", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }

    public void loadTableHD () {
        String[] column = {"Số Hóa Đơn", "Ngày Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Trị Giá"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<HoaDon> dbData = hdService.getAllHD();

        for (int i = 0; i < dbData.size(); i++) {
            int soHD = dbData.get(i).getSoHD();
            String ngayHD = dbData.get(i).getNgayHD();
            String maKH = dbData.get(i).getMaKH();
            String maNV = dbData.get(i).getMaNV();
            int triGia = dbData.get(i).getTriGia();

            Object[] tableData = {soHD, ngayHD, maKH, maNV, triGia};

            tableModel.addRow(tableData);
        }
        tHoaDon.setModel(tableModel);
        tHoaDon.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }
}

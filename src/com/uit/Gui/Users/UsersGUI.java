package com.uit.Gui.Users;

import com.uit.Gui.SanPham.SanPhamGUI;
import com.uit.Model.User;
import com.uit.Service.User.UserService;
import com.uit.Service.User.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsersGUI extends JFrame{
    private JPanel userFrame;
    private JTable tUser;
    private JButton btnLoadUser;
    private JButton btnCapNhatUser;
    private JButton btnThemUser;
    private JButton btnXoaUser;

    UserService userService = new UserServiceImpl();

    public UsersGUI() {
        setTitle("Quản Lý User");
        setContentPane(userFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1460, 800));
        setLocationRelativeTo(null);
        loadTableUser();
        ListSelectionModel listSelectionModel = tUser.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnLoadUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableUser();
            }
        });


        btnThemUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserForm form = new UserForm();
                form.setVisible(true);
                form.setTitle("Thêm User");
                form.setLabelHeader("Thêm User");
                form.setBtnAction("Thêm");
            }
        });


        btnCapNhatUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tUser.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(UsersGUI.this, "Vui lòng chọn user cần cập nhật!", "Cập nhật user", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String username = tUser.getModel().getValueAt(row, 0).toString();
                    String password = tUser.getModel().getValueAt(row, 1).toString();
                    String name = tUser.getModel().getValueAt(row, 2).toString();
                    String role = tUser.getModel().getValueAt(row, 3).toString();

                    UserForm form = new UserForm();
                    form.setVisible(true);
                    form.setTitle("Cập Nhật User");
                    form.setLabelHeader("Cập Nhật User");
                    form.setBtnAction("Cập Nhật");
                    form.setEnableUsername();
                    form.fillTextField(username, password, name, role);
                }
            }
        });


        btnXoaUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tUser.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(UsersGUI.this, "Vui lòng chọn user cần xóa!", "Xóa user", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String username = tUser.getModel().getValueAt(row, 0).toString();
                    String name = tUser.getModel().getValueAt(row, 2).toString();
                    int reply = JOptionPane.showConfirmDialog(UsersGUI.this, "Bạn có chắc muốn xóa " + name + " khỏi danh sách?", "Xóa user", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        userService.deleteUser(username);
                        JOptionPane.showMessageDialog(UsersGUI.this, "User " + name + " đã xóa thành công!", "Xóa user", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void loadTableUser () {
        String[] column = {"Username", "Password", "Name", "Role"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        List<User> dbData = userService.getAllUser();

        for (int i = 0; i < dbData.size(); i++) {
            String username = dbData.get(i).getUserName();
            String password = dbData.get(i).getPassword();
            String name = dbData.get(i).getName();
            String role = dbData.get(i).getRole();

            Object[] tableData = {username, password, name, role};

            tableModel.addRow(tableData);
        }
        tUser.setModel(tableModel);
        tUser.getTableHeader().setFont(new Font("Courier", Font.BOLD, 18));
    }
}

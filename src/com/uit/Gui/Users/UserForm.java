package com.uit.Gui.Users;

import com.uit.Gui.SanPham.SanPhamForm;
import com.uit.Model.User;
import com.uit.Service.User.UserService;
import com.uit.Service.User.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfName;
    private JComboBox cbRole;
    private JLabel lbUserFormHeader;
    private JPanel UserForm;
    private JButton btnAction;
    private JButton btnCancel;

    UserService userService = new UserServiceImpl();

    public UserForm () {
        setContentPane(UserForm);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        setComboBoxRole();

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String name = tfName.getText();
                String role = cbRole.getSelectedItem().toString();

                if (btnAction.getText().equals("Thêm")) {
                    if (!userService.checkUsername(username)) {
                        JOptionPane.showMessageDialog(UserForm.this, "Username " + username + " đã tồn tại. Vui lòng nhập lại username!", "Thêm user mới", JOptionPane.ERROR_MESSAGE );
                    } else if (username.isEmpty() || password.isEmpty() || name.isEmpty() || role.isEmpty()) {
                        JOptionPane.showMessageDialog(UserForm.this, "Vui lòng nhập đầy đủ thông tin user", "Thêm user mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        User newUser = new User(username, password, name, role);
                        userService.insertUser(newUser);
                        JOptionPane.showMessageDialog(UserForm.this, "User " + name + " đã được thêm thành công!", "Thêm user mới", JOptionPane.INFORMATION_MESSAGE );
                        dispose();
                    }
                } else if (btnAction.getText().equals("Cập Nhật")){
                    if (password.isEmpty() || name.isEmpty() || role.isEmpty()) {
                        JOptionPane.showMessageDialog(UserForm.this, "Vui lòng nhập đầy đủ thông tin user", "Thêm user mới", JOptionPane.ERROR_MESSAGE );
                    } else {
                        User updatedUser = new User(username, password, name, role);
                        userService.updateUser(updatedUser);
                        JOptionPane.showMessageDialog(UserForm.this, "User " + name + " đã được cập nhật thành công!", "Cập nhật user", JOptionPane.INFORMATION_MESSAGE );
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
        lbUserFormHeader.setText(label);
    }

    public void setBtnAction (String typeAction) {
        btnAction.setText(typeAction);
    }

    public void fillTextField (String username, String password, String name, String role) {
        tfUsername.setText(username);
        tfPassword.setText(password);
        tfName.setText(name);
        if (role == "Admin") {
            cbRole.setSelectedIndex(0);
        } else {
            cbRole.setSelectedIndex(1);
        }
    }

    public void setEnableUsername () {
        tfUsername.setEnabled(false);
    }

    public void setComboBoxRole () {
        cbRole.insertItemAt("Admin", 0);
        cbRole.insertItemAt("User", 1);
    }
}

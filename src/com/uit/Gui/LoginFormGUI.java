package com.uit.Gui;

import com.uit.Model.User;
import com.uit.Service.User.UserService;
import com.uit.Service.User.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFormGUI extends JFrame {
    private JPanel loginFrame;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    UserService userService = new UserServiceImpl();

    public LoginFormGUI() {
        setTitle("Đăng Nhập");
        setContentPane(loginFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tfUsername.getText();
                String password = String.valueOf(pfPassword.getPassword());

                boolean auth = userService.certifyUser(userName, password);
                String role = userService.getRole(userName);

                User user = userService.getUser(userName);

                if (auth && user.getRole().equals("Admin")) {
                    HomeAdminGUI homeAdmin = new HomeAdminGUI();
                    homeAdmin.setVisible(true);
                    homeAdmin.setLabelHeader(user.getName());
                    dispose();
                } else if (auth && user.getRole().equals("User")) {
                    HomeUserGUI homeUser = new HomeUserGUI();
                    homeUser.setVisible(true);
                    homeUser.setLabelHeader(user.getName());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Username hoặc Password không đúng", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

package com.uit.Service.User;

import com.uit.Dao.MyConnection;
import com.uit.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public void insertUser(User newUser) {
        try {
            String sql = "insert into USER (USERNAME, PASSWORD, NAME, ROLE) values(?,?,?,?)";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, newUser.getUserName());
            psta.setString(2, newUser.getPassword());
            psta.setString(3, newUser.getName());
            psta.setString(4, newUser.getRole());

            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User updatedUser) {
        try {
            String sql = "update USER set USERNAME = ?, PASSWORD = ?, NAME = ?, ROLE = ? where USERNAME = ?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.setString(1, updatedUser.getUserName());
            psta.setString(2, updatedUser.getPassword());
            psta.setString(3, updatedUser.getName());
            psta.setString(4, updatedUser.getRole());
            psta.setString(5, updatedUser.getUserName());

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String username) {
        try {
            String sql = "delete from USER where USERNAME = '" + username + "'";
            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            psta.execute();
            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUsername(String userName) {
        try {
            String sql = "select USERNAME from USER where USERNAME = '" + userName + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

//            psta.setString(1, userName);

            ResultSet resultSet = psta.executeQuery();

//            String resultUsername = null;
//            while (resultSet.next()) {
//                resultUsername = resultSet.getString("USERNAME");
//            }
//
//            psta.close();
//            conn.close();
//
//            if (resultUsername != null) {
//                return true;
//            }
            if (!resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = new ArrayList<>();

        try {
            String sql = "select USERNAME, PASSWORD, NAME, ROLE from USER";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setName(resultSet.getString("NAME"));
                user.setRole(resultSet.getString("ROLE"));

                allUsers.add(user);
            }

            psta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public boolean certifyUser(String userName, String password) {
        boolean isCertifyUser=false;
        try {
            String sql = "select * from USER where USERNAME='" + userName + "' and PASSWORD='" + password + "'";

//            String sql = "select * from USER where USERNAME=? and PASSWORD=?";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);
//            psta.setString(1, userName);
//            psta.setString(2, password);

            ResultSet resultSet = psta.executeQuery();
            if(resultSet.next()) {
                isCertifyUser = true;
            } else {
                isCertifyUser = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCertifyUser;
    }

    @Override
    public String getRole(String userName) {
        String role = null;

        try {
            String sql = "select ROLE from USER where USERNAME='" + userName + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("ROLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public User getUser(String userName) {
        User user = new User();

        try {
            String sql = "select USERNAME, PASSWORD, NAME, ROLE from USER where USERNAME='" + userName + "'";

            Connection conn = MyConnection.getConnection();
            PreparedStatement psta = conn.prepareStatement(sql);

            ResultSet resultSet = psta.executeQuery();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setName(resultSet.getString("NAME"));
                user.setRole(resultSet.getString("ROLE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}

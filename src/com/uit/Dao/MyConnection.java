package com.uit.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String DB_URL = "jdbc:mysql://localhost/java_swing_qlbh?serverTimezone=UTC";
            String USER_NAME = "root";
            String PASSWORD = "vuongtrian";

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Connect Failure! :(");
            ex.printStackTrace();
        }
        return conn;
    }
}

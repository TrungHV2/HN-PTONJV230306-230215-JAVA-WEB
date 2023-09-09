package com.ra.web.util;

import java.sql.*;

public class MySqlConnection {
    public static Connection open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:33061/test_db";
            String user = "root";
            String password = "Abc@123";
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

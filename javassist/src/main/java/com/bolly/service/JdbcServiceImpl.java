package com.bolly.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcServiceImpl {


    public void runJdbc() throws SQLException {
        Connection conn = DriverManager
                .getConnection(
                        "jdbc:mysql://127.0.0.1:3306/pssdb?useUnicode=true&;characterEncoding=UTF8",
                        "root", "123456");
        PreparedStatement statment = conn
                .prepareStatement("select * from t_user");
        statment.executeQuery();
        statment.close();
        conn.close();
    }
}

package com.bolly.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bolly
 */
public class C3p0ServiceImpl {
    ComboPooledDataSource dataSource;

    public C3p0ServiceImpl() {
        dataSource = new ComboPooledDataSource("mysql");

    }

    public void exec(String sql) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        try {
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
            resultSet.close();
        }finally {
            conn.close();
        }
    }
}

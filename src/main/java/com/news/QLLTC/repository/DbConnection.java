package com.news.QLLTC.repository;

import java.sql.Connection;

public class DbConnection {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/lich_tau";
    private static final String USER_NAME = "root";
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DB_URL, USER_NAME, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance.connection;
    }
}

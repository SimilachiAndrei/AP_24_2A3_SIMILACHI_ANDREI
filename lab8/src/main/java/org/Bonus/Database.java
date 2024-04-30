package org.Bonus;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:sample.db";
    private static HikariDataSource dataSource;

    private Database() {
    }

    static {
        initializeDataSource();
    }

    private static void initializeDataSource() {
        HikariConfig config = new HikariConfig();
        config.setAutoCommit(false);
        config.setJdbcUrl(URL);
        config.setMaximumPoolSize(500);
        config.setMaxLifetime(30000); // Set a maximum lifetime of 30 seconds
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection() {
        dataSource.close();
    }
}
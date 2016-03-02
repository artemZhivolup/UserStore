package ua.zhivolup.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        try{
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection to database has been found.");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

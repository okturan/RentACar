package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Db instance = null;
    private Connection connection = null;

    public Db() {
        try {
            String url = "jdbc:postgresql://localhost:5432/rentacar";
            String user = "postgres"; // new user
            String password = "1234";      // new password
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return instance.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }
}

package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Db instance = null;
    private Connection connection = null;

    private Db() {
        DatabaseConfig config = DatabaseConfig.load();

        try {
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to connect to PostgreSQL. Check the database configuration and server status.",
                    e
            );
        }
    }

    public static synchronized Connection getInstance() {
        try {
            if (instance == null || instance.connection.isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to inspect the PostgreSQL connection.", e);
        }

        return instance.connection;
    }

    public Connection getConnection() {
        return connection;
    }
}

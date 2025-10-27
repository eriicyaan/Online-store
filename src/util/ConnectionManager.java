package util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionManager {
    private final String URL = PropertiesUtil.get("db.url");
    private final String USER = PropertiesUtil.get("db.user");
    private final String PASSWORD = PropertiesUtil.get("db.password");


    public static Connection get() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

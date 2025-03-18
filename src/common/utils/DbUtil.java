package common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtil {
    private static final ResourceBundle rb = ResourceBundle.getBundle("common.config.dbconfig");

    private static final String URL = rb.getString("URL");
    private static final String USER = rb.getString("USER");
    private static final String PASSWORD = rb.getString("PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC 드라이버를 찾을 수 없습니다!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
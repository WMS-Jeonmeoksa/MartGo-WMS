package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/martgodb?serverTimezone=Asia/Seoul";
    private static final String USERNAME = "martgo";
    private static final String PASSWORD = "martgo1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

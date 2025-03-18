package common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    private static final String URL = "jdbc:mysql://localhost:3306/martgodb?serverTimezone=UTC";
    private static final String USER =  "martgo";
    private static final String PASSWORD =  "martgo1234";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

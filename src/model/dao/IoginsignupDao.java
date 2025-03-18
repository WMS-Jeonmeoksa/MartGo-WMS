package model.dao;

import common.utils.DbUtil;
import model.dto.AdminDto;
import model.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class IoginsignupDao {

    // 회원가입
    public static int insertUser(UserDto user) throws SQLException {
        int result = 0;
        String sql = "{CALL InsertUser(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 회원가입 시 입력받은 id를 user_id와 user_name에 사용합니다.
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getRole());
            pstmt.setInt(8, user.getAdmin_id());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 회원 로그인
    public UserDto loginUser(String userId, String userPassword) {
        UserDto user = null;
        // 컬럼 이름은 테이블 정의에 맞춰 user_pw 사용
        String sql = "SELECT * FROM User WHERE user_id = ? AND user_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserDto();
                    user.setUserId(rs.getString("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setUserPassword(rs.getString("user_pw"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 관리자 로그인
    public AdminDto loginAdmin(String adminId, String adminPassword) {
        AdminDto admin = null;
        String sql = "SELECT * FROM Admin WHERE admin_id = ? AND admin_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adminId);
            pstmt.setString(2, adminPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    admin = new AdminDto();
                    admin.setAdminId(rs.getString("admin_id"));
                    admin.setAdminName(rs.getString("admin_name"));
                    admin.setAdminPassword(rs.getString("admin_pw"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPhone(rs.getString("phone_num"));
                    admin.setAddress(rs.getString("address"));
                    admin.setRole(rs.getString("role"));
                    admin.setWarehouseId(rs.getInt("warehouse_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}

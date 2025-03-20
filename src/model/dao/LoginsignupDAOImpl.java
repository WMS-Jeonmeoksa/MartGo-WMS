package model.dao;

import common.utils.DbUtil;
import model.dto.AdminDTO;
import model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import static common.constants.ErrorCode.*;

public class LoginsignupDAOImpl implements  LoginsignupDAO {

    // 회원가입
    @Override
    public boolean insertUser(UserDTO user) throws SQLException {
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
            pstmt.setString(8, user.getAdmin_id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
        }
        return false;
    }

    // 회원 로그인
    @Override
    public UserDTO loginUser(String userId, String userPassword) {
        UserDTO user = null;
        // 컬럼 이름은 테이블 정의에 맞춰 user_pw 사용
        String sql = "SELECT * FROM User WHERE user_id = ? AND user_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserDTO(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_pw"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("admin_id") // NULL 이면 0으로 저장됨.
                );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
        }
        return user;
    }

    // 관리자 로그인
    @Override
    public AdminDTO loginAdmin(String adminId, String adminPassword) {
        AdminDTO admin = null;
        String sql = "SELECT * FROM Admin WHERE admin_id = ? AND admin_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adminId);
            pstmt.setString(2, adminPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    admin = new AdminDTO(
                            rs.getString("admin_id"),
                            rs.getString("admin_name"),
                            rs.getString("admin_pw"),
                            rs.getString("email"),
                            rs.getString("phone_num"),
                            rs.getString("role"),
                            rs.getInt("warehouse_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
        }
        return admin;
    }
    // 회원이 임대 신청하면 자동으로 '거래처'러 변경
    public boolean updateUserRoleToCustomer(String userId) {
        String sql = "UPDATE User SET role = '거래처' WHERE user_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 입고 승인 시, 창고 관리자 ID(admin_id) 저장
    public boolean assignWarehouseAdmin(String userId,int adminId) {
        String sql = "UPDATE User SET admin_id = ? WHERE user_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,adminId);
            pstmt.setString(2,userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // 로그인 성공 시 회원 id 반환
    @Override
    public String getUserIdByLogin(String userId, String userPassword){
        String retrievedUserId = null;
        String sql = "SELECT * FROM User WHERE user_id = ? AND user_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    retrievedUserId = rs.getString("user_id");
                    // 로그인 성공 시 해당 ID 반환
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
        }
        return retrievedUserId; // 로그인 실패 시 null 반환
    }

    // 로그인 성공 시 관리자 id 반환
    @Override
    public String getAdminIdByLogin(String adminId, String adminPassword){
        String retrievedUserId = null;
        String sql = "SELECT * FROM admin WHERE admin_id = ? AND admin_pw = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adminId);
            pstmt.setString(2, adminPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    retrievedUserId = rs.getString("admin_id");
                    // 로그인 성공 시 해당 ID 반환
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
        }
        return retrievedUserId; // 로그인 실패 시 null 반환
    }

    // 특정 회원 정보를 가져오기
    public UserDTO getUserById(String userId){
        UserDTO user = null;
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserDTO(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_pw"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("role"),
                            rs.getString("admin_id") // NULL 이면 0으로 저장됨.
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean deleteUser(String userId) throws SQLException {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(DATABASE_ERROR.getMessage());
            return false;
        }
    }


}



package model.dao;

import model.dto.AdminDTO;
import model.dto.UserDTO;

import java.sql.SQLException;

public interface LoginsignupDAO {
    // 회원가입: UserDto를 받아서, dao에서 처리 후  회원 가입에 성공하면 서비스에서 true를 반환
    boolean insertUser(UserDTO user) throws SQLException;

    // 회원 로그인: userId와 userPassword를 받아 로그인 성공 시 해당 회원 정보를 반환
    UserDTO loginUser(String userId, String userPassword);

    // 관리자 로그인: adminId와 adminPassword를 받아 로그인 성공 시 해당 관리자 정보를 반환
    AdminDTO loginAdmin(String adminId, String adminPassword);

    // 회원 로그인 성공 시, userId 반환
    String getUserIdByLogin(String userId, String userPassword);

    // 관리자 로그인 성공 시, adminId 반환
    String getAdminIdByLogin(String adminId, String adminPassword);
}

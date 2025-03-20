package model.service;

import model.dto.AdminDTO;
import model.dto.UserDTO;

public interface LoginSignupService {
    // 회원가입 : dao에서 로그인 성공 시 ture를 반환 실패시 false
    boolean signUpUser(UserDTO user);

    // dao에서 로그인 성공 시 UserDto를 반환
    UserDTO loginUser(String userId, String userPassword);
    // user 아이디와 비밀번호를 받아 로그인 성공시 회원id만 반환
    String getUserIdAfterLogin(String userId, String userPassword);
    // dao에서 로그인 성공 시 admindto를 반환
    AdminDTO loginAdmin(String adminId, String adminPassword);
    // admin 아이디와 비밀번호를 받아 로그인 성공 시 관리자id만 반환
    String getAdminIdAfterLogin(String adminId, String adminPassword);
    // 회원 삭제
    boolean deleteUser(String userId);

}

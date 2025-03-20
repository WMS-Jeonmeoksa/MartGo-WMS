package controller;

import model.dto.UserDTO;

public interface UserController {
    // 회원 메뉴 진입
    void memberMenu(UserDTO user);

    // 거래처 메뉴 진입
    void memberCustomerMenu(UserDTO user);

    // 현재 로그인한 사용자 ID 확인
    void checkCurrentLogin();

    // 회원 탈퇴(삭제) 기능.
    void deleteUser(UserDTO user);

}

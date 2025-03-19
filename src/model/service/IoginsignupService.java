package model.service;

import model.dao.LoginsignupDao;
import model.dto.AdminDto;
import model.dto.UserDto;

import java.sql.SQLException;

public class IoginsignupService {

    private LoginsignupDao dao = new LoginsignupDao();
    // 회원가입 (User)
    public boolean signUpUser(UserDto user) {
        try {
            return dao.insertUser(user); // insertUser()는 static 메서드이므로 Dao. 으로도 호출 가능
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 로그인
    public UserDto loginUser(String userid, String userPassword) {
        UserDto resultUser =  dao.loginUser(userid, userPassword); // login 성공했을 때 UserDto 반환
        return resultUser;
    }

    // 관리자 로그인
    public AdminDto loginAdmin(String adminId, String adminPassword) {
        AdminDto resultAdmin = dao.loginAdmin(adminId, adminPassword);
        return resultAdmin;
    }

//    // 회원정보 가져오기
//    public UserDto getUserInfo(String userId){
//        return dao.getUserbyId(userId); // 특정 회원 정보 조회
//    }
    // 로그인 후 ID 반환
    public String getUserIdAfterLogin(String userId, String userPassword) {
        return dao.getUserIdByLogin(userId, userPassword); // 로그인 성공하면, ID 반환
    }

}



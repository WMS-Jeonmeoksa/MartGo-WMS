package model.service;

import model.dao.Dao;
import model.dto.AdminDto;
import model.dto.UserDto;

import java.sql.SQLException;

public class Service {

    private Dao dao = new Dao();

    // 회원가입 (User)
    public boolean signUpUser(UserDto user) {
        try {
            int rows = Dao.insertUser(user); // insertUser()는 static 메서드이므로 Dao. 으로도 호출 가능
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 로그인
    public UserDto loginUser(String userid, String userPassword) {
        return dao.loginUser(userid, userPassword);
    }

    // 관리자 로그인
    public AdminDto loginAdmin(String adminName, String adminPassword) {
        return dao.loginAdmin(adminName, adminPassword);
    }
}



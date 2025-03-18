package model.service;

import model.dao.IoginsignupDao;
import model.dto.AdminDto;
import model.dto.UserDto;

import java.sql.SQLException;

public class IoginsignupService {

    private IoginsignupDao dao = new IoginsignupDao();

    // 회원가입 (User)
    public boolean signUpUser(UserDto user) {
        try {
            int rows = IoginsignupDao.insertUser(user); // insertUser()는 static 메서드이므로 Dao. 으로도 호출 가능
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 로그인
    public UserDto loginUser(String userid, String userPassword) {
        UserDto resultUser =  dao.loginUser(userid, userPassword);
        return resultUser;
    }

    // 관리자 로그인
    public AdminDto loginAdmin(String adminId, String adminPassword) {
        AdminDto resultAdmin = dao.loginAdmin(adminId, adminPassword);
        return resultAdmin;
    }
}



package controller;

import java.util.Scanner;

import common.utils.ValidationUtil;
import model.dto.UserDTO;
import model.dto.AdminDTO;
import model.service.LoginSignupService;
import model.service.LoginSignupServiceImpl;

import static common.constants.MessageEnum.*;

public class LoginSignupControllerImpl implements LoginSignupController {
    private LoginSignupService service = new LoginSignupServiceImpl();
    private Scanner scan = new Scanner(System.in);
    private UserControllerImpl memberController = new UserControllerImpl();
    private AdminControllerImpl adminController = new AdminControllerImpl();
    private UserDTO userDto;
    private AdminDTO adminDto;
    // 메인 메뉴
    public void start() {
        while (true) {
            System.out.println(MAIN_MENU.getMessage());
            System.out.println(MAIN_LOGIN.getMessage());
            System.out.println(MAIN_SIGNUP.getMessage());
            System.out.println(MAIN_EXIT.getMessage());
            System.out.print(MAIN_CHOICE.getMessage());
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println(MAIN_EXIT_MENU.getMessage());
                    return;
                default:
                    System.out.println(MAIN_INVALID_INPUT.getMessage());
            }
        }
    }


    private void login() {
        String id;
        while (true) { // 아이디 입력 반복
            System.out.print(LOGIN_SIGNUP_ID.getMessage());
            id = scan.nextLine().trim();

            // 아이디 유효성 검사
            if (!ValidationUtil.isValidUserId(id)) {
                System.out.println(LOGIN_SIGNUP_ID_CHECK.getMessage());
                continue; // 아이디 다시 입력받기
            }
            break; // 유효한 아이디 입력되면 비밀번호 입력으로 넘어감
        }

        String password;
        while (true) { // 비밀번호 입력 반복
            System.out.print(LOGIN_SIGNUP_PASSWORD.getMessage());
            password = scan.nextLine().trim();

            // 비밀번호 유효성 검사
            if (!ValidationUtil.isValidPassword(password)) {
                System.out.println(LOGIN_SIGNUP_PASSWORD_CHECK.getMessage());
                continue; // 비밀번호 다시 입력받기
            }
            break; // 유효한 비밀번호 입력되면 로그인 시도
        }

        // 먼저 회원 테이블에서 로그인 시도
        UserDTO user = service.loginUser(id, password);
        AdminDTO admin = service.loginAdmin(id, password);

        if(user != null){

            switch (user.getRole()) {
                case "회원":
                    this.userDto = user;
                    System.out.println("\n회원 로그인 성공! " + user.getUserName() + "님 환영합니다.");
                    memberController.memberMenu(user);
                    userIdReturn();// 로그인시 유저리턴에 저장
                    return;
                case "거래처":
                    this.userDto = user;
                    System.out.println("\n회원(거래처) 로그인 성공! " + user.getUserName() + "님 환영합니다.");
                    memberController.memberCustomerMenu(user);
                    userIdReturn();// 로그인시 유저리턴에 저장
                    return;
            }
        }

        // 회원로그인 실패시, 관리자 로그인 시도
        if(admin != null) {
            switch (admin.getRole()) {
                case "창고관리자" :
                    this.adminDto = admin;
                    System.out.println("\n관리자(창고관리자) 로그인 성공! " + admin.getAdminName() + "님 환영합니다");
                    adminController.warehouseAdminMenu(admin);
                    adminIdReturn();// 로그인시 관리자리턴에 저장
                    return;
                case "총관리자" :
                    this.adminDto = admin;
                    System.out.println("\n관리자(총관리자) 로그인 성공! " + admin.getAdminName() + "님 환영합니다");
                    adminController.superAdminMenu(admin);
                    adminIdReturn();// 로그인시 관리자리턴에 저장
                    return;
            }
        }

        // 최종 로그인 실패
        System.out.println(LOGIN_FAILED.getMessage());
    }



    // 회원가입 처리: 필수 입력값을 올바르게 받을 때까지 반복
    private void signUp() {
        System.out.println(SIGNUP_MENU.getMessage());

        String id;
        while (true) {
            System.out.print(LOGIN_SIGNUP_ID.getMessage());
            id = scan.nextLine().trim();
            if (id.isEmpty() || !ValidationUtil.isValidUserId(id)) {
                System.out.println(LOGIN_SIGNUP_ID_CHECK.getMessage());
                continue;
            }
            break;
        }

        String name;
        while (true) {
            System.out.print(SIGNUP_NAME.getMessage());
            name = scan.nextLine().trim();
            if (name.isEmpty() || !ValidationUtil.isValidName(name)) {
                System.out.println(SIGNUP_NAME_CHECK.getMessage());
                continue;
            }
            break;
        }

        // 비밀번호 입력
        String password;
        while (true) {
            System.out.print(LOGIN_SIGNUP_PASSWORD.getMessage());
            password = scan.nextLine().trim();
            if (password.isEmpty() || !ValidationUtil.isValidPassword(password)) {
                System.out.println(LOGIN_SIGNUP_PASSWORD_CHECK.getMessage());
                continue;
            }
            break;
        }
        // 비밀번호 확인만 반복해서 입력받음
        String passwordcheck;
        while (true) {
            System.out.print(SIGNUP_PASSWORD_CHECK.getMessage());
            passwordcheck = scan.nextLine().trim();
            if (!password.equals(passwordcheck)) {
                System.out.println(SIGNUP_PASSWORD_CHECK_MESSAGE.getMessage());
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            System.out.print(SIGNUP_PHONE.getMessage());
            phone = scan.nextLine().trim();
            if (phone.isEmpty() || !ValidationUtil.isValidPhone(phone)) {
                System.out.println(SIGNUP_PHONE_CHECK.getMessage());
                continue;
            }
            break;
        }

        System.out.print(SIGNUP_EMAIL.getMessage());
        String email = scan.nextLine().trim();
        if (email.isEmpty() || !ValidationUtil.isValidEmail(email)) {
            email = null;
        }

        String address;
        while (true) {
            System.out.print(SIGNUP_ADDRESS.getMessage());
            address = scan.nextLine().trim();
            if (address.isEmpty() ||  !ValidationUtil.isValidAddress(address)) {
                System.out.println(SIGNUP_ADDRESS_CHECK.getMessage());
                continue;
            }
            break;
        }

        UserDTO user = new UserDTO(id,name,password,phone,email,address,"회원",null); //아직admin_id를 모르니깐 0으로 입력

        boolean result = service.signUpUser(user);
        if (result) {
            System.out.println(SIGNUP_SUCCESS.getMessage());
        } else {
            System.out.println(SIGNUP_FAIL.getMessage());
        }
    }


    // user아이디 반환메서드
    public String userIdReturn() {
        if(userDto == null) {
            return null;
        }
        return userDto.getUserId();
    }

    // admin아이디 반환메서드
    public  String adminIdReturn() {
        if(adminDto == null) {
            return null;
        }
        return adminDto.getAdminId();
    }



    public static void main(String[] args) {
        LoginSignupControllerImpl controller = new LoginSignupControllerImpl();
        controller.start();

    }
}

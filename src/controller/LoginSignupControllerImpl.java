package controller;

import java.util.Scanner;
import model.dto.UserDTO;
import model.dto.AdminDTO;
import model.service.LoginSignupServiceImpl;

public class LoginSignupControllerImpl implements LoginSignupController {
    private LoginSignupServiceImpl service = new LoginSignupServiceImpl();
    private Scanner scan = new Scanner(System.in);
    private UserControllerImpl memberController = new UserControllerImpl();
    private AdminControllerImpl adminController = new AdminControllerImpl();
    private UserDTO userDto;
    private AdminDTO adminDto;
    // 메인 메뉴
    public void start() {
        while (true) {
            System.out.println("----- 메인 메뉴 -----");
            System.out.println("1. 현재 로그인 상태");
            System.out.println("2. 로그인");
            System.out.println("3. 회원가입");
            System.out.println("4. 종료");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    memberController.checkCurrentLogin();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    signUp();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }


    private void login() {
        System.out.print("아이디: ");
        String id = scan.nextLine();
        System.out.print("비밀번호: ");
        String password = scan.nextLine();


//        String userId = service.getUserIdAfterLogin(id,password); // 로그인 후 ID 가져오기

//        // 로그인 실패
//        if (userId == null) {
//            System.out.println("\n 로그인 실패 : 아이디, 비밀번호 다시 확인하세요.");
//            return;
//        }
//        //여기에 안걸렸다면 로그인이 성공된거긴 함

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
        System.out.println("\n로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
    }



    // 회원가입 처리: 필수 입력값을 올바르게 받을 때까지 반복
    private void signUp() {
        System.out.println("=== 회원가입 ===");

        String id;
        while (true) {
            System.out.print("아이디: ");
            id = scan.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("아이디는 필수 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        String name;
        while (true) {
            System.out.print("이름: ");
            name = scan.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("이름은 필수 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        // 비밀번호 입력
        String password;
        while (true) {
            System.out.print("비밀번호 : ");
            password = scan.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("비밀번호는 필수 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }
        // 비밀번호 확인만 반복해서 입력받음
        String passwordcheck;
        while (true) {
            System.out.print("비밀번호확인 : ");
            passwordcheck = scan.nextLine().trim();
            if (!password.equals(passwordcheck)) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            System.out.print("전화번호: ");
            phone = scan.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("전화번호는 필수 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        System.out.print("이메일 (입력하지 않으면 null 처리): ");
        String email = scan.nextLine().trim();
        if (email.isEmpty()) {
            email = null;
        }

        String address;
        while (true) {
            System.out.print("주소: ");
            address = scan.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("주소는 필수 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        UserDTO user = new UserDTO(id,name,password,phone,email,address,"회원","0"); //아직admin_id를 모르니깐 0으로 입력

        boolean result = service.signUpUser(user);
        if (result) {
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("회원가입 실패!");
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

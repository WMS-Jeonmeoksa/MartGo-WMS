package controller;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import model.dto.UserDto;
import model.dto.AdminDto;
import model.service.Service;

public class Controller {
    private Service service = new Service();
    private Scanner scan = new Scanner(System.in);

    // 회원가입: 필수 항목이 빈 값이면 계속 재입력을 요구함.
    public boolean signUpUser(String id, String username, String userPassword, String phone,
                              String email, String address) {
        UserDto user = new UserDto();
        user.setUserId(id);
        user.setUserName(username);
        user.setUserPassword(userPassword);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        return service.signUpUser(user);
    }

    // 회원 로그인
    public UserDto loginUser(String userid, String userPassword) {
        return service.loginUser(userid, userPassword);
    }

    // 관리자 로그인
    public AdminDto loginAdmin(String adminId, String adminPassword) {
        return service.loginAdmin(adminId, adminPassword);
    }

    // 메인 메뉴
    public void start() {
        while (true) {
            System.out.println("----- 메인 메뉴 -----");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 로그인: 아이디와 비밀번호를 통해 회원 또는 관리자 로그인 후 전용 메뉴 진입
    private void login() {
        System.out.print("아이디: ");
        String id = scan.nextLine();
        System.out.print("비밀번호: ");
        String password = scan.nextLine();


        // 먼저 회원 테이블에서 로그인 시도
        UserDto user = loginUser(id, password);
        if (user != null) {
            System.out.println("\n회원 로그인 성공! " + user.getUserName() + "님 환영합니다.");
            memberMenu(user);
        } else {
            // 회원 정보가 없으면 관리자 테이블에서 로그인 시도
            AdminDto admin = loginAdmin(id, password);
            if (admin != null) {
                System.out.println("\n관리자 로그인 성공! " + admin.getAdminName()
                        + " (" + admin.getRole() + ")");
                adminMenu(admin);
            } else {
                System.out.println("\n로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
            }
        }
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

        boolean result = signUpUser(id, name, password, phone, email, address);
        if (result) {
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("회원가입 실패!");
        }
    }


    // 로그인 후 회원 전용 메뉴
    private void memberMenu(UserDto user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 회원 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 로그아웃");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\n=== 내 정보 ===");
                    System.out.println("아이디: " + user.getUserId());
                    System.out.println("이름: " + user.getUserName());
                    System.out.println("전화번호: " + user.getPhone());
                    System.out.println("이메일: " + user.getEmail());
                    System.out.println("주소: " + user.getAddress());
                    System.out.println("역할: " + user.getRole());
                    break;
                case 2:
                    System.out.println("로그아웃 되었습니다.");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // 로그인 후 관리자 전용 메뉴
    private void adminMenu(AdminDto admin) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 관리자 메뉴 =====");
            System.out.println("1. 관리자 정보 보기");
            System.out.println("2. 로그아웃");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\n=== 관리자 정보 ===");
                    System.out.println("아이디: " + admin.getAdminId());
                    System.out.println("이름: " + admin.getAdminName());
                    System.out.println("전화번호: " + admin.getPhone());
                    System.out.println("이메일: " + admin.getEmail());
                    System.out.println("역할: " + admin.getRole());
                    break;
                case 2:
                    System.out.println("로그아웃 되었습니다.");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
    }
}

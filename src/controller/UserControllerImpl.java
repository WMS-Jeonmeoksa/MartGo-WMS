package controller;

import model.dto.UserDTO;
import model.service.LoginSignupService;
import model.service.LoginSignupServiceImpl;

import java.util.Scanner;

public class UserControllerImpl implements UserController {

    private Scanner scan = new Scanner(System.in);
    private String userId = null;
    private UserDTO userDto;
    private LoginSignupService service = new LoginSignupServiceImpl();

    @Override
    public void memberMenu(UserDTO user) {
        userId = user.getUserId();
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 회원 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 임대신청");
            System.out.println("3. 로그아웃");
            System.out.println("4. 회원 탈퇴");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    showInfo(user);
                    LoginSignupControllerImpl loginsignupController = new LoginSignupControllerImpl();
                    loginsignupController.userIdReturn();
                    break;
                case 2:
                    applyForRental();
                    break;
                case 3:
                    logout();
                    loggedIn = false;
                    break;
                case 4:
                    deleteUser(user);
                    if (userId == null) {
                        loggedIn = false;
                    }
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");

            }
        }
    }


    @Override
    public void memberCustomerMenu(UserDTO user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 거래처 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 제품 등록");
            System.out.println("3. 입고");
            System.out.println("4. 출고");
            System.out.println("5. 재고 조회");
            System.out.println("6. 로그아웃");
            System.out.println("7. 회원 탈퇴");
            System.out.print("선택 > ");

            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {

                case 1:
                    showInfo(user);
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    receiveStock();
                    break;
                case 4:
                    dispatchStock();
                    break;
                case 5:
                    checkInventory();
                    break;
                case 6:
                    logout();
                    loggedIn = false;
                    break;
                case 7:
                    deleteUser(user);
                    if (userId == null) {
                        loggedIn = false;
                    }
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }


    private void showInfo(UserDTO user) {
        System.out.println("\n=== 내 정보 ===");
        System.out.println("아이디: " + user.getUserId());
        System.out.println("이름: " + user.getUserName());
        System.out.println("전화번호: " + user.getPhone());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("주소: " + user.getAddress());
        System.out.println("권한: " + user.getRole());
        System.out.println("담당 창고 관리자 ID: " + user.getAdmin_id());
    }


    //일반회원
    private void applyForRental() {
        System.out.println("임대 신청");
    }

    // 거래처
    private void addProduct() {
        System.out.println("제품 등록");
    }

    private void receiveStock() {
        System.out.println("입고");
    }

    private void dispatchStock() {
        System.out.println("출고");
    }

    private void checkInventory() {
        System.out.println("재고 조회");
    }


    @Override
    public void deleteUser(UserDTO user) {
        userId = user.getUserId();
        if (userId == null) {
            System.out.println("먼저 로그인 해주세요.");
            return;
        }

        System.out.println("정말로 회원 탈퇴하시겠습니까? (Y/N): ");
        String choice = scan.nextLine().trim();

        if (choice.equalsIgnoreCase("Y")) {
            boolean result = service.deleteUser(user.getUserId());
            if (result) {
                System.out.println("회원 탈퇴가 완료되었습니다.");
                // 탈퇴 후 로그인 정보 삭제
                userDto = null;
                userId = null;
            } else {
                System.out.println("회원 탈퇴에 실패하였습니다.");
            }
        } else {
            System.out.println("회원 탈퇴를 취소합니다.");
        }
    }



    // 로그인: 아이디와 비밀번호를 통해 회원 또는 관리자 로그인 후 전용 메뉴 진입
    private void logout() {
        System.out.println("\n로그아웃 되었습니다.");
        userId = null; // 로그인 ID 초기화
    }

    // 현재 로그인 id 확인 메서드
    @Override
    public void checkCurrentLogin() {
        if (userId == null) {
            System.out.println("현재 로그인된 사용자가 없습니다.");
        } else {
            System.out.println("현재 로그인한 사용자 ID :" + userId);
        }
    }
}




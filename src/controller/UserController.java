package controller;

import model.dto.UserDto;

import java.util.Scanner;

public class UserController {
    IncomingController incomingController;
    OutgoingController outgoingController;
    RentController rentController;
    StockControllerImpl stockController;
    ProductControllerImpl productController;
    LoginsignupController loginsignupController;

    private Scanner scan = new Scanner(System.in);
    private String userId = null;

    public void memberMenu(UserDto user) {
        userId = user.getUserId();
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 회원 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 임대신청");
            System.out.println("3. 로그아웃");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    showInfo(user);
                    loginsignupController.userIdReturn();
                    break;
                case 2:
                    RentControllerImpl rentController = new RentControllerImpl();
                    rentController.handleRentRequest(user.getUserId());
                    break;
                case 3:
                    logout();
                    loggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");

            }
        }
    }


    public void memberCustomerMenu(UserDto user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 거래처 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 제품 등록");
            System.out.println("3. 입고");
            System.out.println("4. 출고");
            System.out.println("5. 재고 조회");
            System.out.println("6. 로그아웃");
            System.out.print("선택 > ");

            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {

                case 1:
                    showInfo(user);
                    break;
                case 2:
                    productController.registerProduct(user.getUserId());
                    break;
                case 3:
                    incomingController.requestIncoming(user.getUserId());
                    break;
                case 4:
                    outgoingController.requestOutgoing(user.getUserId());
                    break;
                case 5:
                    stockController.printUserStock(user.getUserId());
                    break;
                case 6:
                    logout();
                    loggedIn = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }


    private void showInfo(UserDto user) {
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
    public void applyForRental() {
        System.out.println("임대 신청");
    }

    // 거래처
    public void addProduct() {
        System.out.println("제품 등록");
    }

    public void receiveStock() {
        System.out.println("입고");
    }

    public void dispatchStock() {
        System.out.println("출고");
    }

    public void checkInventory() {
        System.out.println("재고 조회");
    }


    // 로그인: 아이디와 비밀번호를 통해 회원 또는 관리자 로그인 후 전용 메뉴 진입
    private void logout() {
        System.out.println("\n로그아웃 되었습니다.");
        userId = null; // 로그인 ID 초기화
    }

    // 현재 로그인 id 확인 메서드
    public void checkCurrentLogin() {
        if (userId == null) {
            System.out.println("현재 로그인된 사용자가 없습니다.");
        } else {
            System.out.println("현재 로그인한 사용자 ID :" + userId);
        }
    }
}




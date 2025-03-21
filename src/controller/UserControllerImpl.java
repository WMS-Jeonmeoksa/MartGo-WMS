package controller;

import model.dto.UserDTO;
import model.service.LoginSignupService;
import model.service.LoginSignupServiceImpl;

import java.util.Scanner;

import static common.constants.MessageEnum.*;

public class UserControllerImpl implements UserController {
    RentController rentController = new RentControllerImpl();
    IncomingController incomingController = new IncomingControllerImpl();
    OutgoingController outgoingController = new OutgoingControllerImpl();
    StockController stockController = new StockControllerImpl();
    ProductController productController = new ProductControllerImpl();

    private Scanner scan = new Scanner(System.in);
    private String userId = null;
    private UserDTO userDto;
    private LoginSignupService service = new LoginSignupServiceImpl();

    @Override
    public void memberMenu(UserDTO user) {
        userId = user.getUserId();
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println(USER_MAIN_MENU.getMessage());
            System.out.println(MY_PAGE.getMessage());
            System.out.println(USER_RENT_APPLICATION.getMessage());
            System.out.println(USER_LOG_OUT.getMessage());
            System.out.println(USER_DELETE.getMessage());
            System.out.print(MAIN_CHOICE.getMessage());
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    showInfo(user);
                    LoginSignupControllerImpl loginsignupController = new LoginSignupControllerImpl();
                    loginsignupController.userIdReturn();
                    break;
                case 2:
                    rentController.applyRent(user.getUserId());
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
                    System.out.println(MAIN_INVALID_INPUT.getMessage());

            }
        }
    }


    @Override
    public void memberCustomerMenu(UserDTO user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println(USER_CUSTOMER_MAIN_MENU.getMessage());
            System.out.println(MY_PAGE.getMessage());
            System.out.println(USER_CUSTOMER_PRODUCT.getMessage());
            System.out.println(USER_CUSTOMER_OUTGOING.getMessage());
            System.out.println(USER_CUSTOMER_INCOMING.getMessage());
            System.out.println(USER_CUSTOMER_STOCK.getMessage());
            System.out.println(USER_LOG_OUT_6.getMessage());
            System.out.print(MAIN_CHOICE.getMessage());

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
                case 7:
                    deleteUser(user);
                    if (userId == null) {
                        loggedIn = false;
                    }
                default:
                    System.out.println(MAIN_INVALID_INPUT.getMessage());
            }
        }
    }


    private void showInfo(UserDTO user) {
        System.out.println(USER_SHOW_INFO.getMessage());
        System.out.println("아이디: " + user.getUserId());
        System.out.println("이름: " + user.getUserName());
        System.out.println("전화번호: " + user.getPhone());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("주소: " + user.getAddress());
        System.out.println("권한: " + user.getRole());
        System.out.println("담당 창고 관리자 ID: " + user.getAdmin_id());
    }



    @Override
    public void deleteUser(UserDTO user) {
        userId = user.getUserId();
        if (userId == null) {
            System.out.println(DELETE_LOGIN_FIRST.getMessage());
            return;
        }

        System.out.println(DELETE_CHECK.getMessage());
        String choice = scan.nextLine().trim();

        if (choice.equalsIgnoreCase("Y")) {
            boolean result = service.deleteUser(user.getUserId());
            if (result) {
                System.out.println(DELETE_COMPLETE.getMessage());
                // 탈퇴 후 로그인 정보 삭제
                userDto = null;
                userId = null;
            } else {
                System.out.println(DELETE_FAIL.getMessage());
            }
        } else {
            System.out.println(DELETE_CANCEL.getMessage());
        }
    }



    // 로그인: 아이디와 비밀번호를 통해 회원 또는 관리자 로그인 후 전용 메뉴 진입
    private void logout() {
        System.out.println(LOG_OUT_MESSAGE.getMessage());
        userId = null; // 로그인 ID 초기화
    }

    // 현재 로그인 id 확인 메서드
    @Override
    public void checkCurrentLogin() {
        if (userId == null) {
            System.out.println(NOW_USER_LOGIN_STATUS.getMessage());
        } else {
            System.out.println("현재 로그인한 사용자 ID :" + userId);
        }
    }
}




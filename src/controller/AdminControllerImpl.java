package controller;

import model.dto.AdminDTO;


import java.util.Scanner;

import static common.constants.MessageEnum.*;

public class AdminControllerImpl implements AdminController {
    RentController rentController = new RentControllerImpl();
    IncomingController incomingController = new IncomingControllerImpl();
    OutgoingController outgoingController = new OutgoingControllerImpl();
    StockController stockController = new StockControllerImpl();

    private Scanner scan = new Scanner(System.in);
    private String adminId = null; // 로그인한 관리자 ID 저장

    // 창고 관리자 메뉴
    @Override
    public void warehouseAdminMenu(AdminDTO admin) {
        adminId = admin.getAdminId(); // 로그인한 관리자 ID 저장
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println(WAREHOUSE_ADMIN_MENU.getMessage());
            System.out.println(ADMIN_PAGE.getMessage());
            System.out.println(WAREHOUSE_ADMIN_WAITING_RENT.getMessage());
            System.out.println(WAREHOUSE_ADMIN_WAITING_OUTGOING.getMessage());
            System.out.println(WAREHOUSE_ADMIN_WAITING_INCOMING.getMessage());
            System.out.println(WAREHOUSE_ADMIN_STOCK.getMessage());
            System.out.println(WAREHOUSE_ADMIN_STOCK_HISTORY.getMessage());
            System.out.println(ADMIN_LOG_OUT.getMessage());
            System.out.print(MAIN_CHOICE.getMessage());
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    viewAdminInfo(admin);
                    break;
                case 2:
                    rentController.holdRentList(adminId);
                    break;
                case 3:
                    incomingController.approveIncoming(adminId);
                    break;
                case 4:
                    outgoingController.approveOutgoing(adminId);
                    break;
                case 5:
                    stockController.printAdminUserStock(adminId);
                    break;
                case 6:
                    stockController.printAdminStockHistory(adminId);
                    break;
                case 7:
                    logout();
                    loggedIn = false;
                    break;
                default:
                    System.out.println(MAIN_INVALID_INPUT.getMessage());
            }
        }
    }

    @Override
    public void superAdminMenu(AdminDTO admin) {
        adminId = admin.getAdminId(); // 로그인한 관리자 ID
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println(SUPER_ADMIN_MENU.getMessage());
            System.out.println(ADMIN_PAGE.getMessage());
            System.out.println(SUPER_ADMIN_PROGRESS_RENT.getMessage());
            System.out.println(SUPER_ADMIN_PROGRESS_OUTGING.getMessage());
            System.out.println(SUPER_ADMIN_PROGRESS_INCOMING.getMessage());
            System.out.println(SUPER_ADMIN_STOCK.getMessage());
            System.out.println(SUPER_ADMIN_STOCK_HISTORY.getMessage());
            System.out.println(ADMIN_LOG_OUT.getMessage());
            System.out.print(MAIN_CHOICE.getMessage());
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    viewAdminInfo(admin);
                    break;
                case 2:
                    rentController.inProgressRentList(adminId);
                    break;
                case 3:
                    incomingController.approveIncoming(adminId);
                    break;
                case 4:
                    outgoingController.approveOutgoing(adminId);
                    break;
                case 5:
                    stockController.printGeneralStock(adminId);
                    break;
                case 6:
                    stockController.printGeneralStockHistory(adminId);
                    break;
                case 7:
                    logout();
                    loggedIn = false;
                    break;

                default:
                    System.out.println(MAIN_INVALID_INPUT.getMessage());
            }
        }

    }

    // 관리자 정보 보기 (공통)
    private void viewAdminInfo(AdminDTO admin) {
        System.out.println(ADMIN_PAGE_COMMON.getMessage());
        System.out.println("아이디: " + admin.getAdminId());
        System.out.println("이름: " + admin.getAdminName());
        System.out.println("전화번호: " + admin.getPhone());
        System.out.println("이메일: " + admin.getEmail());
        System.out.println("권한: " + admin.getRole());
        System.out.println("담당 창고 ID: " + admin.getWarehouseId());
    }

    private void logout() {
        System.out.println(LOG_OUT_MESSAGE.getMessage());
        adminId = null;
    }

    public void checkCurrentLogin() {
        if (adminId == null) {
            System.out.println(NOW_ADMIN_LOGIN_STATUS.getMessage());
        } else {
            System.out.println("현재 로그인한 관리자 ID : " + adminId);
        }
    }


}

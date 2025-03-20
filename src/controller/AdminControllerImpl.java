package controller;

import model.dto.AdminDTO;
import model.dto.UserDTO;

import java.util.Scanner;

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
            System.out.println("\n===== 창고 관리자 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 대기중인 임대신청 목록");
            System.out.println("3. 대기중인 입고신청 목록");
            System.out.println("4. 대기중인 출고신청 목록");
            System.out.println("5. 담당한 창고의 재고 목록");
            System.out.println("6. 담당한 창고의 재고 변경 이력");
            System.out.println("7. 로그아웃");
            System.out.print("선택 > ");
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
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    @Override
    public void superAdminMenu(AdminDTO admin) {
        adminId = admin.getAdminId(); // 로그인한 관리자 ID
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 총 관리자 메뉴 =====");
            System.out.println("1. 관리자 정보 보기");
            System.out.println("2. 진행중인 임대신청 목록");
            System.out.println("3. 진행중인 입고신청 목록");
            System.out.println("4. 진행중인 출고신청 목록");
            System.out.println("5. 담당한 창고의 재고 목록");
            System.out.println("6. 담당한 창고의 재고 변경 이력");
            System.out.println("7. 로그아웃");
            System.out.print("선택 > ");
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
                    System.out.println("잘못된 선택입니다.");
            }
        }

    }

    // 관리자 정보 보기 (공통)
    private void viewAdminInfo(AdminDTO admin) {
        System.out.println("\n=== 관리자 정보 ===");
        System.out.println("아이디: " + admin.getAdminId());
        System.out.println("이름: " + admin.getAdminName());
        System.out.println("전화번호: " + admin.getPhone());
        System.out.println("이메일: " + admin.getEmail());
        System.out.println("권한: " + admin.getRole());
        System.out.println("담당 창고 ID: " + admin.getWarehouseId());
    }

    private void logout() {
        System.out.println("\n 로그아웃 되었습니다.");
        adminId = null;
    }

    public void checkCurrentLogin() {
        if (adminId == null) {
            System.out.println("현재 로그인된 관리자가 없습니다.");
        } else {
            System.out.println("현재 로그인한 관리자 ID : " + adminId);
        }
    }


}

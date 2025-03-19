package controller;

import model.dto.AdminDto;

import java.util.Scanner;

public class AdminController {

    private Scanner scan = new Scanner(System.in);
    private String adminId = null; // 로그인한 관리자 ID 저장

    // 창고 관리자 메뉴
    public void warehouseAdminMenu(AdminDto admin) {
        adminId = admin.getAdminId(); // 로그인한 관리자 ID 저장
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 창고 관리자 메뉴 =====");
            System.out.println("1. 내 정보 보기");
            System.out.println("2. 대기중인 임대신청 목록");
            System.out.println("3. 대기중인 입고신청 목록");
            System.out.println("4. 대기중인 출고신청 목록");
            System.out.println("5. 로그아웃");
            System.out.println("6. 로그인된 아이디 보기");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    viewAdminInfo(admin);
                    break;
                case 2:
                    showPendingLeaseRequests(); // 대기중인 임대 신청 목록 조회
                    break;
                case 3 :
                    showPendingStockRequests(); // 대기중인 입고 신청 목록 조회
                    break;
                case 4:
                    showPendingReleaseRequests(); // 대기중인 출고신청 목록 조회
                    break;
                case 5:
                    logout();
                    loggedIn = false;
                    break;
                case 6:
                    checkCurrentLogin();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void superAdminMenu(AdminDto admin) {
        adminId = admin.getAdminId(); // 로그인한 관리자 ID
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== 총 관리자 메뉴 =====");
            System.out.println("1. 관리자 정보 보기");
            System.out.println("2. 진행중인 임대신청 목록");
            System.out.println("3. 진행중인 입고신청 목록");
            System.out.println("4. 진행중인 출고신청 목록");
            System.out.println("5. 로그아웃");
            System.out.println("6. 로그인된 아이디 보기");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    viewAdminInfo(admin);
                    break;
                case 2:
                    showOngoingLeaseRequests();
                    break;
                case 3:
                    showOngoingStockRequests();
                    break;
                case 4:
                    showOngoingReleaseRequests();
                    break;
                case 5:
                    logout();
                    loggedIn = false;
                    break;
                case 6:
                    checkCurrentLogin();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }

    }
    // 관리자 정보 보기 (공통)
    private void viewAdminInfo(AdminDto admin) {
        System.out.println("\n=== 관리자 정보 ===");
        System.out.println("아이디: " + admin.getAdminId());
        System.out.println("이름: " + admin.getAdminName());
        System.out.println("전화번호: " + admin.getPhone());
        System.out.println("이메일: " + admin.getEmail());
        System.out.println("권한: " + admin.getRole());
        System.out.println("담당 창고 ID: " + admin.getWarehouseId());
    }

    // 창고 관리자
    private void showPendingStockRequests() {
        System.out.println("대기중인 입고 신청 목록 조회 구현 예정");
    }
    private void showPendingLeaseRequests() {
        System.out.println("임대");
    }
    private void showPendingReleaseRequests(){
        System.out.println("출고");
    }


    // 총 관리자
    private void showOngoingStockRequests() {
        System.out.println("진행중인 입고 신청 목록 조회 구현 예정");
    }
    private void showOngoingLeaseRequests() {
        System.out.println("진행 중인 임대");
    }
    private void showOngoingReleaseRequests(){
        System.out.println("진행 중인 출고");
    }

//    // 전체 관리자 계정 조회(총관리자)
//    private void showAllAdmins(){
//        System.out.println("전체 관리자 계정 목록 조회 기능 구현");
//    }

    private void logout() {
        System.out.println("\n 로그아웃 되었습니다.");
        adminId = null;
    }

    public void checkCurrentLogin() {
        if (adminId == null){
            System.out.println("현재 로그인된 관리자가 없습니다.");
        }
        else{
            System.out.println("현재 로그인한 관리자 ID : " + adminId);
        }
    }










}

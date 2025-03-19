package view;

import model.dto.RentHistoryDTO;

import java.util.Scanner;

public class RentViewImpl {

    Scanner sc = new Scanner(System.in);

    public int displayMenu() {
        System.out.println("1. 임대신청");
        System.out.println("2. 임대 신청 대기 목록");   // 관리자 메뉴에서만 보이도록 수정 예정
        System.out.println("3. 임대 신청 진행중 목록");  // 총관리자만 사용할 수 있도록 수정 예정
        return sc.nextInt();
    }

    public int getWareHouseChoice() {
        System.out.print("원하는 창고번호를 선택하세요: ");
        int warehouse = sc.nextInt();
        System.out.println("\n"+warehouse+"번 창고의 섹터 목록");
        return warehouse;
    }

    public String getSectorChoice() {
        System.out.print("원하는 섹터를 선택하세요: ");
        return sc.next();
    }

    public int getRentPeriod() {
        System.out.println("\n임대 기간 선택");
        System.out.println("1개월 | 3개월 | 6개월 | 12개월");
        System.out.print("원하는 임대기간을 입력하세요: ");
        return sc.nextInt();
    }

    public String getStartDate() {
        System.out.println("원하는 임대 시작일을 입력하세요: 예) 2025-03-21");
        sc.nextLine();
        return sc.nextLine();
    }

    public int LastConfirm(RentHistoryDTO rentHistory, String startDay, String endDate) {
        System.out.println("\n========최종 선택 내역==========");
        System.out.println("창고 이름 : " + rentHistory.getWarehouseId() + "번 창고");
        System.out.println("섹터 이름 : " + rentHistory.getSectorId());
        System.out.println("임대 기간 : " + startDay+" ~ "+ endDate);
        System.out.println("임대 비용 : " + rentHistory.getRentPrice() + "만원");
        System.out.println("임대 신청 하시겠습니까? (1. 예 / 2. 아니오)");
        return sc.nextInt();
    }

    public void rentEnd() {
        System.out.println("임대 신청이 완료되었습니다.");
    }

    public void displayHoldRentHistory() {
        System.out.println("===========대기중인 임대 신청 목록=============");
    }


    public int selectRentHistory() {
        System.out.println("임대 신청을 진행시킬 임대번호를 선택하세요");
        return sc.nextInt();
    }
}

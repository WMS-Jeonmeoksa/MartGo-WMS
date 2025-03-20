package controller;

import model.dao.RentDAO;
import model.dao.RentDAOImpl;
import model.dto.RentHistoryDTO;
import model.service.RentService;
import model.service.RentServiceImpl;

import java.util.Scanner;
import static common.constants.MessageEnum.*;

public class RentControllerImpl implements RentController {

    RentService rentService = new RentServiceImpl();
    RentDAO rentDao = new RentDAOImpl();
    RentHistoryDTO rentHistory = new RentHistoryDTO();
    Scanner sc = new Scanner(System.in);

    public void applyRent(String userId) {
        rentDao.getAllWarehouses();

        // 창고 번호 선택
        System.out.print(INPUT_WAREHOUSE.getMessage());
        int wareHouse = sc.nextInt();
        System.out.printf((SHOW_WAREHOUSE_SECTOR_LIST.getMessage()) + "%n", wareHouse);

        rentDao.getAllSectors(wareHouse);

        // 섹터 선택
        System.out.print(INPUT_SECTOR.getMessage());
        String sectorName = sc.next();

        rentDao.getCostInfo(wareHouse, sectorName);

        // 임대 기간 선택
        System.out.println(INPUT_RENT_PERIOD1.getMessage());
        System.out.println(INPUT_RENT_PERIOD2.getMessage());
        System.out.print(INPUT_RENT_PERIOD3.getMessage());
        int month = sc.nextInt();

        // 임대 비용 정보
        int rentPrice = rentDao.getRentPrice(wareHouse, sectorName, month);
        rentHistory.setSectorId(sectorName);
        rentHistory.setWarehouseId(wareHouse);
        rentHistory.setRentPrice(rentPrice);
        rentHistory.setUserId(userId);

        // 임대 시작일 입력
        System.out.print(INPUT_RENT_START_DATE.getMessage());
        sc.nextLine(); // 엔터 버퍼 처리
        String startDay = sc.nextLine();

        String endDate = rentService.endDate(month, startDay);

        // 최종 선택 내역 출력
        System.out.println(SHOW_RENT_HISTORY_LAST.getMessage());
        System.out.printf((RENT_WAREHOUSE.getMessage()) + "%n", wareHouse);
        System.out.printf((RENT_SECTOR.getMessage()) + "%n", sectorName);
        System.out.printf((RENT_PERIOD.getMessage()) + "%n", startDay, endDate);
        System.out.printf((RENT_PRICE.getMessage()) + "%n", rentPrice);

        // 임대 신청 확인
        System.out.println(CONFIRM_RENT.getMessage());
        int confirm = sc.nextInt();
        if (confirm == 1) {
            rentService.saveRentHistory(rentHistory, month, startDay);
            System.out.println(RENT_END.getMessage());
        } else {
            System.out.println("임대 신청이 취소되었습니다.");
        }
    }

    public void holdRentList(String adminId) {
        System.out.println(SHOW_HOLD_RENT_HISTORY.getMessage());
        rentDao.getHoldRentHistory();

        System.out.print(SELECT_RENT_HISTORY.getMessage());
        int selectRentNum = sc.nextInt();

        rentDao.updateAdminId(selectRentNum, adminId);
        rentDao.updateUserAdminId();
    }

    public void inProgressRentList(String adminId) {
        System.out.println(SHOW_HOLD_RENT_HISTORY.getMessage());
        rentDao.getInProgressRentHistory(adminId);

        System.out.print(SELECT_RENT_HISTORY.getMessage());
        int selectRentNum = sc.nextInt();

        rentDao.completedRentStatus(selectRentNum, adminId);
    }
}

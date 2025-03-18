package controller;

import model.dao.RentDaoImpl;
import model.service.RentService;
import model.service.RentServiceImpl;
import view.RentViewImpl;

public class RentControllerImpl implements RentController {

    RentService rentService = new RentServiceImpl();
    RentViewImpl rentView = new RentViewImpl();
    RentDaoImpl rentDao = new RentDaoImpl();


    public void handleRentRequest() {

        int menu = rentView.displayMenu();
        if (menu == 1) {
            int wareHouse = rentView.getWareHouseChoice();
            String sectorName = rentView.getSectorChoice(wareHouse);
            int month = rentView.getRentPeriod();

            int rentPrice = rentDao.getRentPrice(wareHouse, sectorName, month);
            if (rentPrice != -1) {
                rentView.displaySelection(wareHouse, sectorName, month, rentPrice);
                int select = rentView.confirmSelection();
                if (select == 1) {
                    String startDay = rentView.getStartDate();
                    rentService.saveRentHistory(wareHouse, sectorName, month, rentPrice, startDay);
                    rentView.rentEnd();
                }
            }
        } else if (menu == 2) {
            rentView.displayHoldRentHistory();
            RentDaoImpl.getHoldRentHistory();
            int selectRentNum = rentView.selectRentHistory();
            int adminId = 123;   // 관리자 아이디 받아야됨
            RentDaoImpl.updateAdminId(selectRentNum, adminId);
        } else if (menu == 3) {
            rentView.displayHoldRentHistory();
            RentDaoImpl.getinProgressRentHistory();
            int selectRentNum = rentView.selectRentHistory();
            rentDao.completedRentStatus(selectRentNum);

        }
    }
}

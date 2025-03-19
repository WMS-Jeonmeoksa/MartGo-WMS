package controller;

import model.dao.RentDAOImpl;
import model.dto.RentHistoryDTO;
import model.service.RentService;
import model.service.RentServiceImpl;
import view.RentViewImpl;


public class RentControllerImpl implements RentController {

    RentService rentService = new RentServiceImpl();
    RentViewImpl rentView = new RentViewImpl();
    RentDAOImpl rentDao = new RentDAOImpl();
    RentHistoryDTO rentHistory = new RentHistoryDTO();


    public void ApplyRent(String userId) {
         rentDao.getAllWarehouses();
            int wareHouse = rentView.getWareHouseChoice();
            rentDao.getAllSectors(wareHouse);
            String sectorName = rentView.getSectorChoice();
            rentDao.getCostInfo(wareHouse, sectorName);

            int month = rentView.getRentPeriod();

            int rentPrice = rentDao.getRentPrice(wareHouse, sectorName, month);
            rentHistory.setSectorId(sectorName);
            rentHistory.setWarehouseId(wareHouse);
            rentHistory.setRentPrice(rentPrice);
            rentHistory.setUserId(userId);

            String startDay = rentView.getStartDate();
            String endDate = rentService.endDate(month, startDay);
            rentView.LastConfirm(rentHistory, startDay, endDate);
            rentService.saveRentHistory(rentHistory, month, startDay);
            rentView.rentEnd();


        }

        public void HoldRentList() {
            rentView.displayHoldRentHistory();
            RentDAOImpl.getHoldRentHistory();
            int selectRentNum = rentView.selectRentHistory();
            int adminId = 123;   // 관리자 아이디 받아야됨
            RentDAOImpl.updateAdminId(selectRentNum, adminId);
            rentDao.updateUserAdminId();
        }

     public void inProgressRentList() {
         rentView.displayHoldRentHistory();
         RentDAOImpl.getinProgressRentHistory();
         int selectRentNum = rentView.selectRentHistory();
         rentDao.completedRentStatus(selectRentNum);

     }

}

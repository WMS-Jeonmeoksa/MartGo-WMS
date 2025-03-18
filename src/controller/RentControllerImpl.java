package controller;

import model.dao.RentDao;
import model.dao.RentDaoImpl;
import model.service.RentService;
import model.service.RentServiceImpl;
import view.RentViewImpl;

public class RentControllerImpl implements RentController{

    RentService rentService = new RentServiceImpl();
    RentViewImpl rentView = new RentViewImpl();
    RentDao rentDao = new RentDaoImpl();


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
        }
    }
}

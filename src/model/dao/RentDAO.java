package model.dao;

import model.dto.RentHistoryDTO;

public interface RentDAO {
     void getAllWarehouses();
    void getAllSectors(int warehouseId);
    void getCostInfo(int warehouseId, String sectorId);
    int getRentPrice(int wareHouse, String sectorName, int month);
    void saveDb(RentHistoryDTO rentHistory);
    void getHoldRentHistory();
    void updateUserAdminId();
    void updateAdminId(int rentNum, String adminId);
    void getInProgressRentHistory(String adminId);
    void completedRentStatus(int rentNum, String adminId);
}

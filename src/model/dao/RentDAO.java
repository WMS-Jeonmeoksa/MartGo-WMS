package model.dao;

import model.dto.RentHistoryDTO;

public interface RentDAO {
    int getRentPrice(int wareHouse, String sectorName, int month);
    void saveDb(RentHistoryDTO rentHistory);
}

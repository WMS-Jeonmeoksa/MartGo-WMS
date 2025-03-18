package model.service;

public interface RentService {
    void saveRentHistory(int wareHouse, String sectorName, int month, int rentPrice, String startDay);
}

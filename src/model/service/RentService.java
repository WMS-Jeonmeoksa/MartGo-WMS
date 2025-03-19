package model.service;

import model.dto.RentHistoryDTO;

public interface RentService {
    void saveRentHistory(RentHistoryDTO rentHistory, int month, String startDay);
    String endDate(int month, String startDay);
}

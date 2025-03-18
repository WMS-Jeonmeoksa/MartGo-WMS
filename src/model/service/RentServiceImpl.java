package model.service;

import model.dao.RentDao;
import model.dao.RentDaoImpl;
import model.dto.RentHistoryDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentServiceImpl implements RentService {
    RentDao rentDao = new RentDaoImpl();


    public void saveRentHistory(RentHistoryDTO rentHistory, int month, String startDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDay, formatter);
        LocalDate endDate = startDate.plusMonths(month);

        rentHistory.setSectorId(rentHistory.getSectorId());
        rentHistory.setWarehouseId(rentHistory.getWarehouseId());
        rentHistory.setRentStartDate(Date.valueOf(startDate));
        rentHistory.setRentEndDate(Date.valueOf(endDate));
        rentHistory.setRentPrice(rentHistory.getRentPrice());
        rentHistory.setUserId(1);       // 회원 ID받아와서 넣어야함

        rentDao.saveDb(rentHistory);
    }


}

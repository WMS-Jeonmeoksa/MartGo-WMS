package model.service;

import controller.LoginsignupController;
import model.dao.RentDAOImpl;
import model.dto.RentHistoryDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentServiceImpl implements RentService {
    RentDAOImpl rentDao = new RentDAOImpl();

    public void saveRentHistory(RentHistoryDTO rentHistory, int month, String startDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDay, formatter);
        LocalDate endDate = startDate.plusMonths(month);

        rentHistory.setSectorId(rentHistory.getSectorId());
        rentHistory.setWarehouseId(rentHistory.getWarehouseId());
        rentHistory.setRentStartDate(Date.valueOf(startDate));
        rentHistory.setRentEndDate(Date.valueOf(endDate));
        rentHistory.setRentPrice(rentHistory.getRentPrice());
        LoginsignupController loginsignupController = new LoginsignupController();
        rentHistory.setUserId(loginsignupController.userIdReturn());

        rentDao.saveDb(rentHistory);
    }

    public String endDate(int month, String startDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDay, formatter);
        LocalDate endDate = startDate.plusMonths(month);
        return endDate.format(formatter);
    }
}

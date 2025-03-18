package model.dao;

import common.utils.DbUtil;
import model.dto.RentHistoryDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentDaoImpl implements RentDao {

    public int getRentPrice(int wareHouse, String sectorName, int month) {
        String query = "{CALL GetCostInfo(?, ?, ?)}";
        int rentPrice = -1;

        try (Connection connection = DbUtil.getConnection();
             CallableStatement cstmt = connection.prepareCall(query)) {

            cstmt.setInt(1, wareHouse);
            cstmt.setString(2, sectorName);
            cstmt.setString(3, month + "개월");

            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    rentPrice = rs.getInt("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentPrice;
    }

    public void saveDb(RentHistoryDTO rentHistory) {
        String insertQuery = "{CALL InsertRentHistory(?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DbUtil.getConnection();
             CallableStatement cstmt = connection.prepareCall(insertQuery)) {

            cstmt.setString(1, rentHistory.getSectorId());
            cstmt.setInt(2, rentHistory.getWarehouseId());
            cstmt.setDate(3, new java.sql.Date(rentHistory.getRentStartDate().getTime()));
            cstmt.setDate(4, new java.sql.Date(rentHistory.getRentEndDate().getTime()));
            cstmt.setInt(5, rentHistory.getRentPrice());
            cstmt.setInt(6, rentHistory.getUserId());

            cstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

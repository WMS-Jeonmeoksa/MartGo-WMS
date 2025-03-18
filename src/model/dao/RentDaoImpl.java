package model.dao;

import common.utils.DbUtil;
import model.dto.RentHistoryDTO;

import java.sql.*;

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

    public static void getHoldRentHistory() {
        String sql = "{CALL GetHoldRentHistory()}";

        try (Connection conn = DbUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int rentNum = rs.getInt("rent_num");
                String sectorId = rs.getString("sector_id");
                int warehouseId = rs.getInt("warehouse_id");
                String userId = rs.getString("user_id");
                Date rentStartDate = rs.getDate("rent_start_date");
                Date rentEndDate = rs.getDate("rent_end_date");
                int rentPrice = rs.getInt("rent_price");
                String status = rs.getString("status");

                System.out.println("임대번호: " + rentNum + ", 섹터: " + sectorId +
                        ", 창고: " + warehouseId + ", 회원ID: " + userId +
                        ", 임대 시작일: " + rentStartDate + ", 임대 종료일: " + rentEndDate +
                        ", 임대료: " + rentPrice + ", 상태: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void getinProgressRentHistory() {
        String sql = "{CALL GetinProgressRentHistory()}";

        try (Connection conn = DbUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int rentNum = rs.getInt("rent_num");
                String sectorId = rs.getString("sector_id");
                int warehouseId = rs.getInt("warehouse_id");
                String userId = rs.getString("user_id");
                Date rentStartDate = rs.getDate("rent_start_date");
                Date rentEndDate = rs.getDate("rent_end_date");
                int rentPrice = rs.getInt("rent_price");
                String status = rs.getString("status");
                int adminId = rs.getInt("admin_id");

                System.out.println("임대번호: " + rentNum + ", 섹터: " + sectorId +
                        ", 창고: " + warehouseId + ", 회원ID: " + userId +
                        ", 임대 시작일: " + rentStartDate + ", 임대 종료일: " + rentEndDate +
                        ", 임대료: " + rentPrice + ", 상태: " + status +", 창고관리자ID: " + adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateAdminId(int rentNum, int adminId) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = DbUtil.getConnection();

            String sql = "{CALL UpdateAdminId(?, ?)}";
            stmt = conn.prepareCall(sql);

            stmt.setInt(1, rentNum);
            stmt.setInt(2, adminId);

            stmt.executeUpdate();

            System.out.println("Admin ID 업데이트 완료.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void completedRentStatus(int rentNum) {
        String sql = "{CALL CompletedRentStatus(?)}"; // 프로시저 호출

        try (CallableStatement stmt = DbUtil.getConnection().prepareCall(sql)) {
            stmt.setInt(1, rentNum);
            stmt.executeUpdate();
            System.out.println("임대 번호 " + rentNum + "의 상태가 '승인완료'로 변경되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException("임대 상태 업데이트 중 오류 발생: " + e.getMessage(), e);
        }
    }

}

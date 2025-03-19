package model.dao;

import common.utils.DbUtil;
import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockDAOImpl implements StockDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;

    // 회원 아이디에 따른 재고 내역 확인 메서드
    @Override
    public List<StockDTO> checkUserStock(String user_id) {
        List<StockDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM stock WHERE user_id = ?");
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockDTO stock = StockDTO.builder()
                        .stock_num(rs.getInt("stock_num"))
                        .count(rs.getInt("count"))
                        .total_price(rs.getInt("total_price"))
                        .user_id(rs.getString("user_id"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .warehouse_id(rs.getInt("warehouse_id"))
                        .build();
                checkStockList.add(stock);
            }
            return checkStockList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkStockList;
    }


    @Override
    public List<StockDTO> checkAdminUserStock(String admin_id) {
        List<StockDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT stock_num,count, total_price, stock.user_id, product_id, sector_id, stock.warehouse_id FROM stock\n" +
                    "    JOIN user ON user.user_id = stock.user_id\n" +
                    "    JOIN admin ON user.admin_id = admin.admin_id\n" +
                    "WHERE stock.user_id = user.user_id AND user.admin_id = ?");
            pstmt.setString(1, admin_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockDTO stock = StockDTO.builder()
                        .stock_num(rs.getInt("stock_num"))
                        .count(rs.getInt("count"))
                        .total_price(rs.getInt("total_price"))
                        .user_id(rs.getString("user_id"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .warehouse_id(rs.getInt("warehouse_id"))
                        .build();
                checkStockList.add(stock);
            }
            return checkStockList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkStockList;
    }

    @Override
    public List<StockDTO> checkGeneralStock(String admin_id) {
        List<StockDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT stock_num,count, total_price, stock.user_id, product_id, sector_id, stock.warehouse_id\n" +
                    "FROM stock\n" +
                    "JOIN warehouse ON stock.warehouse_id = warehouse.warehouse_id\n" +
                    "JOIN admin ON admin.admin_id = ?\n" +
                    "WHERE warehouse.warehouse_id = admin.warehouse_id");
            pstmt.setString(1, admin_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockDTO stock = StockDTO.builder()
                        .stock_num(rs.getInt("stock_num"))
                        .count(rs.getInt("count"))
                        .total_price(rs.getInt("total_price"))
                        .user_id(rs.getString("user_id"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .warehouse_id(rs.getInt("warehouse_id"))
                        .build();
                checkStockList.add(stock);
            }
            return checkStockList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkStockList;
    }

    @Override
    public List<StockHistoryDTO> checkGeneralStockHistory(String admin_id) {
        List<StockHistoryDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT history_num, product_id, stock_history.sector_id, count, change_date, change_type, stock_history.admin_id, incoming_num, outgoing_num, stock_num\n" +
                    "FROM stock_history\n" +
                    "         JOIN sector ON stock_history.sector_id = sector.sector_id\n" +
                    "         JOIN warehouse ON warehouse.warehouse_id = sector.warehouse_id\n" +
                    "         JOIN admin ON admin.admin_id = ?\n" +
                    "WHERE warehouse.warehouse_id = admin.warehouse_id");
            pstmt.setString(1, admin_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockHistoryDTO stockHistory = StockHistoryDTO.builder()
                        .history_num(rs.getInt("history_num"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .count(rs.getInt("count"))
                        .change_date(rs.getDate("change_date"))
                        .change_type(rs.getString("change_type"))
                        .admin_id(rs.getString("admin_id"))
                        .incoming_num(rs.getInt("incoming_num"))
                        .outgoing_num(rs.getInt("outgoing_num"))
                        .stock_num(rs.getInt("stock_num"))
                        .build();
                checkStockList.add(stockHistory);
            }
            return checkStockList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkStockList;
    }

    @Override
    public List<StockHistoryDTO> checkAdminStockHistory(String admin_id) {
        List<StockHistoryDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT history_num, product_id, stock_history.sector_id, count, change_date, change_type, stock_history.admin_id, incoming_num, outgoing_num, stock_num\n" +
                    "FROM stock_history\n" +
                    "JOIN admin ON admin.admin_id = ?");
            pstmt.setString(1, admin_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockHistoryDTO stockHistory = StockHistoryDTO.builder()
                        .history_num(rs.getInt("history_num"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .count(rs.getInt("count"))
                        .change_date(rs.getDate("change_date"))
                        .change_type(rs.getString("change_type"))
                        .admin_id(rs.getString("admin_id"))
                        .incoming_num(rs.getInt("incoming_num"))
                        .outgoing_num(rs.getInt("outgoing_num"))
                        .stock_num(rs.getInt("stock_num"))
                        .build();
                checkStockList.add(stockHistory);
            }
            return checkStockList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checkStockList;
    }


}

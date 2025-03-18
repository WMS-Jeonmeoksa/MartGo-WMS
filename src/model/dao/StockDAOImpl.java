package model.dao;

import common.utils.DbUtil;
import model.dto.StockDTO;

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
    public List<StockDTO> checkAllStock(String admin_id) {
        List<StockDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM stock");
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
}

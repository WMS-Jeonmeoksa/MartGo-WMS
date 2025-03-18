package model.dao;

import common.constants.ErrorCode;
import common.utils.DbUtil;
import model.dto.OutgoingDTO;
import model.dto.StockDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutgoingDAOImpl implements OutgoingDAO {

    @Override
    public List<StockDTO> getStockByUserId(String userId) {
        List<StockDTO> stockDTOList = new ArrayList<>();

        String sql = "SELECT * FROM stock WHERE userid = ?";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StockDTO stockDTO = StockDTO.builder()
                        .stock_num(rs.getInt("stock_num"))
                        .count(rs.getInt("count"))
                        .total_price(rs.getInt("total_price"))
                        .user_id(rs.getString("user_id"))
                        .product_id(rs.getString("product_id"))
                        .sector_id(rs.getString("sector_id"))
                        .warehouse_id(rs.getInt("warehouse_id")).build();
                stockDTOList.add(stockDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
        return stockDTOList;
    }

    @Override
    public void insertOutgoing(OutgoingDTO outgoingDTO) {

        String sql = "INSERT INTO outgoing (count, outgoing_date, status, user_id, stock_num) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, outgoingDTO.getCount());
            ps.setDate(2, (Date) outgoingDTO.getOutgoingDate());
            ps.setString(3, outgoingDTO.getStatus());
            ps.setString(4, outgoingDTO.getUserId());
            ps.setInt(5, outgoingDTO.getStockNum());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    public List<OutgoingDTO> getOutgoingByStatus(String status) {
        List<OutgoingDTO> outgoingDTOList = new ArrayList<>();

        String sql = "SELECT * FROM outgoing WHERE status = ?";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OutgoingDTO outgoingDTO = OutgoingDTO.builder()
                        .outgoingNum(rs.getInt("outgoing_num"))
                        .count(rs.getInt("count"))
                        .outgoingDate(rs.getDate("outgoing_date"))
                        .status(rs.getString("status"))
                        .userId(rs.getString("user_id"))
                        .stockNum(rs.getInt("stock_num")).build();
                outgoingDTOList.add(outgoingDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return outgoingDTOList;
    }

    @Override
    public void updateOutgoingStatus(int outgoingNum, String status) {

        String sql = "UPDATE outgoing SET status = ? WHERE outgoing_num = ?";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, outgoingNum);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
    }
}


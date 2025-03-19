package model.dao;

import common.constants.ErrorCode;
import common.utils.DbUtil;
import model.dto.IncomingDTO;
import model.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomingDAOImpl implements IncomingDAO {

    @Override
    public void insertIncoming(IncomingDTO incomingDTO) {

        String sql = "INSERT INTO incoming (count, incoming_date, status, product_id, user_id) VALUES (?,?,?,?,?)";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, incomingDTO.getCount());
            ps.setDate(2, new java.sql.Date(incomingDTO.getIncomingDate().getTime()));
            ps.setString(3, "대기");
            ps.setString(4, incomingDTO.getProductId());
            ps.setString(5, incomingDTO.getUserId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    public String getAdminRoleById(String adminId) {

        String sql = "SELECT role FROM admin WHERE admin_id = ?";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, adminId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<IncomingDTO> getIncomingByStatus(String adminId, String status) {
        List<IncomingDTO> incomingDTOList = new ArrayList<>();

        String sql = new StringBuilder()
                .append("SELECT i.* ")
                .append("FROM incoming i ")
                .append("JOIN user u ON(i.user_id = u.user_id) ")
                .append("WHERE u.admin_id = ? AND i.status = ? ").toString();

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, adminId);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IncomingDTO incomingDTO = IncomingDTO.builder()
                        .incomingNum(rs.getInt("incoming_num"))
                        .count(rs.getInt("count"))
                        .incomingDate(rs.getDate("incoming_date"))
                        .status(rs.getString("status"))
                        .productId(rs.getString("product_id"))
                        .userId(rs.getString("user_id")).build();
                incomingDTOList.add(incomingDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
        return incomingDTOList;
    }

    @Override
    public List<IncomingDTO> getIncomingByStatusNext(String adminId, String status) {
        List<IncomingDTO> incomingDTOList = new ArrayList<>();

        String sql = new StringBuilder()
                .append("SELECT i.* ")
                .append("FROM incoming i ")
                .append("JOIN user u ON(i.user_id = u.user_id) ")
                .append("JOIN admin a ON (u.admin_id = a.admin_id) ")
                .append("WHERE u.admin_id IN ")
                .append("(SELECT admin_id FROM admin WHERE warehouse_id = ")
                .append("(SELECT warehouse_id FROM admin WHERE admin_id = ?) ")
                .append("AND role = '창고관리자') ")
                .append("AND status = ?").toString();

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, adminId);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IncomingDTO incomingDTO = IncomingDTO.builder()
                        .incomingNum(rs.getInt("incoming_num"))
                        .count(rs.getInt("count"))
                        .incomingDate(rs.getDate("incoming_date"))
                        .status(rs.getString("status"))
                        .productId(rs.getString("product_id"))
                        .userId(rs.getString("user_id")).build();
                incomingDTOList.add(incomingDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
        return incomingDTOList;
    }

    @Override
    public void updateIncomingStatus(int incomingNum, String status) {

        String sql = "UPDATE incoming SET status = ? WHERE incoming_num = ?";

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, incomingNum);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    public String getAdminIdByIncomingNum(int incomingNum) {

        String sql = new StringBuilder()
                .append("SELECT u.admin_id ")
                .append("FROM incoming i ")
                .append("JOIN user u ON(i.user_id = u.user_id) ")
                .append("WHERE i.incoming_num = ? ").toString();

        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, incomingNum);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("admin_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
        return null;
    }
}

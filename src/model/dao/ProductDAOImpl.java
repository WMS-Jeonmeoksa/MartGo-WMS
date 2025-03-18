package model.dao;

import common.constants.ErrorCode;
import common.utils.DbUtil;
import model.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void insertProduct(ProductDTO productDTO) {

        String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?)";

        try {
            Connection con = DbUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productDTO.getProductId());
            ps.setString(2, productDTO.getProductName());
            ps.setString(3, productDTO.getCategory());
            ps.setInt(4, productDTO.getHeight());
            ps.setInt(5, productDTO.getWidth());
            ps.setInt(6, productDTO.getPrice());
            ps.setString(7, productDTO.getManufacturer());
            ps.setString(8, productDTO.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    public boolean isUserAuthorized(String userId) {
        String sql = "SELECT role FROM user WHERE user_id = ?";

        try {
            Connection con = DbUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("role").equals("거래처");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.DATABASE_ERROR.getMessage());
        }
        return false;
    }
}

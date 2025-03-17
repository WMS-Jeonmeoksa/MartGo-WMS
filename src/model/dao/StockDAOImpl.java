package model.dao;

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


    // 입고 "완료" 시 재고와 재고 이력 업데이트
    @Override
    public boolean incomingUpdateStock(int incoming_num) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");

            pstmt = conn.prepareStatement("CALL incomingStock(?)");
            pstmt.setInt(1, incoming_num);

            pstmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean incomingUpdateStockHistory(int incoming_num) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");

            pstmt = conn.prepareStatement("CALL incomingStockHistory(?)");
            pstmt.setInt(1, incoming_num);

            pstmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 출고에 따른 재고 변경 메서드
    @Override
    public boolean outgoingUpdateStock(int outgoing_num) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");

            pstmt = conn.prepareStatement("CALL outgoingStock(?)");
            pstmt.setInt(1, outgoing_num);

            pstmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 출고에 따른 재고 이력 변경 메서드
    @Override
    public boolean outgoingUpdateStockHistory(int outgoing_num) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");

            pstmt = conn.prepareStatement("CALL outgoingStockHistory(?)");
            pstmt.setInt(1, outgoing_num);

            pstmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 회원 아이디에 따른 재고 내역 확인 메서드
    @Override
    public <T> Optional<List<StockDTO>> checkStock(Integer user_id) {
        List<StockDTO> checkStockList = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "root");
            pstmt = conn.prepareStatement("SELECT * FROM stock WHERE user_id = ?");
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockDTO stock = StockDTO.builder()
                        .stock_num(rs.getInt("stock_num"))
                        .count(rs.getInt("count"))
                        .total_price(rs.getInt("total_price"))
                        .admin_id(rs.getInt("admin_id"))
                        .product_id(rs.getString("product_id"))
                        .incoming_num(rs.getInt("Incoming_num"))
                        .sector_id(rs.getString("sector_id"))
                        .warehouse_id(rs.getInt("warehouse_id"))
                        .build();
                checkStockList.add(stock);
            }

            return Optional.of(checkStockList);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


/*
DELIMITER $$

CREATE PROCEDURE incomingStock(IN in_incoming_num INT)
BEGIN
    DECLARE v_product_id INT;
    DECLARE v_count INT;
    DECLARE v_user_id INT;
    DECLARE v_admin_id INT;
    DECLARE v_unit_price INT;
    DECLARE v_total_price INT;
    DECLARE v_stock_num INT;
    DECLARE v_sector_id CHAR(3);
    DECLARE v_warehouse_id INT;
    DECLARE stock_not_found INT DEFAULT 0;

    -- 기존에 존재하는 Stock 레코드를 찾고 없으면 1로 설정함
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET stock_not_found = 1;

    -- 입고 테이블에서 완료 처리된 입고 번호를 통해 찾아옴
    SELECT product_id, count, user_id
      INTO v_product_id, v_count, v_user_id
      FROM Incoming
     WHERE Incoming_num = in_incoming_num;

    -- 해당 제품 가격을 들고옴
    SELECT price
      INTO v_unit_price
      FROM Product
     WHERE product_id = v_product_id;

    SET v_total_price = v_unit_price * v_count;

    -- 어드민 id 를 들고 옴
     SELECT admin_id
      INTO v_admin_id
      FROM User
     WHERE user_id = v_user_id;

    -- 섹터 아이디와 창고 아이디를 들고옴
    SELECT sector_id, warehouse_id
      INTO v_sector_id, v_warehouse_id
      FROM Rent_History
     WHERE user_id = v_user_id
       AND status = '완료'
     LIMIT 1;

    -- 기존에 있는 재고인지 확인 함
    SET stock_not_found = 0;
    SELECT stock_num
      INTO v_stock_num
      FROM Stock
     WHERE user_id = v_user_id
       AND product_id = v_product_id
     LIMIT 1;

    -- 있으면 수량만 업데이트 하고 없으면 새로 추가함
    IF stock_not_found = 1 THEN
        -- 새 재고 생성
        INSERT INTO Stock
            (count, total_price, user_id, product_id, incoming_num,
             sector_id, warehouse_id)
        VALUES
            (v_count,
             v_total_price,
             v_user_id,
             v_product_id,
             in_incoming_num,
             v_sector_id,
             v_warehouse_id,
             );

        SET v_stock_num = LAST_INSERT_ID();
    ELSE
        -- 기존 재고에 수량/총가격 누적 + 섹터/창고/회원 추가
        UPDATE Stock
           SET count = count + v_count,
               total_price = total_price + v_total_price,
               user_id = v_user_id,
               sector_id = v_sector_id,
               warehouse_id = v_warehouse_id
         WHERE stock_num = v_stock_num;
    END IF;

END$$

DELIMITER ;
 */

/*
DELIMITER $$
CREATE PROCEDURE incomingStockHistory(IN in_incoming_num INT)
BEGIN
    DECLARE v_product_id
    DECLARE v_sector_id
    DECLARE v_count
    DECLARE v_admin_id
    DECLARE v_stock_num

    -- 제품 아이디, 수량, 유저 아이디 불러오기
    SELECT product_id, count, user_id
      INTO v_product_id, v_count, v_user_id
      FROM Incoming
     WHERE Incoming_num = in_incoming_num;

    -- 유저아이디를 통해 어드민 아이디 불러오기
     SELECT admin_id
      INTO v_admin_id
      FROM User
     WHERE user_id = v_user_id;

    -- 유저아이디와 제품 아이디를 통해 입고번호를 들고오기
     SELECT stock_num
      INTO v_stock_num
      FROM Stock
     WHERE user_id = v_user_id
       AND product_id = v_product_id
     LIMIT 1;

    INSERT INTO Stock_History
        (product_id, sector_id, count, change_date, change_type,
         admin_id, stock_num,)
    VALUES
        (v_product_id,
         v_sector_id,
         v_count,
         SYSDATE(),
         '입고',
         v_admin_id,
         v_stock_num,
        );
 */

/*
DELIMITER $$

CREATE PROCEDURE outgoingStock(IN in_outgoing_num INT)
BEGIN
    DECLARE v_product_id INT;
    DECLARE v_count INT;
    DECLARE v_user_id INT;
    DECLARE v_admin_id INT;
    DECLARE v_unit_price INT;
    DECLARE v_total_price INT;
    DECLARE v_stock_num INT;
    DECLARE v_sector_id CHAR(3);
    DECLARE v_warehouse_id INT;
    DECLARE stock_not_found INT DEFAULT 0;

    -- 기존에 존재하는 Stock 레코드를 찾고 없으면 1로 설정함
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET stock_not_found = 1;

    -- 입고 테이블에서 완료 처리된 입고 번호를 통해 찾아옴
    SELECT product_id, count, user_id
      INTO v_product_id, v_count, v_user_id
      FROM Incoming
     WHERE Incoming_num = in_incoming_num;

    -- 해당 제품 가격을 들고옴
    SELECT price
      INTO v_unit_price
      FROM Product
     WHERE product_id = v_product_id;

    SET v_total_price = v_unit_price * v_count;

    -- 어드민 id 를 들고 옴
     SELECT admin_id
      INTO v_admin_id
      FROM User
     WHERE user_id = v_user_id;

    -- 섹터 아이디와 창고 아이디를 들고옴
    SELECT sector_id, warehouse_id
      INTO v_sector_id, v_warehouse_id
      FROM Rent_History
     WHERE user_id = v_user_id
       AND status = '완료'
     LIMIT 1;


    -- 있으면 수량만 업데이트 하고 없으면 새로 추가함

        -- 기존 재고에 수량/총가격 누적 + 섹터/창고/회원 추가
        UPDATE Stock
           SET count = count + v_count,
               total_price = total_price + v_total_price,
               user_id = v_user_id,
               sector_id = v_sector_id,
               warehouse_id = v_warehouse_id
         WHERE stock_num = v_stock_num;
    END IF;

END$$

DELIMITER ;
 */
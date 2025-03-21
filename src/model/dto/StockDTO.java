package model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class StockDTO {
    Integer stock_num;
    Integer count;
    Integer total_price;
    String user_id;
    String product_id;
    String sector_id;
    Integer warehouse_id;

    public static StockDTOBuilder builder() {
        return new StockDTOBuilder();
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-10s | %-6d | %-10d | %-10s | %-9s | %-10d |",
                stock_num, product_id, count, total_price, user_id, sector_id, warehouse_id);
    }

    public static class StockDTOBuilder {
        Integer stock_num;
        Integer count;
        Integer total_price;
        String user_id;
        String product_id;
        String sector_id;
        Integer warehouse_id;


        public StockDTOBuilder stock_num(Integer stock_num) {
            this.stock_num = stock_num;
            return this;
        }
        public StockDTOBuilder count(Integer count) {
            this.count = count;
            return this;
        }
        public StockDTOBuilder total_price(Integer total_price) {
            this.total_price = total_price;
            return this;
        }
        public StockDTOBuilder user_id(String user_id) {
            this.user_id = user_id;
            return this;
        }
        public StockDTOBuilder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }
        public StockDTOBuilder sector_id(String sector_id) {
            this.sector_id = sector_id;
            return this;
        }
        public StockDTOBuilder warehouse_id(Integer warehouse_id) {
            this.warehouse_id = warehouse_id;
            return this;
        }

        public StockDTO build(){
            StockDTO stockDTO = new StockDTO();
            stockDTO.stock_num = stock_num;
            stockDTO.count = count;
            stockDTO.total_price = total_price;
            stockDTO.user_id = user_id;
            stockDTO.product_id = product_id;
            stockDTO.sector_id = sector_id;
            stockDTO.warehouse_id = warehouse_id;
            return stockDTO;
        }

    }
}

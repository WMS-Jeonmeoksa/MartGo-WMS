package model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class StockDTO {
    Integer stock_num;
    Integer count;
    Integer total_price;
    Integer admin_id;
    String product_id;
    Integer incoming_num;
    String sector_id;
    Integer warehouse_id;

    public static StockDTOBuilder builder() {
        return new StockDTOBuilder();
    }


    public static class StockDTOBuilder {
        Integer stock_num;
        Integer count;
        Integer total_price;
        Integer admin_id;
        String product_id;
        Integer incoming_num;
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
        public StockDTOBuilder admin_id(Integer admin_id) {
            this.admin_id = admin_id;
            return this;
        }
        public StockDTOBuilder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }
        public StockDTOBuilder incoming_num(Integer incoming_num) {
            this.incoming_num = incoming_num;
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
            stockDTO.admin_id = admin_id;
            stockDTO.product_id = product_id;
            stockDTO.incoming_num = incoming_num;
            stockDTO.sector_id = sector_id;
            stockDTO.warehouse_id = warehouse_id;
            return stockDTO;
        }
    }
}

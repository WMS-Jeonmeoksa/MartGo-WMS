package model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockHistoryDTO {
    Integer history_num;
    String product_id;
    String sector_id;
    Integer count;
    Date change_date;
    String change_type;
    String admin_id;
    Integer incoming_num;
    Integer outgoing_num;
    Integer stock_num;

    public static StockHistoryDTOBuilder builder() {
        return new StockHistoryDTOBuilder();
    }

    public static class StockHistoryDTOBuilder {
        Integer history_num;
        String product_id;
        String sector_id;
        Integer count;
        Date change_date;
        String change_type;
        String admin_id;
        Integer incoming_num;
        Integer outgoing_num;
        Integer stock_num;

        public StockHistoryDTO.StockHistoryDTOBuilder stock_num(Integer stock_num) {
            this.stock_num = stock_num;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder count(Integer count) {
            this.count = count;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder history_num(Integer history_num) {
            this.history_num = history_num;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder admin_id(String admin_id) {
            this.admin_id = admin_id;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder sector_id(String sector_id) {
            this.sector_id = sector_id;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder incoming_num(Integer incoming_num) {
            this.incoming_num = incoming_num;
            return this;
        }
        public StockHistoryDTO.StockHistoryDTOBuilder outgoing_num(Integer outgoing_num) {
            this.outgoing_num = outgoing_num;
            return this;
        }

        public StockHistoryDTO.StockHistoryDTOBuilder change_date(Date change_date) {
            this.change_date = change_date;
            return this;
        }
        public StockHistoryDTO.StockHistoryDTOBuilder change_type(String change_type) {
            this.change_type = change_type;
            return this;
        }

        public StockHistoryDTO build() {
            StockHistoryDTO stockHistoryDTO = new StockHistoryDTO();

            stockHistoryDTO.count = count;
            stockHistoryDTO.history_num = history_num;
            stockHistoryDTO.change_date = change_date;
            stockHistoryDTO.change_type = change_type;
            stockHistoryDTO.admin_id = admin_id;
            stockHistoryDTO.product_id = product_id;
            stockHistoryDTO.sector_id = sector_id;
            stockHistoryDTO.incoming_num = incoming_num;
            stockHistoryDTO.outgoing_num = outgoing_num;
            stockHistoryDTO.stock_num = stock_num;
            return stockHistoryDTO;
        }
    }
}

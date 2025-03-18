package model.service;

import model.dto.StockDTO;

import java.util.List;

public interface StockService {
    boolean checkIncomingStock(int incoming_num);
    boolean checkIncomingStockHistory(int incoming_num);
    List<StockDTO> getUserStock(int user_id);
}

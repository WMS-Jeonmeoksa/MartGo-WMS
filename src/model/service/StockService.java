package model.service;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getUserStock(String user_id);
    List<StockDTO> getAllStock(String admin_id);
    List<StockHistoryDTO> getStockHistoryList();
}

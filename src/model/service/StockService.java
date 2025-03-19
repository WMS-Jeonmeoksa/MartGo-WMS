package model.service;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getUserStock(String user_id);
    List<StockDTO> getAdminUserStock(String admin_id);
    List<StockDTO> getGeneralStock(String admin_id);
    List<StockHistoryDTO> getAdminStockHistory(String admin_id);
    List<StockHistoryDTO> getGeneralStockHistory(String admin_id);
}

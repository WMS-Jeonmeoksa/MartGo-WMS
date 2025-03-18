package model.dao;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.util.List;
import java.util.Optional;

public interface StockDAO {
    List<StockDTO> checkUserStock(String user_id);
    List<StockDTO> checkAllStock(String admin_id);
    List<StockHistoryDTO> checkStockHistoryList();
}

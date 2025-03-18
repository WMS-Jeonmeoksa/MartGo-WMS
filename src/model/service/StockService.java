package model.service;

import model.dto.StockDTO;

import java.util.List;

public interface StockService {
    boolean isIncomingStockUpdated(int incoming_num);
    boolean isIncomingStockHistoryUpdated(int incoming_num);
    boolean isOutgoingStockUpdated(int outgoing_num);
    boolean isOutgoingStockHistoryUpdated(int outgoing_num);
    List<StockDTO> getUserStock(int user_id);
}

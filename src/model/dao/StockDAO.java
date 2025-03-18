package model.dao;

import model.dto.StockDTO;

import java.util.List;
import java.util.Optional;

public interface StockDAO {
    <T> Optional<List<StockDTO>> checkStock(Integer user_id);
    boolean incomingUpdateStock(int incoming_num);
    boolean incomingUpdateStockHistory(int incoming_num);
    boolean outgoingUpdateStock(int outgoing_num);
    boolean outgoingUpdateStockHistory(int outgoing_num);
}

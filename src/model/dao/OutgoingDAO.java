package model.dao;

import model.dto.OutgoingDTO;
import model.dto.StockDTO;

import java.util.List;

public interface OutgoingDAO {
    List<StockDTO> getStockByUserId(String userId);
    void insertOutgoing(OutgoingDTO outgoingDTO);
    List<OutgoingDTO> getOutgoingByStatus(String status);
    void updateOutgoingStatus(int outgoingNum, String status);
}

package model.dao;

import model.dto.OutgoingDTO;
import model.dto.StockDTO;

import java.util.List;

public interface OutgoingDAO {
    List<StockDTO> getStockByUserId(String userId);
    void insertOutgoing(OutgoingDTO outgoingDTO);
    List<OutgoingDTO> getOutgoingByStatus(String adminId, String status);
    List<OutgoingDTO> getOutgoingByStatusNext(String adminId, String status);
    void updateOutgoingStatus(int outgoingNum, String status);
    String getAdminIdByOutgoingNum(int outgoingNum);
    String getAdminIdByOutgoingNumNext(int outgoingNum);
}

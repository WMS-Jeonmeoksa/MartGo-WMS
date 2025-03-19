package model.service;

import model.dto.OutgoingDTO;
import model.dto.StockDTO;

import java.util.List;

public interface OutgoingService {
    List<StockDTO> showStockByUserId(String userId);
    void requestOutgoing(OutgoingDTO outgoingDTO);
    List<OutgoingDTO> getOutgoingByRole(String role);
    void approveOutgoing(int outgoingNum, String role);
}

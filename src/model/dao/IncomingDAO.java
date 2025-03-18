package model.dao;

import model.dto.IncomingDTO;

import java.util.List;

public interface IncomingDAO {
    void insertIncoming(IncomingDTO incomingDTO);
    List<IncomingDTO> getIncomingByStatus(String status);
    void updateIncomingStatus(int incomingNum, String status);
}

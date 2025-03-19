package model.dao;

import model.dto.IncomingDTO;

import java.util.List;

public interface IncomingDAO {
    void insertIncoming(IncomingDTO incomingDTO);
    List<IncomingDTO> getIncomingByStatus(String adminId, String status);
    void updateIncomingStatus(int incomingNum, String status);
    String getAdminRoleById(String adminId);
    String getAdminIdByIncomingNum(int incomingNum);
}

package model.dao;

import model.dto.IncomingDTO;
import model.dto.ProductDTO;

import java.util.List;

public interface IncomingDAO {
    void insertIncoming(IncomingDTO incomingDTO);
    List<IncomingDTO> getIncomingByStatus(String adminId, String status);
    List<IncomingDTO> getIncomingByStatusNext(String adminId, String status);
    void updateIncomingStatus(int incomingNum, String status);
    String getAdminRoleById(String adminId);
    String getAdminIdByIncomingNum(int incomingNum);
    String getAdminIdByIncomingNumNext(int incomingNum);
    String getUserIdByIncomingNum(int incomingNum);
}

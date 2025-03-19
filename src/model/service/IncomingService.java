package model.service;

import model.dto.IncomingDTO;

import java.util.List;

public interface IncomingService {
    void requestIncoming(IncomingDTO incomingDTO);
    List<IncomingDTO> getIncomingByRole(String adminId, String role);
    void approveIncoming(String adminId, int incomingNum, String role);
    String getAdminRoleById(String adminId);
}

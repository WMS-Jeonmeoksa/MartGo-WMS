package model.service;

import model.dto.IncomingDTO;

import java.util.List;

public interface IncomingService {
    void requestIncoming(IncomingDTO incomingDTO);
    List<IncomingDTO> getIncomingByRole(String role);
    void approveIncoming(int incomingNum, String role);
}

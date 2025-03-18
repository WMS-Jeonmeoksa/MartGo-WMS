package model.service;

import common.constants.MessageEnum;
import model.dao.IncomingDAO;
import model.dao.IncomingDAOImpl;
import model.dto.IncomingDTO;

import java.util.List;

public class IncomingServiceImpl implements IncomingService{
    IncomingDAO incomingDAO = new IncomingDAOImpl();

    @Override
    public void requestIncoming(IncomingDTO incomingDTO) {
        incomingDAO.insertIncoming(incomingDTO);
        System.out.println(MessageEnum.INCOMING_REQUEST_SUCCESS.getMessage());
    }

    @Override
    public List<IncomingDTO> getIncomingByRole(String role) {
        if (role.equals("창고관리자")) {
            return incomingDAO.getIncomingByStatus("대기");
        } else if (role.equals("총관리자")) {
            return incomingDAO.getIncomingByStatus("진행중");
        }
        return null;
    }

    @Override
    public void approveIncoming(int incomingNum, String role) {
        String newStatus = null;
        if (role.equals("창고관리자")) newStatus = "진행중";
        else if (role.equals("총관리자")) newStatus = "완료";
        incomingDAO.updateIncomingStatus(incomingNum, newStatus);
        System.out.println(MessageEnum.INCOMING_APPROVE_SUCCESS.getMessage());
    }
}

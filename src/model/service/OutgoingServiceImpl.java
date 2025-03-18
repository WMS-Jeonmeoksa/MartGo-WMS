package model.service;

import common.constants.MessageEnum;
import model.dao.OutgoingDAO;
import model.dao.OutgoingDAOImpl;
import model.dto.OutgoingDTO;
import model.dto.StockDTO;

import java.util.List;

public class OutgoingServiceImpl implements OutgoingService {
    OutgoingDAO outgoingDAO = new OutgoingDAOImpl();

    @Override
    public List<StockDTO> showStockByUserId(String userId) {
        return outgoingDAO.getStockByUserId(userId);
    }

    @Override
    public void requestOutgoing(OutgoingDTO outgoingDTO) {
        outgoingDAO.insertOutgoing(outgoingDTO);
        System.out.println(MessageEnum.OUTGOING_REQUEST_SUCCESS.getMessage());
    }

    @Override
    public List<OutgoingDTO> getOutgoingByRole(String role) {
        if (role.equals("창고관리자")) {
            return outgoingDAO.getOutgoingByStatus("대기");
        } else if (role.equals("총관리자")) {
            return outgoingDAO.getOutgoingByStatus("진행중");
        }
        return null;
    }

    @Override
    public void approveOutgoing(int outgoingNum, String role) {
        String newStatus = null;
        if (role.equals("창고관리자")) newStatus = "진행중";
        else if (role.equals("총관리자")) newStatus = "완료";
        outgoingDAO.updateOutgoingStatus(outgoingNum, newStatus);
        System.out.println(MessageEnum.OUTGOING_APPROVE_SUCCESS.getMessage());
    }
}

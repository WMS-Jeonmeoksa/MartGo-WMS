package model.service;

import common.constants.ErrorCode;
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
    public List<OutgoingDTO> getOutgoingByRole(String adminId, String role) {
        if (role.equals("창고관리자")) {
            return outgoingDAO.getOutgoingByStatus(adminId, "대기");
        } else if (role.equals("총관리자")) {
            return outgoingDAO.getOutgoingByStatusNext(adminId, "진행중");
        }
        return null;
    }

    @Override
    public void approveOutgoing(String adminId, int outgoingNum, String role) {
        String checkAdminId = null;
        if (role.equals("창고관리자")) {
            checkAdminId = outgoingDAO.getAdminIdByOutgoingNum(outgoingNum);
        } else if (role.equals("총관리자")) {
            checkAdminId = outgoingDAO.getAdminIdByOutgoingNumNext(outgoingNum);
        }
        if (checkAdminId != null && checkAdminId.equals(adminId)) {
            String newStatus = null;
            if (role.equals("창고관리자")) newStatus = "진행중";
            else if (role.equals("총관리자")) newStatus = "완료";
            outgoingDAO.updateOutgoingStatus(outgoingNum, newStatus);
            System.out.println(MessageEnum.INCOMING_APPROVE_SUCCESS.getMessage());
        } else {
            System.out.println(ErrorCode.NO_INCOMINGNUM_APPROVE.getMessage());
        }
    }
}

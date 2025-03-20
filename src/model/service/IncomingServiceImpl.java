package model.service;

import common.constants.ErrorCode;
import common.constants.MessageEnum;
import model.dao.*;
import model.dto.IncomingDTO;
import model.dto.ProductDTO;

import java.util.List;

public class IncomingServiceImpl implements IncomingService{
    IncomingDAO incomingDAO = new IncomingDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<ProductDTO> getProductByUserId(String userId) {
        return productDAO.getProductByUserId(userId);
    }

    @Override
    public void requestIncoming(IncomingDTO incomingDTO) {
        incomingDAO.insertIncoming(incomingDTO);
        System.out.println(MessageEnum.INCOMING_REQUEST_SUCCESS.getMessage());
    }

    @Override
    public String getAdminRoleById(String adminId) {
        return incomingDAO.getAdminRoleById(adminId);
    }

    @Override
    public List<IncomingDTO> getIncomingByRole(String adminId, String role) {
        if (role.equals("창고관리자")) {
            return incomingDAO.getIncomingByStatus(adminId,"대기");
        } else if (role.equals("총관리자")) {
            return incomingDAO.getIncomingByStatusNext(adminId,"진행중");
        }
        return null;
    }

    @Override
    public void approveIncoming(String adminId, int incomingNum, String role) {
//        if (adminId.equals(incomingDAO.getAdminIdByIncomingNum(incomingNum))) {
//            String newStatus = null;
//            if (role.equals("창고관리자")) newStatus = "진행중";
//            else if (role.equals("총관리자")) newStatus = "완료";
//            incomingDAO.updateIncomingStatus(incomingNum, newStatus);
//            System.out.println(MessageEnum.INCOMING_APPROVE_SUCCESS.getMessage());
//        } else {
//            System.out.println(ErrorCode.NO_INCOMINGNUM_APPROVE.getMessage());
//        }
        String checkAdminId = null;
        if (role.equals("창고관리자")) {
            checkAdminId = incomingDAO.getAdminIdByIncomingNum(incomingNum);
        } else if (role.equals("총관리자")) {
            checkAdminId = incomingDAO.getAdminIdByIncomingNumNext(incomingNum);
        }
        if (checkAdminId != null && checkAdminId.equals(adminId)) {
            String newStatus = null;
            if (role.equals("창고관리자")) newStatus = "진행중";
            else if (role.equals("총관리자")) newStatus = "완료";
            incomingDAO.updateIncomingStatus(incomingNum, newStatus);
            System.out.println(MessageEnum.INCOMING_APPROVE_SUCCESS.getMessage());
        } else {
            System.out.println(ErrorCode.NO_INCOMINGNUM_APPROVE.getMessage());
        }
    }
}

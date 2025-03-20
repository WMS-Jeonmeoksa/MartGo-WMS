package controller;

import common.constants.ErrorCode;
import model.dto.OutgoingDTO;
import model.dto.StockDTO;
import model.service.IncomingService;
import model.service.IncomingServiceImpl;
import model.service.OutgoingService;
import model.service.OutgoingServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static common.constants.MessageEnum.*;

public class OutgoingControllerImpl implements OutgoingController {
    OutgoingService outgoingService = new OutgoingServiceImpl();
    IncomingService incomingService = new IncomingServiceImpl();
    Scanner sc = new Scanner(System.in);

    @Override
    public void requestOutgoing(String userId) {
        System.out.println(INPUT_OUTGOING_TITLE.getMessage());
        System.out.println(SHOW_STOCK_TITLE.getMessage());

        List<StockDTO> stockDTOList = outgoingService.showStockByUserId(userId);
        System.out.println(SHOW_STOCK_LIST.getMessage());
        for (StockDTO stockDTO : stockDTOList) {
            System.out.println(stockDTO);
        }

        System.out.println(INPUT_OUTGOING_STOCK_NUM.getMessage());
        int stockNum = Integer.parseInt(sc.nextLine());
        System.out.println(INPUT_OUTGOING_COUNT.getMessage());
        int count = Integer.parseInt(sc.nextLine());
        System.out.println(INPUT_OUTGOING_DATE.getMessage());
        String date = sc.nextLine();

        Date outgoingDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            outgoingDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(ErrorCode.INPUT_WRONG_DATE.getMessage());
            return;
        }

        OutgoingDTO outgoingDTO = OutgoingDTO.builder()
                .count(count)
                .outgoingDate(outgoingDate)
                .status("대기")
                .userId(userId)
                .stockNum(stockNum).build();

        outgoingService.requestOutgoing(outgoingDTO);
    }

    @Override
    public void approveOutgoing(String adminId) {
        String role = incomingService.getAdminRoleById(adminId);
        List<OutgoingDTO> outgoingDTOList = outgoingService.getOutgoingByRole(adminId, role);
        if (outgoingDTOList == null || outgoingDTOList.isEmpty()) {
            System.out.println(NO_OUTGOING_LIST.getMessage());
            return;
        }
        System.out.println(SHOW_OUTGOING_LIST.getMessage());
        for (OutgoingDTO outgoingDTO : outgoingDTOList) {
            System.out.println(outgoingDTO);
        }
        System.out.println(INPUT_OUTGOING_APPROVE.getMessage());
        int outgoingNum = Integer.parseInt(sc.nextLine());

        outgoingService.approveOutgoing(adminId, outgoingNum, role);
    }

    public static void main(String[] args) {
        OutgoingControllerImpl outgoingControllerImpl = new OutgoingControllerImpl();
        //Scanner sc = new Scanner(System.in);
        //outgoingControllerImpl.requestOutgoing("1111");
        outgoingControllerImpl.approveOutgoing("9999");
    }
}

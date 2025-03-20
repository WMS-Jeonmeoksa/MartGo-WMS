package controller;

import common.constants.ErrorCode;
import common.utils.ValidationUtil;
import model.dto.IncomingDTO;
import model.dto.ProductDTO;
import model.service.IncomingService;
import model.service.IncomingServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static common.constants.MessageEnum.*;

public class IncomingControllerImpl implements IncomingController {
    IncomingService incomingService = new IncomingServiceImpl();
    Scanner sc = new Scanner(System.in);

    @Override
    public void requestIncoming(String userId) {
        System.out.println(INPUT_INCOMING_TITLE.getMessage());
        List<ProductDTO> productDTOList = incomingService.getProductByUserId(userId);
        System.out.println(SHOW_PRODUCT_LIST.getMessage());
        for (ProductDTO productDTO : productDTOList) {
            System.out.println(productDTO);
        }
        System.out.println(INPUT_INCOMING_PRODUCT_ID.getMessage());
        String productId = sc.nextLine();
        int count = ValidationUtil.getValidPositiveNumber(INPUT_INCOMING_COUNT.getMessage());

        Date incomingDate = null;
        while (incomingDate == null) {
            System.out.println(INPUT_INCOMING_DATE.getMessage());
            String date = sc.nextLine();
            incomingDate = ValidationUtil.isValidDate(date);
        }
        List<String> productIds = productDTOList.stream()
                .map(ProductDTO::getProductId)
                .collect(Collectors.toList());

        if (!productIds.contains(productId)) {
            System.out.println(ErrorCode.NO_PRODUCT_INCOMING.getMessage());
        } else {
            IncomingDTO incomingDTO = IncomingDTO.builder()
                    .count(count)
                    .incomingDate(incomingDate)
                    .status("대기")
                    .productId(productId)
                    .userId(userId)
                    .build();

            incomingService.requestIncoming(incomingDTO);
        }
    }

    @Override
    public void approveIncoming(String adminId) {
        String role = incomingService.getAdminRoleById(adminId);
        List<IncomingDTO> incomingDTOList = incomingService.getIncomingByRole(adminId, role);
        System.out.println(SHOW_INCOMING_LIST.getMessage());
        if (incomingDTOList == null || incomingDTOList.isEmpty()) {
            System.out.println(NO_INCOMING_LIST.getMessage());
            return;
        }
        for (IncomingDTO incomingDTO : incomingDTOList) {
            System.out.println(incomingDTO);
        }
        System.out.println(INPUT_INCOMING_APPROVE.getMessage());
        int incomingNum = Integer.parseInt(sc.nextLine());

        incomingService.approveIncoming(adminId, incomingNum, role);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IncomingControllerImpl incomingControllerImpl = new IncomingControllerImpl();
        //incomingControllerImpl.approveIncoming("9999");
        incomingControllerImpl.requestIncoming("1111");
    }
}

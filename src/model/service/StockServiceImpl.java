package model.service;

import model.dao.StockDAO;
import model.dao.StockDAOImpl;
import model.dto.StockDTO;
import java.util.ArrayList;


import java.util.List;

public class StockServiceImpl implements StockService {
    private StockDAO stockDAO = new StockDAOImpl();

    // 입고 승인(완료) 시점에 프로시저를 호출하여 재고와 이력 업데이트
    public boolean isIncomingStockUpdated(int incoming_num) {
        boolean result = stockDAO.incomingUpdateStock(incoming_num);
        if (result) {
            System.out.println("재고 업데이트 완료 : 재고 업데이트 완료.");
        } else {
            System.err.println("재고 업데이트 실패 : 재고 업데이트 에러 발생.");
        }
        return result;
    }

    @Override
    public boolean isIncomingStockHistoryUpdated(int incoming_num) {
        boolean result = stockDAO.incomingUpdateStockHistory(incoming_num);
        if (result) {
            System.out.println("재고 이력 업데이트 완료 : 재고 이력 업데이트 완료.");
        } else {
            System.err.println("재고 이력 업데이트 실패 : 재고 이력 업데이트 에러 발생.");
        }
        return result;
    }

    @Override
    public boolean isOutgoingStockUpdated(int outgoing_num) {
        boolean result = stockDAO.outgoingUpdateStock(outgoing_num);
        if (result) {
            System.out.println("재고 업데이트 완료 : 재고 업데이트 완료.");
        } else {
            System.err.println("재고 업데이트 실패 : 재고 업데이트 에러 발생.");
        }
        return result;
    }

    @Override
    public boolean isOutgoingStockHistoryUpdated(int outgoing_num) {
        boolean result = stockDAO.incomingUpdateStockHistory(outgoing_num);
        if (result) {
            System.out.println("재고 이력 업데이트 완료 : 재고 이력 업데이트 완료.");
        } else {
            System.err.println("재고 이력 업데이트 실패 : 재고 이력 업데이트 에러 발생.");
        }
        return result;
    }

    public List<StockDTO> getUserStock(int user_id) {
        return stockDAO.checkUserStock(user_id).orElseGet(ArrayList::new);
    }
}

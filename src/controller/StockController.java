package controller;

import model.dto.StockDTO;
import model.service.StockService;
import model.service.StockServiceImpl;

import java.util.List;

public class StockController {

    public static void main(String[] args) {
        StockService stockService = new StockServiceImpl();


        // 여기에 입고 완료 되었을 때 입고번호를 넘겨 받음
        int incoming_num = 0;
        if (stockService.checkIncomingStock(incoming_num)) {
            System.out.println("재고 업데이트가 성공적으로 완료되었습니다.");
        } else {
            System.out.println("재고 업데이트에 실패하였습니다.");
        }

        
        // 입고 완료 되었을 때 해당 회원 id 를 넘겨받음
        int user_id = 0;
        List<StockDTO> userStock = stockService.getUserStock(user_id);
        userStock.forEach(stock -> {
            System.out.println("Stock 번호: " + stock.getStock_num());
            System.out.println("제품 ID: " + stock.getProduct_id());
            System.out.println("수량: " + stock.getCount());
            System.out.println("총가격: " + stock.getTotal_price());
            System.out.println("섹터 ID: " + stock.getSector_id());
            System.out.println("창고 ID: " + stock.getWarehouse_id());
            System.out.println("어드민 ID: " + stock.getAdmin_id());
            System.out.println("-----------------------------");
        });
    }
}

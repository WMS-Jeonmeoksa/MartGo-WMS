package controller;

import model.dto.StockDTO;
import model.service.StockService;
import model.service.StockServiceImpl;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class StockController {

    public static void main(String[] args) {
        StockService stockService = new StockServiceImpl();

        // 입고 완료 되었을 때 해당 회원 id 를 넘겨받음
//        String user_id = "U123";
//        List<StockDTO> userStock = stockService.getUserStock(user_id);
//        userStock.forEach(stock -> {
//            System.out.println("Stock 번호: " + stock.getStock_num());
//            System.out.println("회원 ID " + stock.getUser_id());
//            System.out.println("제품 ID: " + stock.getProduct_id());
//            System.out.println("수량: " + stock.getCount());
//            System.out.println("총가격: " + stock.getTotal_price());
//            System.out.println("섹터 ID: " + stock.getSector_id());
//            System.out.println("창고 ID: " + stock.getWarehouse_id());
//            System.out.println("-----------------------------");
//        });

        String admin_id = "A123";
        List<StockDTO> allStock = stockService.getAllStock(admin_id);
        allStock.forEach(stock -> {
            System.out.println("Stock 번호: " + stock.getStock_num());
            System.out.println("회원 ID " + stock.getUser_id());
            System.out.println("제품 ID: " + stock.getProduct_id());
            System.out.println("수량: " + stock.getCount());
            System.out.println("총가격: " + stock.getTotal_price());
            System.out.println("섹터 ID: " + stock.getSector_id());
            System.out.println("창고 ID: " + stock.getWarehouse_id());
            System.out.println("-----------------------------");
        });
    }
}

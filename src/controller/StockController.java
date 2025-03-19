package controller;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;
import model.service.StockService;
import model.service.StockServiceImpl;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class StockController {
    StockService stockService = new StockServiceImpl();

    public void printUserStock(String user_id) {
        List<StockDTO> userStock = stockService.getUserStock(user_id);
        userStock.forEach(stock -> {
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


    public void printAllStock(String admin_id) {
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

    public void printAllStockHistory() {
        List<StockHistoryDTO> allStockHistory = stockService.getStockHistoryList();
        allStockHistory.forEach(stock -> {
            System.out.println("-----------------------------");
            System.out.println("변경 이력 번호 : " + stock.getHistory_num());
            System.out.println("제품 ID : " + stock.getProduct_id());
            System.out.println("섹터 ID : " + stock.getSector_id());
            System.out.println("수량 : " + stock.getCount());
            System.out.println("변경 날짜 : " + stock.getChange_date());
            System.out.println("변경 구분 : " + stock.getChange_type());
            System.out.println("어드민 ID : " + stock.getAdmin_id());
            System.out.println("입고 번호 : " + stock.getIncoming_num());
            System.out.println("출고 번호 : " + stock.getOutgoing_num());
            System.out.println("Stock 번호 : " + stock.getStock_num());
            System.out.println("-----------------------------");
        });
    }
}

package controller;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;
import model.service.StockService;
import model.service.StockServiceImpl;

import java.util.List;

public class StockControllerImpl implements StockController {
    StockService stockService = new StockServiceImpl();

    public static void main(String[] args) {
        StockControllerImpl stockController = new StockControllerImpl();
        String admin_id = "A123";
        String user_id = "U456";
//        stockController.printUserStock(user_id);
//        stockController.printAdminUserStock(admin_id);
//        stockController.printAdminStockHistory(admin_id);
//        stockController.printGeneralStock(admin_id);
        stockController.printGeneralStockHistory(admin_id);
    }

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


    public void printAdminUserStock(String admin_id) {
        List<StockDTO> allStock = stockService.getAdminUserStock(admin_id);
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
    public void printGeneralStock(String admin_id) {
        List<StockDTO> generalStock = stockService.getGeneralStock(admin_id);
        generalStock.forEach(stock -> {
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


    public void printGeneralStockHistory(String admin_id) {
        List<StockHistoryDTO> generalStockHistory = stockService.getGeneralStockHistory(admin_id);
        generalStockHistory.forEach(stock -> {
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

    public void printAdminStockHistory(String admin_id) {
        List<StockHistoryDTO> adminStockHistory = stockService.getAdminStockHistory(admin_id);
        adminStockHistory.forEach(stock -> {
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

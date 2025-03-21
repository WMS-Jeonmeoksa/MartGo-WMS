package controller;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;
import model.service.StockService;
import model.service.StockServiceImpl;

import java.util.List;

import static common.constants.MessageEnum.*;

public class StockControllerImpl implements StockController {
    StockService stockService = new StockServiceImpl();

    public void printUserStock(String user_id) {
        List<StockDTO> userStock = stockService.getUserStock(user_id);
        if (userStock == null || userStock.isEmpty()) {
            System.out.println(NO_STOCK_LIST.getMessage());
        } else {
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-10s %-6s %-8s %-8s %-8s\n",
                    "Stock 번호", "회원 ID", "제품 ID", "수량", "총가격", "섹터 ID", "창고 ID"
            );
            System.out.println("---------------------------------------------------------------------------");
            userStock.forEach(stock -> {
                System.out.printf("    %-10s %-10s %-10s   %-6d  %-8d    %-8s   %-8s\n"
                        , stock.getStock_num(), stock.getUser_id(), stock.getProduct_id(), stock.getCount()
                        , stock.getTotal_price(), stock.getSector_id(), stock.getWarehouse_id(), stock.getWarehouse_id());
            });
        }
    }


    public void printAdminUserStock(String admin_id) {
        List<StockDTO> adminUserStock = stockService.getAdminUserStock(admin_id);
        if (adminUserStock == null || adminUserStock.isEmpty()) {
            System.out.println(NO_STOCK_LIST.getMessage());
        } else {
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-10s %-6s %-8s %-8s %-8s\n",
                    "Stock 번호", "회원 ID", "제품 ID", "수량", "총가격", "섹터 ID", "창고 ID"
            );
            System.out.println("---------------------------------------------------------------------------");
            adminUserStock.forEach(stock -> {
                System.out.printf("    %-10s %-10s %-10s   %-6d  %-8d    %-8s   %-8s\n"
                        , stock.getStock_num(), stock.getUser_id(), stock.getProduct_id(), stock.getCount()
                        , stock.getTotal_price(), stock.getSector_id(), stock.getWarehouse_id(), stock.getWarehouse_id());
            });
        }
    }

    public void printGeneralStock(String admin_id) {
        List<StockDTO> generalStock = stockService.getGeneralStock(admin_id);
        if (generalStock == null || generalStock.isEmpty()) {
            System.out.println(NO_STOCK_LIST.getMessage());
        } else {
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-10s %-6s %-8s %-8s %-8s\n",
                    "Stock 번호", "회원 ID", "제품 ID", "수량", "총가격", "섹터 ID", "창고 ID"
            );
            System.out.println("---------------------------------------------------------------------------");
            generalStock.forEach(stock -> {
                System.out.printf("    %-10s %-10s %-10s   %-6d  %-8d    %-8s   %-8s\n"
                        , stock.getStock_num(), stock.getUser_id(), stock.getProduct_id(), stock.getCount()
                        , stock.getTotal_price(), stock.getSector_id(), stock.getWarehouse_id(), stock.getWarehouse_id());
            });
        }
    }


    public void printGeneralStockHistory(String admin_id) {
        List<StockHistoryDTO> generalStockHistory = stockService.getGeneralStockHistory(admin_id);
        if (generalStockHistory == null || generalStockHistory.isEmpty()) {
            System.out.println(NO_STOCK_HISTORY_LIST.getMessage());
        } else {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-10s %-6s   %-8s   %-8s %-8s %-8s %-8s %-8s\n",
                    "변경 이력 번호", "제품 ID", "섹터 ID", "수량", "변경 날짜", "변경 구분", "어드민 ID","입고 번호","출고 번호","Stock 번호"
            );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            generalStockHistory.forEach(stock -> {
                System.out.printf("    %-10s    %-10s    %-10s%-6d  %-8s      %-9s   %-8s   %-8d   %-10d   %-10d\n"
                        , stock.getHistory_num(), stock.getProduct_id(), stock.getSector_id(), stock.getCount()
                        , stock.getChange_date(), stock.getChange_type(), stock.getAdmin_id(), stock.getIncoming_num(), stock.getOutgoing_num(),stock.getStock_num());
            });
        }
    }

    public void printAdminStockHistory(String admin_id) {
        List<StockHistoryDTO> adminStockHistory = stockService.getAdminStockHistory(admin_id);
        if (adminStockHistory == null || adminStockHistory.isEmpty()) {
            System.out.println(NO_STOCK_HISTORY_LIST.getMessage());
        } else {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-10s %-6s   %-8s   %-8s %-8s %-8s %-8s %-8s\n",
                    "변경 이력 번호", "제품 ID", "섹터 ID", "수량", "변경 날짜", "변경 구분", "어드민 ID","입고 번호","출고 번호","Stock 번호"
            );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            adminStockHistory.forEach(stock -> {
                System.out.printf("    %-10s    %-10s    %-10s%-6d  %-8s      %-9s   %-8s   %-8d   %-10d   %-10d\n"
                        , stock.getHistory_num(), stock.getProduct_id(), stock.getSector_id(), stock.getCount()
                        , stock.getChange_date(), stock.getChange_type(), stock.getAdmin_id(), stock.getIncoming_num(), stock.getOutgoing_num(),stock.getStock_num());
            });
        }
    }
}

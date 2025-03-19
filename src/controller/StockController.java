package controller;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.util.List;

public interface StockController {
    // 회원이 입고한 재고 목록 확인
    public void printUserStock(String user_id);
    // 창고 관리자가 담당하고 있는 회원의 재고 목록 확인
    public void printAdminUserStock(String admin_id);
    // 총 관리자가 담당하고 있는 창고의 재고 목록 확인
    public void printGeneralStock(String admin_id);
    // 창고 관리자가 담당하고 있는 회원의 재고 변경 이력 확인
    public void printAdminStockHistory(String admin_id);
    // 총 관리자가 담당하고 있는 창고의 재고 변경 이력 확인
    public void printGeneralStockHistory(String admin_id);
}
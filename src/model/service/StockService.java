package model.service;

import model.dto.StockDTO;
import model.dto.StockHistoryDTO;

import java.util.List;

public interface StockService {
    // 회원이 입고한 재고 목록 확인
    List<StockDTO> getUserStock(String user_id);
    // 창고 관리자가 담당하고 있는 회원의 재고 목록 확인
    List<StockDTO> getAdminUserStock(String admin_id);
    // 총 관리자가 담당하고 있는 창고의 재고 목록 확인
    List<StockDTO> getGeneralStock(String admin_id);
    // 창고 관리자가 담당하고 있는 회원의 재고 변경 이력 확인
    List<StockHistoryDTO> getAdminStockHistory(String admin_id);
    // 총 관리자가 담당하고 있는 창고의 재고 변경 이력 확인
    List<StockHistoryDTO> getGeneralStockHistory(String admin_id);
}

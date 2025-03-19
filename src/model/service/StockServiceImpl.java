package model.service;

import model.dao.StockDAO;
import model.dao.StockDAOImpl;
import model.dto.StockDTO;
import model.dto.StockHistoryDTO;


import java.util.List;

public class StockServiceImpl implements StockService {
    private StockDAO stockDAO = new StockDAOImpl();

    public List<StockDTO> getUserStock(String user_id) {
        List<StockDTO> resultUserStock = stockDAO.checkUserStock(user_id);
        return resultUserStock;
    }

    @Override
    public List<StockDTO> getAdminUserStock(String admin_id) {
        List<StockDTO> resultAdminUserStock = stockDAO.checkAdminUserStock(admin_id);
        return resultAdminUserStock;
    }

    @Override
    public List<StockDTO> getGeneralStock(String admin_id) {
        List<StockDTO> resultGeneralStock = stockDAO.checkGeneralStock(admin_id);
        return resultGeneralStock;
    }

    @Override
    public List<StockHistoryDTO> getAdminStockHistory(String admin_id) {
        List<StockHistoryDTO> resultAdminStockHistory = stockDAO.checkAdminStockHistory(admin_id);
        return resultAdminStockHistory;
    }

    @Override
    public List<StockHistoryDTO> getGeneralStockHistory(String admin_id) {
        List<StockHistoryDTO> resultGeneralStockHistory = stockDAO.checkGeneralStockHistory(admin_id);
        return resultGeneralStockHistory;
    }

}

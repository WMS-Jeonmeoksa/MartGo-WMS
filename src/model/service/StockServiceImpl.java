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
    public List<StockDTO> getAllStock(String admin_id) {
        List<StockDTO> resultAllStock = stockDAO.checkAllStock(admin_id);
        return resultAllStock;
    }

    @Override
    public List<StockHistoryDTO> getStockHistoryList() {
        List<StockHistoryDTO> resultAllStockHistory = stockDAO.checkStockHistoryList();
        return resultAllStockHistory;
    }
}

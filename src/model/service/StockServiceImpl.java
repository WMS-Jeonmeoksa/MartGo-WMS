package model.service;

import model.dao.StockDAO;
import model.dao.StockDAOImpl;
import model.dto.StockDTO;
import java.util.ArrayList;


import java.util.List;

public class StockServiceImpl implements StockService {
    private StockDAO stockDAO = new StockDAOImpl();

    public List<StockDTO> getUserStock(int user_id) {
        return stockDAO.checkUserStock(user_id).orElseGet(ArrayList::new);
    }
}
